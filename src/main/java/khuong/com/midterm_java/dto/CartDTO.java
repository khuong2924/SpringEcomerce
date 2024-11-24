package khuong.com.midterm_java.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    public Long id;
    public Long userId;
    public List<CartItemDTO> items;
}

