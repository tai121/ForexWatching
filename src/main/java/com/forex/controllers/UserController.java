package com.forex.controllers;


import com.forex.entities.User;
import com.forex.services.RoleService;
import com.forex.services.UserService;
import com.forex.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/users")
@ComponentScan("com.forex")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public String viewAllUser(Model model) {
		List<User> listUser = userService.listAll();
		model.addAttribute("users", listUser);
		return "user/index";
	}

	@GetMapping("/new")
	public String showNewUserPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("roles", roleService.listAll());
		return "user/new_user";
	}

	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User user, @RequestParam("image") MultipartFile multipartFile)
			throws IOException {

		if (user.getPassword().isEmpty()) {
			user.setPassword(userService.get(user.getId()).getPassword());
		}else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		if (!multipartFile.isEmpty()) {
			user.setphotourl(fileName);
		}else {
			user.setphotourl(userService.get(user.getId()).getphotourl());
		}
		user.setEnabled(true);
		User savedUser = userService.save(user);
		if (!multipartFile.getOriginalFilename().isBlank()) {
			String uploadDir = "photos/" + savedUser.getId();
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}	

		return "redirect:/users";
	}

	@GetMapping("/edit/{id}")
	public String showEditUserPage(@PathVariable("id") Long id, Model model) {
		User user = userService.get(id);

		if (user == null) {
			model.addAttribute("message","user not found");
			return "message";

		} else {
			model.addAttribute("roles", roleService.listAll());
			model.addAttribute("user", user);
			return "user/edit";
		}
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id,Model model) {
		User user = userService.get(id);
		if (user == null) {
			model.addAttribute("message","user not found");
			return "message";
		} else {
			userService.delete(id);
			return "redirect:/users";
		}
	}

}
