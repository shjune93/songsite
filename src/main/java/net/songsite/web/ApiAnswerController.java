package net.songsite.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.songsite.domain.Answer;
import net.songsite.domain.AnswerRepository;
import net.songsite.domain.Question;
import net.songsite.domain.QuestionRepository;
import net.songsite.domain.User;

@RestController
@RequestMapping("api/questions/{questionId}/answers")
public class ApiAnswerController {
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("")
	public Answer create(@PathVariable Long questionId,String contents,HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return null;
		}
		User loginUser=HttpSessionUtils.getUserFromSession(session);
		Question question=questionRepository.findById(questionId).get();
		Answer answer=new Answer(loginUser,question,contents);
		question.addAnswer();//답변숫자 늘려줌
		return answerRepository.save(answer);
		//return String.format("redirect:/questions/%d", questionId);
	}
	
	@DeleteMapping("{id}")
	public Result delete(@PathVariable Long questionId,@PathVariable Long id,HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("로그인히야 합니다.");
		}
		Answer answer=answerRepository.findById(id).get();
		User loginUser=HttpSessionUtils.getUserFromSession(session);
		if(!answer.isSameWriter(loginUser)) {
			return Result.fail("자신의 글만 삭제할 수 있습니다.");
		}
		
		answerRepository.deleteById(id);
		Question question=questionRepository.findById(questionId).get();
		question.deleteAnswer();//답변숫자 줄여줌
		questionRepository.save(question);
		return Result.ok();
		
	}
}
