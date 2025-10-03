package com.fastcampus;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class PassBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassBatchApplication.class, args);
	}

	class Solution {
		public boolean solution(String s, String t) {
			for(int i=0; i<s.length(); i++){
				String u = s.substring(0, i) + s.substring(i+1);
				String v = t.substring(0, i) + t.substring(i+1);
				if(u.equals(v))
					return true;
			}

			return false;
		}
	}
}
