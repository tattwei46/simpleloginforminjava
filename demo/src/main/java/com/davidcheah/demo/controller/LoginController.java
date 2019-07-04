package com.davidcheah.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.davidcheah.demo.model.User;
import com.davidcheah.demo.service.UserService;

@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService service;

	@GetMapping("/")
	public String handleAllRequest() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showLoginPage(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null) {
			return "welcome";
		}
		return "login";
	}

	@PostMapping("/login")
	public String handleLogin(ModelMap model, @RequestParam String username, @RequestParam String password,
			HttpServletRequest request) {
		logger.info("[handleLogin] username: " + username + " and password: " + password);
		// Get user from database
		User foundUser = service.validateUser(username, password);
		if (foundUser != null) {

			// If user found, create new session
			HttpSession session = request.getSession();
			session.setAttribute("username", foundUser.getName());
			session.setAttribute("roles", foundUser.getRoleList());
			return "welcome";
		}
		model.put("errorMessage", "Invalid credentials");
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();

		return "redirect:/";
	}
}
