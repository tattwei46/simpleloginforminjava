package com.davidcheah.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.davidcheah.demo.dao.UserDao;
import com.davidcheah.demo.model.User;

@Service
public class UserService {
	@Autowired
	UserDao userDao;

	@Autowired
	PasswordService pwService;

	public User validateUser(String username, String password) {
		User user = userDao.findByUsername(username);
		System.out.println(user);
		if (user != null) {
			if (pwService.matches(password, user.getPassword())) {
				return user;
			}
		}
		return null;
	}

	public User findById(int id) {
		return userDao.findById(id);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public User insert(User user) {
		return userDao.insert(user);
	}

}
