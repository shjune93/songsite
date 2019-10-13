package net.songsite.web;


import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.songsite.domain.User;
import net.songsite.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	private java.util.List<User> users=new ArrayList<>();
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/form")
	public String form(){
		return "/user/form";
	}
	
	@PostMapping("")
	public String create(User user) {
		System.out.println("user"+user);
		//users.add(user);
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users",userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("/{id}/form") // 밑에 id가 주소의 id
	public String updateForm(@PathVariable Long id,Model model){
		User user=userRepository.findById(id).get();
		model.addAttribute("user",user);
		return "/user/updateForm";
	}
	
	@PostMapping("/{id}")
	public String update(@PathVariable Long id,User newUser) {
		User user=userRepository.findById(id).get();
		user.update(newUser);
		userRepository.save(user);
		return "redirect:/users";
	}
	
	
}
