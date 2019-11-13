package net.songsite.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonProperty getter/setter 의 이름을 property 와 다른 이름을 사용할 수 있도록 설정한다. Database 를 자바 클래스로 매핑하는데 DB의 컬럼명이 알기 어려울 경우등에 유용하게 사용할 수 있다.

@Entity
public class Question extends AbstractEntity{

	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_question_writer"))
	@JsonProperty
	private User writer;
	
	@JsonProperty
	private String title;
	
	@Lob
	@JsonProperty
	private String contents;

	@JsonProperty
	private Integer countOfAnswer = 0;
	
	
	
	@OneToMany(mappedBy="question")
	@OrderBy("id DESC")
	private List<Answer> answers;
	
	
	public Question() {}
	
	public Question(User writer, String title, String contents) {
		
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	
	}
	
	
	
	public void update(String title,String contents) {
		this.title=title;
		this.contents=contents;
		
	}

	public boolean isSameWriter(User loginUser) {
		
		return this.writer.equals(loginUser);
	}

	public void addAnswer() {
		this.countOfAnswer+=1;
		// TODO Auto-generated method stub
		
	}

	public void deleteAnswer() {
		this.countOfAnswer-=1;
	}
	

	
	
}
