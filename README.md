# 🚗 RentalCar - Spring Boot Project

Đây là đồ án Spring Boot API Web Service ứng dụng mô hình MVC cho hệ thống thuê xe online và quản lý hệ thống thuê xe online trong bộ môn lập trình di động tại trường đại học SPKT TPHCM
Tích hợp với cơ sở dữ liệu MySQL,  dùng Redis, JWT để xác thực..

---

## 📦 Yêu cầu hệ thống

Trước khi chạy dự án, hãy đảm bảo bạn đã cài đặt:

- ✅ Java JDK 22 trở lên
- ✅ Maven 3.9.9
- ✅ MySQL (dùng Cloud DB của Aiven)
- ✅ Redis (cài local trên port 6379)
- ✅ IDE như IntelliJ IDEA, Eclipse hoặc VSCode

---

## 🚀 Cách chạy dự án

### 1. Clone source code từ GitHub
```bash
git clone https://github.com/phamhoanghuy2004/RentalCar.git
cd RentalCar
```

### 2. Cấu hình kết nối
Mở file src/main/resources/application.properties và chắc chắn cấu hình đúng như sau:
```bash
server.port=8081
server.address=0.0.0.0
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.redis.host=localhost
spring.redis.port=6379
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
upload.directory=uploads
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=20MB
```

### 3. Cấu hình các biến môi trường
DB_PASSWORD = AVNS_439qsgZdIKzTmluCfBg
DB_URL = jdbc:mysql://mysql-2cc2c868-phamhoanghuy-96f0.e.aivencloud.com:15293/rentalcar?ssl-mode=REQUIRED
DB_USER = avnadmin
JWT_SECRET = dALEq7X6po7DFjeXCbvQOxiHuil9pdtO6lNED5Q849BY+hRaVeKwFjbjHtsd+FDo

### 4. Build và chạy ứng dụng
```bash
mvn clean install
mvn spring-boot:run
```

---

## 👤 Thông tin tác giả
Tác giả: Phạm Hoàng Huy
📧 Email: phamhoanghuy.2000vn@gmail.com
Tác giả: Trần Thanh Nhã
📧 Email: ttn04042004@gmail.com
