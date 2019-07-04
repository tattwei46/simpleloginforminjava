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
import com.davidcheah.demo.service.LocaleService;
import com.davidcheah.demo.service.UserService;

@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@Autowired
	LocaleService localeService;

	@GetMapping("/")
	public String handleAllRequest() {
		return "redirect:/login?lang=en";
	}

	@GetMapping("/login")
	public String showLoginPage(ModelMap model, HttpServletRequest request, @RequestParam(name = "lang") String lang) {
		if (lang != null) {
			System.out.println("setting locale");
			localeService.setLocale(lang);
		}

		model = getTranslatedModel(model);

		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null) {
			System.out.println("go to welcome");

			return "welcome";
		}
		return "login";
	}

	@PostMapping("/login")
	public String handleLogin(ModelMap model, @RequestParam String username, @RequestParam String password,
			HttpServletRequest request) {

		model = getTranslatedModel(model);

		logger.info("[handleLogin] username: " + username + " and password: " + password);
		// Get user from database
		User foundUser = userService.validateUser(username, password);
		if (foundUser != null) {

			// If user found, create new session
			HttpSession session = request.getSession();
			session.setAttribute("username", foundUser.getName());
			session.setAttribute("roles", foundUser.getRoleList());

			return "welcome";
		}
		model.put("errorMessage", localeService.getTranslatedString("login_invalid"));

		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();

		return "redirect:/";
	}

	private ModelMap getTranslatedModel(ModelMap model) {
		model.put("welcome_name", localeService.getTranslatedString("welcome_name"));
		model.put("welcome_role", localeService.getTranslatedString("welcome_role"));
		model.put("welcome_logout", localeService.getTranslatedString("welcome_logout"));

		model.put("login_signin", localeService.getTranslatedString("login_signin"));
		model.put("login_username", localeService.getTranslatedString("login_username"));
		model.put("login_password", localeService.getTranslatedString("login_password"));
		model.put("login_submit", localeService.getTranslatedString("login_submit"));

		return model;

	}
}
