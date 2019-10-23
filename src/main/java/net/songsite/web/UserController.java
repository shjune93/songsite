package net.songsite.web;


import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

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
@RequestMapping("users")
public class UserController {
	private java.util.List<User> users=new ArrayList<>();
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("loginForm")
	public String loginForm() {
		return "user/login";
	}
	
	
	
	@GetMapping("form")
	public String form(){
		return "user/form";
	}
	
	@PostMapping("")
	public String create(User user) {
		System.out.println("user"+user);
		//users.add(user);
		userRepository.save(user);
		return "redirect:users";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users",userRepository.findAll());
		return "user/list";
	}
	
	@PostMapping("login")
	public String login(String userId,	String password, HttpSession session) {
		User user=userRepository.findByUserId(userId);
		if (user == null) {
			System.out.println("Login Failue!");
			return "redirect:/users/loginForm";
			
		}
		if(!user.matchPassword(password)) {

			System.out.println("Login Failue!");
			return "redirect:/users/loginForm";
		}
		
		System.out.println("Login Success!");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		
		return "redirect:/";
	}
	
	
	@GetMapping("{id}/form") // 밑에 가 @PathVariable Long id 주소의 id
	public String updateForm(@PathVariable Long id,Model model, HttpSession session){
		Object tempUser=session.getAttribute("sessionedUser");
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser =HttpSessionUtils.getUserFromSession(session);
		if(!id.equals(sessionedUser.getId())) {
			throw new IllegalStateException("You can't update another user");
			
		}
		
		User user=userRepository.findById(id).get();
		model.addAttribute("user",user);
		return "user/updateForm";
	}
	
	@PostMapping("{id}")
	public String update(@PathVariable Long id,User updatedUser,HttpSession session) {
		Object tempUser=session.getAttribute("sessionedUser");
		if(tempUser ==null) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser =(User)tempUser;
		if(sessionedUser.matchId(id)) {
			throw new IllegalStateException(" You can't update another user");
			
		}
		
		
		User user=userRepository.findByUserId(sessionedUser.getUserId());
		user.update(updatedUser);
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		return "redirect:/";
	}
	
	
}
