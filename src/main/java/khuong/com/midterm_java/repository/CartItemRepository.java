package khuong.com.midterm_java.repository;

import khuong.com.midterm_java.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
