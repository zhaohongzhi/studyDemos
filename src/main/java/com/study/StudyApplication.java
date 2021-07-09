package com.study;

import org.minbox.framework.api.boot.autoconfigure.swagger.annotation.EnableApiBootSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableApiBootSwagger
@SpringBootApplication
@EnableConfigurationProperties
public class StudyApplication {





	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}

}
