package khuong.com.midterm_java.repository;

import khuong.com.midterm_java.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
