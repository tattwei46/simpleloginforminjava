package com.davidcheah.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.davidcheah.demo.model.User;
import com.davidcheah.demo.service.PasswordService;
import com.davidcheah.demo.service.UserService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordService pwService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//populateDb();
		//localeTest();
	}

	private void populateDb() {
		User user1 = new User("user1", "user1", "USER");
		User user2 = new User("user2", "user2", "USER,MANAGER");
		User user3 = new User("user3", "user3", "MANAGER");

		saveUser(user1);
		saveUser(user2);
		saveUser(user3);
	}

	private void saveUser(User user) {
		String password = user.getPassword();
		String hashedPassword = pwService.encodes(password);
		user.setPassword(hashedPassword);

		logger.info("Inserting" + user.getId() + " -> {}", userService.insert(user));

		// validateUser(user, password);
	}
	
//	private void localeTest() {
//		String lang = "en";
//		String country = "US";
//		
//		Locale locale = new Locale(lang, country);
//		
//		ResourceBundle r = ResourceBundle.getBundle("message", locale);
//		
//		String str = r.getString("hello");
//		System.out.println(str);
//		
//	}

//	private void validateUser(User user, String password) {
//		if (pwService.matches(password, user.getPassword())) {
//			System.out.println("Match password");
//		}
//	}

}
