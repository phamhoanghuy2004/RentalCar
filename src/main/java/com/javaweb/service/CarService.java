package com.javaweb.service;


import java.util.List;
<<<<<<< HEAD
import org.springframework.web.multipart.MultipartFile;
import com.javaweb.beans.CarDTO;
=======


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.javaweb.beans.CarDTO;
import com.javaweb.beans.request.InsertCarRequest;
>>>>>>> Nha




public interface CarService {
<<<<<<< HEAD
	public List<CarDTO> getCarOfBrandActive(Long idBrand);
	public List<CarDTO> getNewCar();
	public List<CarDTO> getSaleCar();
//	public int updateLogo(int id, MultipartFile file)  throws IOException;
//	Object lessThanSevenDay();
//	Object topTen();
=======
	public ResponseEntity insertCar(InsertCarRequest carRequest);

>>>>>>> Nha
}
