package vn.myproject.jobhunter.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.myproject.jobhunter.domain.User;
import vn.myproject.jobhunter.repository.UserRepository;
import vn.myproject.jobhunter.util.error.IdInvalidException;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User handleSaveUser(User user) {
        User createUser = new User();
        createUser.setEmail(user.getEmail());
        createUser.setName(user.getName());
        createUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(createUser);
    }

    public User handleUpdateUser(User user) {
        User updateUser = this.getUser(user.getId());

        if (updateUser != null) {
            updateUser.setName(user.getName());
            updateUser.setEmail(user.getEmail());
            updateUser = this.userRepository.save(updateUser);
        }

        return updateUser;
    }

    public void deleteUser(Long id) {
        // User user = getUser(id);

        if (!userRepository.existsById(id)) {
            throw new IdInvalidException("id không tồn tại !!!");
        }

        this.userRepository.deleteById(id);
    }

    public User getUser(Long id) {
        // Optional<User> userOptional = this.userRepository.findById(id);
        // if (userOptional.isPresent()) {
        // return userOptional.get();
        // }
        // return null;

        // best practice from chat gpt
        return userRepository.findById(id).orElseThrow(() -> new IdInvalidException("id không tồn tại"));
    }

    public User handleGetUserByUsername(String username) {
        return this.userRepository.findByEmail(username);
    }
}
