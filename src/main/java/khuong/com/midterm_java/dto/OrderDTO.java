package khuong.com.midterm_java.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    public Long id;
    public Long userId;
    public LocalDateTime orderDate;
    public String deliveryAddress;
    public String status;
    public List<OrderItemDTO> items;
}
