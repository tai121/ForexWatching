package com.forex.services;

import com.forex.entities.User;
import com.forex.exceptions.UserNotFoundException;
import com.forex.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User get(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void delete(long id) {
        User user = get(id);
        user.setIsdeleted(true);
        userRepository.save(user);
    }

    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if (user != null) {
            user.settokenforgotpassword(token);
            user.setTimeexpired(null);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("khong ton tai user co email " + email+" hoac tai khoan chua duoc kich hoat");
        }
    }

    public User getUserByTokenforgotpassWord(String token) {
        return userRepository.getUserBytokenforgotpassword(token);
    }

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.settokenforgotpassword(null);
        userRepository.save(user);
    }
    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);

            return true;
        }

    }
}
