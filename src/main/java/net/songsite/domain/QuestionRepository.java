package net.songsite.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long>{//스프링프레임웍이 구현체 생성,인스턴스 까지 만들어서 관리
	
}
