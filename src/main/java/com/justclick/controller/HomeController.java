package com.justclick.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String home(@PathVariable String username, Model model) {
		User user = userService.findByUsername(username);
		if(user != null) {
			model.addAttribute("user", user);
			return "index";
		} else {
			log.info("Usuario no existe");
			return("notfound");
		}
		
	}
	

}
