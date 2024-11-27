package khuong.com.midterm_java.service;

import khuong.com.midterm_java.dto.UserDTO;
import khuong.com.midterm_java.entity.Cart;
import khuong.com.midterm_java.entity.User;
import khuong.com.midterm_java.repository.CartRepository;
import khuong.com.midterm_java.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final CartRepository cartRepository;



    public UserDTO mapToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(mapToDto(user));
        }
        return userDTOs;
    }

    public UserDTO getByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(this::mapToDto).orElse(null);
    }

    public void create(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        // Mã hóa mật khẩu trước khi lưu
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
    }

    // Cập nhật người dùng
    public void update(Long id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            userRepository.save(user);
        }
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public boolean checkCredentials(String username, String password) {
        Optional<User> user = userRepository.findByEmail(username);
        return user.isPresent() && user.get().getPassword().equals(password);
    }


    public boolean validateUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User validateGetUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }


    @Transactional
    public User createUser(User user) {
        User savedUser = userRepository.save(user);

        // Tạo profile rỗng tương ứng với user
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
        return savedUser;
    }



}
