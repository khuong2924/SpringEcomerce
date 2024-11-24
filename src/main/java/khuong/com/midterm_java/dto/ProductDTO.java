package khuong.com.midterm_java.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
  public Long id;
  public String name;
  public Long categoryId;
  public double price;
  public double weight;
  public String brand;
  public String color;
  public String description;
  public String imageProduct;
}
