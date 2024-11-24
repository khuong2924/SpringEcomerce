package khuong.com.midterm_java.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    public Long id;
    public Long orderId;
    public Long productId;
    public Integer quantity;
}
