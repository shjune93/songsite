package net.songsite.web;


import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	private java.util.List<User> users=new ArrayList<>();
	
	
	@PostMapping("/create")
	public String create(User user) {
		System.out.println("user"+user);
		users.add(user);
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("users",users);
		return "list";
	}
}
