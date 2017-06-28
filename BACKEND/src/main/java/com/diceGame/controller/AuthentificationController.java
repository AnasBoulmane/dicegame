package com.diceGame.controller;

import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Authentification")
public class AuthentificationController {

	public String authentification(String name) {
		if (name.trim().length() != 0 && name.trim().length() <= 255 && Pattern.matches("[a-zA-Z\\d]*", name.trim())) {
			return "Success";
		} else {
			return "Le pseudo ne peut pas être vide et ne peut pas comporter de caractères spéciaux.";
		}

	}

}
