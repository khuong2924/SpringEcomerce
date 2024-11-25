package khuong.com.midterm_java.config;

import khuong.com.midterm_java.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Autowired
  private final CustomUserDetailsService customUserDetailsService;
  @Autowired
  @Lazy
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final CustomLoginSuccessHandler loginSuccessHandler;

  // Cấu hình PasswordEncoder không mã hóa mật khẩu
  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance(); // Không mã hóa mật khẩu
  }

  // Cấu hình AuthenticationProvider
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(customUserDetailsService);
    provider.setPasswordEncoder(passwordEncoder()); // Sử dụng NoOpPasswordEncoder
    return provider;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests(configurer -> configurer
                    .requestMatchers("/", "/shop/**", "/register", "/checkout/**", "/denied")
                    .permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
//                    .requestMatchers("/cart").authenticated()
                    .anyRequest().permitAll())
            .formLogin(form -> form
                    .loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/home")
                    .failureUrl("/login?error=true")
                    .permitAll())
            .logout(logout -> logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login"))
            .exceptionHandling(configurer -> configurer.accessDeniedPage("/denied"))
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())  // Sử dụng AuthenticationProvider
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  public static String getLoggedInUsername() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      return ((UserDetails) principal).getUsername();
    }
    return null; // Nếu không có user đăng nhập
  }

}
