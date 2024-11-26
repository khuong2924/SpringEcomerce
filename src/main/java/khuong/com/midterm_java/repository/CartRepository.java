package khuong.com.midterm_java.repository;

import khuong.com.midterm_java.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);


}
