package com.fdmgroup.ChatProject.bootapplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

public class BootApplication {
	@Component
	public class BootsStrapData implements CommandLineRunner {

		@Override
		public void run(String... args) throws Exception {
			System.out.println("Started in BootApplication");
			
		}
		
	}
	}

