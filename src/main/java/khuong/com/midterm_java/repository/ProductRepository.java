package khuong.com.midterm_java.repository;

import khuong.com.midterm_java.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findAllByCategoryId(int id);
}
