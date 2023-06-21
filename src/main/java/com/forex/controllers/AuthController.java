package com.forex.controllers;

import com.forex.exceptions.UserNotFoundException;
import com.forex.services.RoleService;
import com.forex.services.SendMailService;
import com.forex.services.UserService;
import com.forex.utils.Utilities;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.forex.entities.*;
import org.springframework.web.bind.annotation.PostMapping;
import static com.forex.utils.StringUtils.*;

import java.io.UnsupportedEncodingException;

@Controller
@ComponentScan("com.forex")
@ComponentScan("com.forex.utils")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SendMailService sendMailService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());

        return "auth/signup_form";
    }
    @PostMapping("/process_register")
    public String processRegister(User user, HttpServletRequest request,Model model) throws UnsupportedEncodingException, MessagingException {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.addRoles(roleService.getByName("USER"));
        user.setVerificationCode(generateRandomString(30));
        // user.setEnabled(true);
        userService.save(user);
        sendMailService.sendVerificationEmail(user, Utilities.getSiteURL(request));
        model.addAttribute("message","sign up successfully. check mail to enable ur acc.");
        return "message";
    }
    @GetMapping("/auth/me")
    public String findMe(Authentication authentication, Model model) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        if (user == null) {
            model.addAttribute("message","user not found");
            return "message";

        } else {
            model.addAttribute("roles", roleService.listAll());
            model.addAttribute("user", user);
            return "user/edit";
        }
    }

    @GetMapping("/login")
    public String Login(Model model) {
        return "auth/login";
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "auth/forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = generateRandomString(30);

        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utilities.getSiteURL(request) + "/reset_password?token=" + token;
            sendMailService.sendEmailForgotPassword(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }

        return "auth/forgot_password_form";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = userService.getUserByTokenforgotpassWord(token);
        model.addAttribute("token", token);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }

        return "auth/reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getUserByTokenforgotpassWord(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            userService.updatePassword(user, password);

            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "auth/reset_password_form";
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code, Model model) {
        if (userService.verify(code)) {
            model.addAttribute("message", "Congratulations, your account has been verified.");
        } else {
            model.addAttribute("error", "Sorry, we could not verify account. It maybe already verified,\n"
                    + "        or verification code is incorrect.");
        }
        return "message";
    }


}
