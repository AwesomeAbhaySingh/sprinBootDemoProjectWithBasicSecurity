package com.abhay.mvc.component.controller;

import java.security.Principal;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.abhay.demo.configrations.CustomAuthenticationProvider;
import com.abhay.demo.mvc.component.constant.WebPages;
import com.abhay.demo.mvc.component.validator.LoginValidator;
import com.abhay.demo.request.LoginRequest;

@Controller
@RequestMapping("/")
public class DefaultWebController {

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Autowired
	private LoginValidator loginValidator;

	
	
	@ModelAttribute("command")
	private LoginRequest getAttribute() {
		LoginRequest login_request = new LoginRequest();

		return login_request;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showHomePage(Model model, Principal principal, HttpServletRequest httpServletRequest) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.err.println("test@@@@");
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return WebPages.DOSHBOARD.getJsp_name();
		}

		return WebPages.INDEX.getJsp_name();
	}

	@PostMapping(params = "_login", value = { "/" })
	public ModelAndView loginPagePage(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") LoginRequest loginRequest, Errors errors) {

		ModelAndView model = new ModelAndView();

		loginValidator.validatesRequest(loginRequest, errors);
		if (errors.hasErrors()) {
			model.addObject("message", errors.getAllErrors().get(0).getDefaultMessage());
			// model.setViewName("");
			model.setViewName(WebPages.INDEX.getJsp_name());
			return model;
		}

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
				loginRequest.getPassword());
		token.setDetails(new WebAuthenticationDetails(request));

		Authentication authentication = customAuthenticationProvider.authenticate(token);

		if (Objects.isNull(authentication)) {

			model.addObject("message", "Invalid  Username/Password");
			model.setViewName(WebPages.INDEX.getJsp_name());

			return model;
		}

		//
		// Collection<GrantedAuthority> auth = new
		// ArrayList<GrantedAuthority>(authentication.getAuthorities());
		// String hasRole = null;
		// for (Iterator<GrantedAuthority> iter = auth.iterator(); iter.hasNext();) {
		// hasRole = iter.next().toString();
		// }
		//
		// if (hasRole.equalsIgnoreCase("USER")) {
		// System.out.println("role::" + hasRole);
		// SecurityContextHolder.getContext().setAuthentication(authentication);
		// model.setViewName("redirect:/" + WebPages.DASHBOARD.getJsp_name());
		//
		// return model;
		// } /dashboard
		model.addObject("login", true);
		model.setViewName(WebPages.INDEX.getJsp_name());
		return model;
	}

	@GetMapping("/logout")
	public String getLogoutPage(Model model, HttpServletRequest request, HttpServletResponse response) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);

		}
		return "redirect:/";
	}

}
