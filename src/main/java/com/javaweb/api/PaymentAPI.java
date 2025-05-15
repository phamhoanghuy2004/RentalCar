package com.javaweb.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.beans.ContractDTO;
import com.javaweb.beans.PaymentDTO;
import com.javaweb.beans.PaymentResDTO;
import com.javaweb.beans.ResultDTO;
import com.javaweb.config.Config;
import com.javaweb.customeExceptions.FiledRequiredException;
import com.javaweb.entity.ContractEntity;
import com.javaweb.entity.PaymentEntity;
import com.javaweb.service.ContractService;
import com.javaweb.service.PaymentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/payment")
public class PaymentAPI {
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/create_payment")
	public ResponseEntity<?> createPayment(@RequestParam("idContract") Long idContract, @RequestParam("price") Long price  , HttpServletRequest request) throws UnsupportedEncodingException {
	    // Khai báo các tham số cần thiết
	    String vnp_Version = "2.1.0";
	    String vnp_Command = "pay";
	    String orderType = "other";
	        
	    if ( idContract == null || idContract.equals("")) {
	    	throw new FiledRequiredException("Không thể tìm thấy hợp đồng cần thanh toán!");
	    }
	    
	    ContractEntity  contractEntity = contractService.getContract(idContract);
	    if (contractEntity == null) {
	    	throw new FiledRequiredException("Không thể tìm thấy hợp đồng cần thanh toán!");
	    }
	    
	    if (price == null || price <= 0 || !price.equals(contractEntity.getPrice().longValue())) {
	    	throw new FiledRequiredException("Cần phải có số tiền của hợp đồng chính xác!");
	    }  
	    
	    // Thiết lập giá trị mặc định cho amount (10,000 VND)
	    long amount = price * 100;  // 10,000 VND (VNPAY yêu cầu đơn vị là đồng, nên nhân với 100)
	    
	    // Thiết lập giá trị mặc định cho bankCode (NCB)
	    String bankCode = "NCB";    // Mặc định là NCB

	    // Tạo mã giao dịch và lấy địa chỉ IP
	    String vnp_TxnRef = Config.getRandomNumber(8);
	    String vnp_IpAddr = Config.getIpAddress(request);

	    // Mã của merchant (do VNPAY cung cấp)
	    String vnp_TmnCode = Config.vnp_TmnCode;
	    
	    // Khởi tạo map chứa các tham số
	    Map<String, String> vnp_Params = new HashMap<>();
	    vnp_Params.put("vnp_Version", vnp_Version);
	    vnp_Params.put("vnp_Command", vnp_Command);
	    vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
	    vnp_Params.put("vnp_Amount", String.valueOf(amount));
	    vnp_Params.put("vnp_CurrCode", "VND");
	    
	    // Thêm mã ngân hàng nếu có
	    vnp_Params.put("vnp_BankCode", bankCode);
	    
	    // Các thông tin khác liên quan đến đơn hàng
	    vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
	    vnp_Params.put("vnp_OrderInfo", idContract.toString() + "-" + vnp_TxnRef);
	    vnp_Params.put("vnp_OrderType", orderType);

	    // Kiểm tra ngôn ngữ
	    String locate = request.getParameter("language");
	    if (locate != null && !locate.isEmpty()) {
	        vnp_Params.put("vnp_Locale", locate);
	    } else {
	        vnp_Params.put("vnp_Locale", "vn");
	    }
	    
	    // URL trả về sau khi thanh toán
	    vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
	    vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

	    // Tạo thời gian tạo và thời gian hết hạn
	    Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	    String vnp_CreateDate = formatter.format(cld.getTime());
	    vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
	    
	    cld.add(Calendar.MINUTE, 15);
	    String vnp_ExpireDate = formatter.format(cld.getTime());
	    vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
	    
	    // Sắp xếp các tham số và xây dựng chuỗi hash
	    List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
	    Collections.sort(fieldNames);
	    StringBuilder hashData = new StringBuilder();
	    StringBuilder query = new StringBuilder();
	    Iterator<String> itr = fieldNames.iterator();
	    
	    while (itr.hasNext()) {
	        String fieldName = itr.next();
	        String fieldValue = vnp_Params.get(fieldName);
	        if (fieldValue != null && fieldValue.length() > 0) {
	            // Tạo chuỗi hash
	            hashData.append(fieldName);
	            hashData.append('=');
	            hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
	            // Tạo chuỗi query
	            query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
	            query.append('=');
	            query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
	            if (itr.hasNext()) {
	                query.append('&');
	                hashData.append('&');
	            }
	        }
	    }

	    // Tạo Secure Hash
	    String queryUrl = query.toString();
	    String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
	    queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
	    
	    // Tạo URL thanh toán
	    String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

	    // Tạo DTO PaymentResDTO
	    PaymentResDTO paymentRes = new PaymentResDTO();
	    paymentRes.setStatus("00");
	    paymentRes.setMessage("success");
	    paymentRes.setURL(paymentUrl);
	    
	    // Trả về kết quả dưới dạng JSON từ DTO
	    return ResponseEntity.ok(paymentRes);
	}
	
	@GetMapping("/payment_infor")
	public ModelAndView transaction(
			@RequestParam(value = "vnp_Amount") String amount,
			@RequestParam(value = "vnp_BankCode") String bankCode,
			@RequestParam(value = "vnp_OrderInfo") String order,
			@RequestParam(value = "vnp_ResponseCode") String responseCode
			) {
		
		ModelAndView mv = new ModelAndView("payment_result");
		ResultDTO<ContractDTO> result = new ResultDTO<>();
		String idContract = extractId(order);
		
		if (idContract == null || idContract.isEmpty() ) {
			result.setStatus(false);
	        result.setMessage("Thanh toán lỗi! Số tiền sẽ được hoàn lại.");
	        mv.addObject("result", result);
	        return mv;
		}
		
		if (amount == null || amount.isEmpty()) {
			result.setStatus(false);
	        result.setMessage("Thanh toán lỗi! Số tiền sẽ được hoàn lại.");
	        mv.addObject("result", result);
	        return mv;
		}
		
		if (responseCode.equals("00")) {	
			Long idContractL = Long.parseLong(idContract);
			Date date = new Date();
			Long price = Long.parseLong(amount) / 100;
			
			result = contractService.upDatePayment(idContractL, date, price);
		}
		else {
			result.setStatus(false);
			result.setMessage("Giao dịch thất bại số tiền sẽ được hoàn lại!");
		}
		 mv.addObject("result", result);
		 return mv;
	}
	
	public String extractId (String vnp_OrderInfo) {
		if (vnp_OrderInfo == null || !vnp_OrderInfo.contains("-")) {
			return null;
		}
		try {
			return vnp_OrderInfo.split("-")[0].trim();  // lay phan truoc 
		}
		catch (Exception e) {
	        e.printStackTrace();
	        return null;
		}
	}
	
	@GetMapping(value = "/getAll")
	public List<PaymentDTO> getAll(){
		return paymentService.getAll();
	}
}
