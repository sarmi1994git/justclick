package com.justclick.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justclick.entity.User;
import com.justclick.service.LogService;
import com.justclick.service.UserService;

@Controller
@RequestMapping("/link")
public class HomeController {
	private Logger log = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private LogService logService;

	@GetMapping("/{username}")
	public String home(@PathVariable String username, Model model, @CookieValue(
			value = "currentValue", defaultValue = "0") Integer currentValue,
			@CookieValue(
					value = "userInfo", defaultValue = "") String userInfo,
			@RequestHeader HttpHeaders headers,
			HttpServletResponse response) {
		Cookie currentValueCookie;
		Cookie userCookie = null;
		User user = userService.findByUsername(username);
		// Guardar request en clicks.json
		logService.logRequest(headers);
		if(user != null) {
			// create currentValue cookie
			currentValue++;
			currentValueCookie = new Cookie("currentValue", Integer.toString(currentValue));
			log.info("Valor actual: " + currentValue);
			// add Cookie
			response.addCookie(currentValueCookie);
			// create userInfo cookie
			if(userInfo.isEmpty()) {
				userCookie = new Cookie("userInfo", user.getUsername());
				response.addCookie(userCookie);
			}
			if (currentValue < user.getMaxValue()) {
				model.addAttribute("user", user);
				return("index");
			} else {
				return("notfound");
			}

		} else {
			log.info("Usuario no existe");
			return("notfound");
		}

	}


}
