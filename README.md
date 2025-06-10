🚗 RentalCar - Spring Boot Project
Đây là đồ án Spring Boot API Web Service cho hệ thống thuê xe online, tích hợp các công nghệ hiện đại như:
✅ Spring Boot
✅ MySQL (Aiven Cloud)
✅ Redis (Cache & Session)
✅ JWT Authentication
✅ File Upload

📋 Yêu cầu hệ thống
Thành phần	Phiên bản tối thiểu
Java JDK	17+
Maven	3.6+
MySQL	8.0+
Redis	6.0+
⚙️ Cấu hình
1. Biến môi trường (.env)
properties
DB_URL=jdbc:mysql://mysql-2cc2c868-phamhoanghuy-96f0.e.aivencloud.com:15293/rentalcar?ssl-mode=REQUIRED
DB_USER=avnadmin
DB_PASSWORD=AVNS_439qsgZdIKzTmluCfBg
JWT_SECRET=dALEq7X6po7DFjeXCbvQOxiHuil9pdtO6lNED5Q849BY+hRaVeKwFjbjHtsd+FDo
2. Cấu hình ứng dụng (application.properties)
properties
# Server
server.port=8081
server.address=0.0.0.0

# Database
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

# Redis
spring.redis.host=localhost
spring.redis.port=6379

# File Upload
upload.directory=uploads
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=20MB
🚀 Khởi chạy dự án
bash
# 1. Clone repository
git clone https://github.com/phamhoanghuy2004/RentalCar.git
cd RentalCar

# 2. Build và chạy ứng dụng
mvn clean install
mvn spring-boot:run
Truy cập ứng dụng tại: http://localhost:8081

👥 Thông tin nhóm
Thành viên	Email
Phạm Hoàng Huy	phamhoanghuy.2000vn@gmail.com
Trần Thanh Nhã	ttn04042004@gmail.com
