package com.ltk.jblog.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ltk.jblog.service.BlogService;
import com.ltk.jblog.service.UserService;
import com.ltk.jblog.vo.UserVo;

@Controller
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/usr/join", method = RequestMethod.GET)
	public String join() {
		return "users/joinform";
	}

	@RequestMapping(value = "/usr/join", method = RequestMethod.POST)
	public String joinAction(@ModelAttribute UserVo userVo) {
		logger.debug("Form 전송된 데이터:" + userVo);

		boolean success = userService.join(userVo);

		if (success) {
			logger.debug("가입 성공!");
			userVo = userService.getUser(userVo.getId());
			blogService.join(userVo);
			return "redirect:/user/join";
		} else {
			logger.error("가입 실패!");
			return "redirect:/users/";
		}
	}

	@RequestMapping("/user/join")
	public String joinSuccess() {
		return "users/joinsuccess";
	}

	@RequestMapping("/users/idcheck")
	@ResponseBody
	public Object existId(@RequestParam(value = "id", required = false, defaultValue = "") String id) {
		logger.debug(id);
		UserVo vo = userService.getUser(id);
		boolean exists = vo != null ? true : false;

		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
		map.put("data", exists);
		return map;
	}

	@RequestMapping("/users/logincheck")
	@ResponseBody
	public Object existIdPassword(@RequestParam(value = "id", required = false, defaultValue = "") String id,
			@RequestParam(value = "password", required = false, defaultValue = "") String passoword) {
		logger.debug("passowrd: " + passoword);
		UserVo vo = userService.getUser(id, passoword);
		boolean exists = vo != null ? true : false;

		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
		map.put("data", exists);
		return map;
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String loginForm() {
		return "users/loginform";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public String loginAction(@RequestParam String id, @RequestParam String password, HttpSession session) {
		UserVo authUser = userService.getUser(id, password);

		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			session.setAttribute("userName", authUser.getUserName());
			session.setAttribute("login", true);
			return "redirect:/";
		}
		session.setAttribute("login", false);
		return "redirect:/user/login";
	}

	@RequestMapping(value = "/user/logout")
	public String logoutAction(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
}