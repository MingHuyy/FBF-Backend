# Football Field Booking App - Backend API

Backend API cho hệ thống đặt sân bóng đá, xây dựng với Spring Boot.

## Tech Stack

- **Java 21**
- **Spring Boot 3.5.10**
- **Spring Data JPA**
- **MySQL**
- **Maven**
- **Lombok**

## Database

Sử dụng MySQL database với tên: `fbf`

## Configuration

Tạo file `.env` trong thư mục root:

```env
DB_USERNAME=your_username
DB_PASSWORD=your_password
```

## Installation

```bash
# Clone repository
git clone https://github.com/MingHuyy/FBF-Backend.git
cd FBF-Backend

# Tạo database
CREATE DATABASE fbf;

# Cấu hình .env file

# Chạy application
./mvnw spring-boot:run
```

Server sẽ chạy tại: `http://localhost:3636`

## API Endpoints

### Single Field Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/single-fields` | Tạo sân đơn mới |
| GET | `/api/single-fields` | Lấy danh sách tất cả sân |
| GET | `/api/single-fields/{id}` | Lấy thông tin chi tiết sân |
| GET | `/api/single-fields/facility/{facilityId}` | Lấy sân theo cơ sở |
| GET | `/api/single-fields/status/{status}` | Lấy sân theo trạng thái |
| PUT | `/api/single-fields/{id}` | Cập nhật thông tin sân |
| DELETE | `/api/single-fields/{id}` | Xóa sân |

## Features

- ✅ Quản lý sân đơn (Single Field CRUD)
- 🚧 Quản lý sân kết hợp (Combined Field) - Coming soon
- 🚧 Quản lý đặt sân (Booking) - Coming soon
- 🚧 Quản lý người dùng (User) - Coming soon
- 🚧 Quản lý dịch vụ (Service) - Coming soon
