package khuong.com.midterm_java.repository;

import khuong.com.midterm_java.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
