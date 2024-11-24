package khuong.com.midterm_java.service;

import khuong.com.midterm_java.dto.ProductDTO;
import khuong.com.midterm_java.entity.Product;
import khuong.com.midterm_java.repository.CategoryRepository;
import khuong.com.midterm_java.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductService {
  @Autowired
  private final ProductRepository productRepository;

  @Autowired
  private final CategoryRepository categoryRepository;

  public List<ProductDTO> mapToDto(List<Product> products) {
    List<ProductDTO> dtos = new ArrayList<>();
    for (Product product : products) {
      dtos.add(new ProductDTO(
          product.getId(),
              product.getName(),
              product.getCategory().getId(),
              product.getPrice(),
              product.getWeight(),
              product.getBrand(),
              product.getColor(),
              product.getDescription(),
              product.getImageProduct()
      ));
    }
    return dtos;
  }

  public List<ProductDTO> getAll() {
    List<Product> products = productRepository.findAll();
    return mapToDto(products);
  }

  public void create (ProductDTO productDTO) {
    Product product = new Product();
    product.setId(productDTO.getId());
    product.setName(productDTO.getName());
    product.setCategory(categoryRepository.findById(productDTO.getCategoryId()).orElse(null));
    product.setPrice(productDTO.getPrice());
    product.setWeight(productDTO.getWeight());
    product.setBrand(productDTO.getBrand());
    product.setColor(productDTO.getColor());
    product.setDescription(productDTO.getDescription());
    product.setImageProduct(productDTO.getImageProduct());
    productRepository.save(product);
  }

  public void update (Long id, ProductDTO productDTO) {
    Product product = productRepository.findById(id).orElse(null);
    product.setName(productDTO.getName());
    product.setCategory(categoryRepository.findById(productDTO.getCategoryId()).orElse(null));
    product.setPrice(productDTO.getPrice());
    product.setWeight(productDTO.getWeight());
    product.setBrand(productDTO.getBrand());
    product.setColor(productDTO.getColor());
    product.setDescription(productDTO.getDescription());
    product.setImageProduct(productDTO.getImageProduct());
    productRepository.save(product);
  }

  public void delete (Long id) {
    productRepository.deleteById(id);
  }

}








