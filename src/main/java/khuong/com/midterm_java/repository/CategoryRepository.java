package khuong.com.midterm_java.repository;

import khuong.com.midterm_java.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
