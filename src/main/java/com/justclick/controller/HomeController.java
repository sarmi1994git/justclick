package com.justclick.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.justclick.entity.User;
import com.justclick.service.UserService;

@Controller
@RequestMapping("/link")
public class HomeController {
	private Logger log = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private UserService userService;
	
	@GetMapping("/{username}")
	public String home(@PathVariable String username, Model model, @CookieValue(
						value = "currentValue", defaultValue = "0") Integer currentValue,
						HttpServletResponse response) {
		Cookie cookie;
		User user = userService.findByUsername(username);
		if(user != null) {
			// create cookie
			currentValue++;
			cookie = new Cookie("currentValue", Integer.toString(currentValue));
			log.info("Valor actual: " + currentValue);
			// add Cookie
			response.addCookie(cookie);
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
