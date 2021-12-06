package com.itacademy;

import com.itacademy.entity.ExpertPersonEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class SpaceApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpaceApplication.class, args);
		ExpertPersonEntity entity = new ExpertPersonEntity(15);
		System.out.println(entity);
		var num = 0;
	}




}
