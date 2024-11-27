# Chọn image base Java
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc trong container (nên sử dụng thư mục riêng cho dễ quản lý)
#WORKDIR /app

# Copy file jar vào container (đảm bảo tên file jar chính xác)
COPY target/MidTerm_Java-0.0.1-SNAPSHOT.jar /app/MidTerm_Java-0.0.1-SNAPSHOT.jar

# Expose port 8080
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "/app/MidTerm_Java-0.0.1-SNAPSHOT.jar"]
