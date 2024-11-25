package khuong.com.midterm_java.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import khuong.com.midterm_java.entity.Role;
import khuong.com.midterm_java.entity.User;
import khuong.com.midterm_java.repository.RoleRepository;
import khuong.com.midterm_java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApplicationConfig {

  // Bean cho Cloudinary
  @Value("${cloudinary.cloud-name}")
  private String cloudName;

  @Value("${cloudinary.api-key}")
  private String apiKey;

  @Value("${cloudinary.api-secret}")
  private String apiSecret;

  @Bean
  public Cloudinary cloudinary() {
    return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret));
  }

  // Seed dữ liệu database
  @Bean
  public ApplicationRunner initDatabase(RoleRepository roleRepository, UserRepository userRepository) {
    return args -> {
      if (roleRepository.count() == 0) {
        roleRepository.saveAll(List.of(new Role("ROLE_ADMIN"), new Role("ROLE_USER")));

        User user = new User("Admin", null, "admin@gmail.com", "123");

        user.setRoles(roleRepository.findAll());
        userRepository.save(user);
      }
    };
  }
}
