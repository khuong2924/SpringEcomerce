package khuong.com.midterm_java.repository;

import khuong.com.midterm_java.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
