package co.jp.ths.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("co.jp.ths.api.dao")
@SpringBootApplication
public class PersonalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalApiApplication.class, args);
	}
}
