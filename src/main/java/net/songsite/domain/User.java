package net.songsite.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable=false, length=20 ,unique=true)
	private String userId;
	private String password;
	private String name;
	private String email;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void update(User newUser) {
		// TODO Auto-generated method stub
		this.password=newUser.password;
		this.name=newUser.name;
		this.email=newUser.email;
		
	}
	
	//아이디 일치여부확인
	public boolean matchId(Long newId) {
		if(newId==null) {
			return false;
		}
		return newId.equals(password);
	}
	
	//비밀번호 일치여부 확인
	public boolean matchPassword(String newPassword) {
		if(newPassword==null) {
			return false;
		}
		return newPassword.equals(password);
	}
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	

}
