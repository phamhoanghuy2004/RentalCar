package com.javaweb.service.Impl;


import java.text.ParseException;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.javaweb.beans.CustomerDTO;
import com.javaweb.beans.ResultDTO;
import com.javaweb.beans.request.GGLoginRequest;
import com.javaweb.beans.request.updateProfileRequest;
import com.javaweb.converter.AddressConverter;
import com.javaweb.converter.CustomerDTOConverter;
import com.javaweb.customeExceptions.UnauthorizedException;
import com.javaweb.entity.AddressEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.AddressRepository;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.CustomerService;
import com.javaweb.util.TokenService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;
import java.time.Duration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.javaweb.beans.request.UserRequest;
import com.javaweb.util.EmailService;
import com.javaweb.util.OTPGenerate;



@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
    private CustomerRepository customerRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AddressRepository addressRepository;


	@Autowired
    private RedisTemplate<String, String> redisTemplate;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CustomerDTOConverter customerDTOConverter;
	
	@Autowired
	private AddressConverter addressConverter;
	
	@Value("${JWT_SECRET}")
	private String jwtSecret;
	
	
	public CustomerEntity convertToEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, CustomerEntity.class);
    }
	
	public AddressEntity convertToAddressEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, AddressEntity.class);
    }
    
	@Override
	public boolean updateStatusByEmail(String email) {
        CustomerEntity customer = customerRepository.findByEmail(email).get();
        if (customer != null) {
//            customer.setStatus(1);
        	customerRepository.save(customer);
            return true; 
        }
        return false; 
	}

	@Override
	public ResultDTO<String> Login(String email, String password) throws KeyLengthException, JOSEException {
		ResultDTO<String> result = new ResultDTO<String>();
		Optional<CustomerEntity> opt  = customerRepository.findByEmail(email);
		if(opt.isEmpty()) {
			result.setStatus(false);
			result.setData(null);
			result.setMessage("Email không tồn tại");
		} else {
			CustomerEntity customerEntity = opt.orElse(null);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (customerEntity == null || customerEntity.getStatus() == 0 || !encoder.matches(password, customerEntity.getPassword())) {
				result.setStatus(false);
				result.setData(null);
				result.setMessage("Sai tài khoản hoặc mật khẩu");
			}
			else {
				result.setStatus(true);
				result.setData(TokenService.genarateToken(customerEntity.getEmail(), customerEntity.getId(),"CUSTOMER" ,jwtSecret));
				result.setMessage("Đăng nhập thành công");
			}
		}
		return result;
	}
	
	@Override
	public ResultDTO<String> LoginGG(GGLoginRequest ggLoginRequest) throws KeyLengthException, JOSEException {
		ResultDTO<String> result = new ResultDTO<String>();
		Optional<CustomerEntity> opt = customerRepository.findByEmail(ggLoginRequest.getEmail());
		CustomerEntity customerEntity = opt.orElse(null);
		
		if (customerEntity != null && customerEntity.getStatus() == 1) {
			result.setStatus(true);
			result.setData(TokenService.genarateToken(customerEntity.getEmail(), customerEntity.getId(), "CUSTOMER" ,jwtSecret));
			result.setMessage("Đăng nhập thành công");
			
			return result;
		}
		
		// tao tai khoan sau do tra ve thang token
		customerEntity = new CustomerEntity();
		customerEntity.setName(ggLoginRequest.getName());
		customerEntity.setEmail(ggLoginRequest.getEmail());
		customerEntity.setAvatar(ggLoginRequest.getAvt());
		String password = new StringBuilder(ggLoginRequest.getEmail()).reverse().toString();
		customerEntity.setPassword(password);
		customerEntity.setStatus(1);
		
		customerEntity = customerRepository.save(customerEntity);
		
		result.setStatus(false);
		result.setData(TokenService.genarateToken(customerEntity.getEmail(), customerEntity.getId(), "CUSTOMER",jwtSecret));
		result.setMessage("Đăng nhập thành công");	
		return result;
	}
	

	@Override
	public ResultDTO<CustomerDTO> Forgot(String email) {
		ResultDTO<CustomerDTO> result = new ResultDTO<CustomerDTO>();
		Optional<CustomerEntity> userOt = customerRepository.findByEmail(email);
		CustomerEntity customerEntity = userOt.orElse(null); 
		if (customerEntity == null || customerEntity.getStatus() == 0) {
			result.setStatus(false);
			result.setMessage("Tài khoản sai hoặc chưa kích hoạt!");
			result.setData(null);
		}
		else {
			result.setStatus(true);
			result.setMessage("Đã tìm thấy tài khoản vui lòng kiểm tra email để xác nhận OTP!");
			CustomerDTO customerDTO = customerDTOConverter.convertToDTO(customerEntity);
			result.setData(customerDTO);
		}
		return result;
	}

	@Override
	public boolean ResetPass(String pass, String email) {
		Optional<CustomerEntity> userOt = customerRepository.findByEmail(email);
		if (userOt.isPresent()) {
			CustomerEntity  cusEntity =  userOt.get();
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        String encodedPassword = encoder.encode(pass);
	        cusEntity.setPassword(encodedPassword);
			customerRepository.save(cusEntity);
			return true;
		}
		else {
			return false;
		} 
	}

	@Override
	public CustomerDTO getProfile(String token) throws JOSEException, ParseException {
		Boolean isValid = TokenService.checkToken(token,jwtSecret );
		if (isValid) {
			Long id = TokenService.getId(token);
			Optional<CustomerEntity> optCustomer = customerRepository.findById(id);
			String role = TokenService.getRole(token);
			if (optCustomer.isPresent() && role.equals("CUSTOMER")) {
				// kiem tra xem trong opt co gia tri hay khong
				CustomerDTO result = customerDTOConverter.convertToDTO(optCustomer.get());
				return result;
			}
		}
		return null;
	}

	@Override
	public ResultDTO updateProfile(updateProfileRequest request, String token) throws JOSEException, ParseException {
		ResultDTO result = new ResultDTO<>();
		if (!TokenService.checkToken(token,jwtSecret)) {
			throw new UnauthorizedException("Không thể xác thực người dùng vui lòng đăng nhập lại");
		}
		
		Long idCus = TokenService.getId(token);
		CustomerEntity customerEntity = customerRepository.findById(idCus).orElse(null);
		if (customerEntity == null) {
			result.setStatus(false);
			result.setMessage("Không thể tìm thấy người dùng vui lòng đăng nhập lại!");
			return result;
		}
		
		AddressEntity address = customerEntity.getCustomerAddress();
		addressConverter.convertToEntityFromCusRequest(request, address);
		customerEntity.setCustomerAddress(address);
		
		customerDTOConverter.convertToEntityFromRequest(request, customerEntity);
		customerRepository.save(customerEntity);
		result.setStatus(true);
		result.setMessage("Cập nhật thông tin thành công");
		return result;

	}

	public CustomerEntity convertToCustomerEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, CustomerEntity.class);
    }

	
	@Override
	public ResultDTO registerUser(UserRequest userRequest) {
		ResultDTO result = new ResultDTO();
	    if (userRequest == null || userRequest.getEmail() == null || userRequest.getEmail().isBlank()) {
	        result.setStatus(false);
	        result.setMessage("Email không được để trống");
	        return result;
	    }
		if (customerRepository.findByEmail(userRequest.getEmail()).isPresent()) {	            
	            result.setStatus(false);
	            result.setMessage("Email đã tồn tại trong hệ thống");
	    } else {	    	 
	    	String otp = OTPGenerate.generateOTP2();
	    	try {
	    		   EmailService.sendOTP(userRequest.getEmail(), otp);
	    		   redisTemplate.opsForValue().set(userRequest.getEmail(), otp, Duration.ofMinutes(5));
	    		   result.setStatus(true);
	    		   result.setMessage("Đã gửi OTP. Vui lòng kiểm tra email của bạn");
	    	} catch (Exception e) {
	    		   result.setStatus(false);
	    		   result.setMessage("Không thể gửi OTP. Vui lòng thử lại sau");
	    		   e.printStackTrace();
	    		}
	     }
		 return result;
	}
	
	@Override
	public ResultDTO saveUser(UserRequest request) {
		ResultDTO result = new ResultDTO();
		try {
			AddressEntity newAddress = convertToAddressEntity(request);
			CustomerEntity customer = convertToCustomerEntity(request);

			String password = customer.getPassword();
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        String encodedPassword = encoder.encode(password);
	        customer.setPassword(encodedPassword);
			
			customer.setCustomerAddress(newAddress);
			customer.setStatus(1);
			customerRepository.save(customer);
			result.setStatus(true);
			result.setMessage("Đăng ký thành công");
		} catch (Exception e) {
			result.setStatus(false);
			result.setMessage("Xảy ra lỗi trong quá trình lưu dữ liệu");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ResultDTO<String> LoginAdmin (String email, String password) throws KeyLengthException, JOSEException {
		ResultDTO<String> result = new ResultDTO<String>();
		Optional<UserEntity> opt  = userRepository.findByEmail(email);
		if(opt.isEmpty()) {
			result.setStatus(false);
			result.setData(null);
			result.setMessage("Email không tồn tại");
		} else {
			UserEntity userEntity = opt.orElse(null);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (userEntity == null || userEntity.getStatus() == 0 || !encoder.matches(password, userEntity.getPassword())) {
				result.setStatus(false);
				result.setData(null);
				result.setMessage("Sai tài khoản hoặc mật khẩu");
			}
			else {
				result.setStatus(true);
				result.setData(TokenService.genarateToken(userEntity.getEmail(), userEntity.getId(),"ADMIN" ,jwtSecret));
				result.setMessage("Đăng nhập thành công");
			}
		}
		return result;
	}

}
