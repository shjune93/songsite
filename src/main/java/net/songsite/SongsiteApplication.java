package net.songsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing//도메인 클래스 중복제거에 사용되는 코드
public class SongsiteApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SongsiteApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SongsiteApplication.class);
	}
	
	
	

}
