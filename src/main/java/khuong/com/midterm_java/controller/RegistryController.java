package khuong.com.midterm_java.controller;


import khuong.com.midterm_java.entity.User;
import khuong.com.midterm_java.repository.UserRepository;
import khuong.com.midterm_java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class RegistryController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registry")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        ResponseEntity<String> response = null;

        try {
            User savedUser = userService.createUser(user);
            if (savedUser.getId() > 0) {
                response = ResponseEntity.status(HttpStatus.CREATED)
                        .body("User is created successfully for " + savedUser.getUsername());

            }
        }
        catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
        return response;
    }


}
