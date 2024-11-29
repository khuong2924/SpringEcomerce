## Nguyên tắc, Mô hình và Thực tiễn Phát triển trong trang web của tôi

## 1. Nguyên tắc
- **Separation of Concerns (SoC)**: Tách riêng các phần của ứng dụng theo từng chức năng hoặc vai trò cụ thể (giao diện người dùng, logic xử lý, truy cập dữ liệu).
- **Single Responsibility Principle (SRP)**: Mỗi lớp hoặc module chỉ nên có một trách nhiệm cụ thể, giúp mã dễ hiểu và dễ thay đổi.

## 2. Mô hình
- **Mô hình MVC**: Tách biệt rõ ràng giữa dữ liệu (**Model**), giao diện (**View**), và xử lý yêu cầu (**Controller**) trong ứng dụng.

## 3. Thực tiễn phát triển
- **Quản lý phiên bản với Git**: Sử dụng Git để theo dõi và kiểm soát mã nguồn, đảm bảo làm việc nhóm hiệu quả và an toàn.
- **Bảo mật**:
    - Sử dụng **Session** để xác thực người dùng khi đăng nhập.
    - Lưu trữ thông tin đăng nhập trong session và kiểm tra session khi người dùng cố gắng truy cập các trang bảo vệ.
- **Kiến trúc phân tầng**:
    - Tách biệt các phần:
        - **Giao diện (Frontend)**: Sử dụng HTML, JavaScript và Tailwind CSS.
        - **Logic nghiệp vụ (Service)**: Xử lý các quy tắc nghiệp vụ.
        - **Truy cập dữ liệu (Data Access)**: Giao tiếp với cơ sở dữ liệu.
- **Responsive Design**: Sử dụng **Tailwind CSS** để thiết kế giao diện tương thích trên nhiều loại thiết bị, từ máy tính đến điện thoại di động.



### Cấu trúc mã trong project này:


1. **Backend**:
    - **Controller**: Chịu trách nhiệm xử lý các yêu cầu từ người dùng và trả về phản hồi (thường là giao diện hoặc dữ liệu JSON).
    - **DTO (Data Transfer Object)**: Đóng vai trò trung gian để truyền dữ liệu giữa các lớp, giúp giảm phụ thuộc trực tiếp vào các entity.
    - **Entity**: Đại diện cho các bảng trong cơ sở dữ liệu, chứa các thuộc tính và ánh xạ với bảng dữ liệu.
    - **Repository**: Tương tác trực tiếp với cơ sở dữ liệu bằng cách sử dụng JPA để thực hiện các truy vấn.
    - **Service**: Chứa logic nghiệp vụ, là cầu nối giữa Controller và Repository.
    - **Config**:
        - **CloudinaryConfig**: Cấu hình để tích hợp và sử dụng dịch vụ Cloudinary (lưu trữ và quản lý tệp hình ảnh).
        - **SessionUtils**: Tiện ích hỗ trợ quản lý và xử lý thông tin session, như kiểm tra trạng thái đăng nhập hoặc lưu thông tin người dùng.

2. **Frontend**:
    - Giao diện được phát triển bằng **HTML**, **JavaScript**, và **Tailwind CSS**, đặt trong thư mục `resources/templates`.

3. **Cơ sở dữ liệu**:
    - Sử dụng tệp `data.sql` trong thư mục `resources` để chứa dữ liệu mẫu để có thể insert vào mySQL Workbence.

Cấu trúc này giúp tách biệt rõ ràng các thành phần trong ứng dụng, đảm bảo dễ dàng mở rộng và bảo trì.

4. **Triển khai bằng Docker**:
    - **Dockerfile**: Định nghĩa môi trường cần thiết để chạy ứng dụng Spring Boot, bao gồm các bước như copy mã nguồn, build ứng dụng, và chạy server.
    - **docker-compose.yml**: Quản lý cấu hình Spring Boot và mySQL.


# Full CURL commands:

#### 1. **Products API**
- **POST /products/upload**: Tạo mới một sản phẩm.
- **GET /products**: Lấy danh sách tất cả sản phẩm.
- **GET /products/{id}**: Lấy thông tin chi tiết của sản phẩm dựa trên id.
- **PUT /products/{id}**: Cập nhật thông tin sản phẩm dựa trên id.
- **DELETE /products/{id}**: Xóa sản phẩm dựa trên id.

#### 2. **Carts API**
- **POST /carts**: Tạo mới một giỏ hàng.
- **GET /carts**: Lấy danh sách tất cả giỏ hàng.
- **GET /carts/{id}**: Lấy thông tin chi tiết của giỏ hàng dựa trên `id`.
- **PUT /carts/{id}**: Cập nhật thông tin giỏ hàng dựa trên `id`.
- **DELETE /carts/{id}**: Xóa giỏ hàng dựa trên `id`.

#### 3. **CartItems API**
- **POST /cartItems**: Thêm sản phẩm vào giỏ hàng.
- **GET /cartItems**: Lấy danh sách tất cả sản phẩm trong giỏ hàng.
- **GET /cartItems/{id}**: Lấy thông tin chi tiết của sản phẩm trong giỏ hàng dựa trên `id`.
- **PUT /cartItems/{id}**: Cập nhật thông tin sản phẩm trong giỏ hàng dựa trên `id`.
- **DELETE /cartItems/{id}**: Xóa sản phẩm khỏi giỏ hàng dựa trên `id`.

#### 4. **Categories API**
- **POST /categories**: Tạo mới một danh mục sản phẩm.
- **GET /categories**: Lấy danh sách tất cả danh mục sản phẩm.
- **GET /categories/{id}**: Lấy thông tin chi tiết của danh mục dựa trên `id`.
- **PUT /categories/{id}**: Cập nhật thông tin danh mục dựa trên `id`.
- **DELETE /categories/{id}**: Xóa danh mục dựa trên `id`.

#### 5. **Orders API**
- **POST /orders**: Tạo mới một đơn hàng.
- **GET /orders**: Lấy danh sách tất cả đơn hàng.
- **GET /orders/{id}**: Lấy thông tin chi tiết của đơn hàng dựa trên `id`.
- **PUT /orders/{id}**: Cập nhật thông tin đơn hàng dựa trên `id`.
- **DELETE /orders/{id}**: Xóa đơn hàng dựa trên `id`.

#### 6. **OrderItems API**
- **POST /orderItems**: Thêm sản phẩm vào đơn hàng.
- **GET /orderItems**: Lấy danh sách tất cả sản phẩm trong các đơn hàng.
- **GET /orderItems/{id}**: Lấy thông tin chi tiết của sản phẩm trong đơn hàng dựa trên `id`.
- **PUT /orderItems/{id}**: Cập nhật thông tin sản phẩm trong đơn hàng dựa trên `id`.
- **DELETE /orderItems/{id}**: Xóa sản phẩm khỏi đơn hàng dựa trên `id`.

#### 7. **Users API**
- **POST /users**: Tạo mới một người dùng.
- **GET /users**: Lấy danh sách tất cả người dùng.
- **GET /users/{id}**: Lấy thông tin chi tiết của người dùng dựa trên `id`.
- **PUT /users/{id}**: Cập nhật thông tin người dùng dựa trên `id`.
- **DELETE /users/{id}**: Xóa người dùng dựa trên `id`.

### Lưu ý

### Lưu ý khi test API cho Cart và CartItems

- **Cart API** và **CartItems API** phụ thuộc vào thông tin người dùng đăng nhập, được lưu trong **SessionUtils** sau khi người dùng đăng nhập thành công.
- Để test các API này:
    1. **Run Project**: Khởi chạy ứng dụng để kích hoạt hệ thống quản lý session.
    2. **Đăng nhập qua giao diện**: Sử dụng giao diện đăng nhập để tạo session hợp lệ và lưu thông tin người dùng.
    3. **Test trên giao diện hoặc công cụ test API**: Sau khi đăng nhập thành công thì có thể sử dụng giao diện hoặc công cụ như Postman để gửi các yêu cầu tới Cart API và CartItems API.

- Nếu không đăng nhập hoặc không có session hợp lệ, các API liên quan đến Cart và CartItems sẽ trả về lỗi xác thực (ví dụ: **401 Unauthorized**).


## Security (Spring Security) for the application:

#### Sử dụng HttpSession để quản lý trạng thái người dùng
- Khi người dùng đăng nhập thành công, thông tin người dùng sẽ được lưu vào session để duy trì trạng thái đăng nhập trong suốt phiên làm việc.
- Trước khi cho phép truy cập trang giỏ hàng, hệ thống kiểm tra xem session có chứa thông tin người dùng không. Nếu có, người dùng đã đăng nhập và có thể truy cập; nếu không, họ sẽ bị chuyển hướng đến trang đăng nhập.

