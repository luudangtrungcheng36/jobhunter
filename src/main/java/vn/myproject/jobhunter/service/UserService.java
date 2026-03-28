package vn.myproject.jobhunter.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.myproject.jobhunter.domain.User;
import vn.myproject.jobhunter.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
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
        this.userRepository.deleteById(id);
    }

    public User getUser(Long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

}
