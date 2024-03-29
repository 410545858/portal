package com.frank.startup.portal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.frank.startup.portal.common.Constant;
import com.frank.startup.portal.common.MessageConstant;
import com.frank.startup.portal.dto.LoginBean;
import com.frank.startup.portal.entity.User;
import com.frank.startup.portal.search.elastic.repository.SearchUserEntity;
import com.frank.startup.portal.service.BaseSearchService;
import com.frank.startup.portal.service.UserService;
import com.frank.startup.portal.util.UUIDGenerator;

/**
 * @ClassName: IndexController.java
 * @Description: TODO
 * @author dell
 * @version V1.0
 * @Date 2014年7月11日 下午5:38:23
 */
@Controller
public class IndexController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private BaseSearchService searchUserService;
	
	@Autowired
	@Qualifier("consoleMongoTemplate")
	private MongoTemplate mongoTemplate;
	
	@RequestMapping("/")
	public String start(HttpServletRequest request) {
//		Session session = new Session();
//		session.setUserid(12345656);
//		// modify and update with save()
//		session.setSessionid(SessionIdGenerator.getInstance().generateSessionId());
//		session.setDate(new Date());
//		mongoTemplate.save(session);
		return "redirect:index";
	}
	

	@SuppressWarnings("unchecked")
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,HttpSession session) {
		SearchUserEntity entry = new SearchUserEntity();
		entry.setId("120cb0cb2e4343f9a2d1cf9f49acab0c");
		entry.setName("中国江苏省苏州市博物馆12123");
//		searchUserService.delete(entry);
//		searchUserService.search(null);
		searchUserService.index(entry);
//		System.out.println(session.getId());
//		session.setAttribute("asdfasdfasf", "wadfasdfadfasdfong");
//		System.out.println(session.getAttribute("asdfasdfasf"));
		//session.invalidate();
//		SessionInfo sessionInfo = (SessionInfo)session.getAttribute("sessionInfo");
//		System.out.println(sessionInfo.getNickName());
	
//		ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
//		valueOper.set("loginname", "1asdf");
//		String key = "user-test";
//		redisTemplate.opsForHash().put(key, "id", UUID.randomUUID().toString());
//		redisTemplate.opsForHash().put(key, "firstName", "frank");
//		redisTemplate.opsForHash().put(key, "lastName", "wong");
//		redisTemplate.opsForSet().add("user", key);
		ModelAndView mav = new ModelAndView("index");
		
		return mav;
	}

	/**
	 * 
	 * @Title: goLoginPage
	 * @Description: TODO
	 * @param: shopId
	 * @param: days 7,15,30,60
	 * @param: request
	 * @param: @return
	 * @return: ModelAndView
	 * @throws
	 * @author FrankWong
	 * @Date 2014年8月4日 下午4:31:26
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView goLoginPage(String shopId, String days, HttpServletRequest request) {
		return null;
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute LoginBean loginBean) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("loginBean", loginBean);
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView doLogin(@ModelAttribute @Valid LoginBean loginBean, BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("login");
			loginBean.setPassword("");
			mav.addObject(loginBean);
			return mav;
		}

		String capText = (String) request.getSession().getAttribute(Constant.KEY_KAPTCHA);
		if (!capText.equalsIgnoreCase(loginBean.getValidCode())) {
			ModelAndView mav = new ModelAndView("login");
			loginBean.setPassword("");
			result.rejectValue("validCode", MessageConstant.ERROR_CAPTCHA, MessageConstant.ERROR_CAPTCHA);
			mav.addObject("loginResult", "验证码错误");
			mav.addObject(loginBean);
			return mav;
		}

		User user = (User) userService.login(loginBean.getPhone(), loginBean.getPassword());
		if (null == user) {
			ModelAndView mav = new ModelAndView("login");
			loginBean.setPassword("");
			mav.addObject("loginResult", "用户名或密码错误");
			mav.addObject("loginBean", loginBean);
			result.rejectValue("validCode", MessageConstant.ERROR_LOGIN_NAME_PASSWORD_WRONG, MessageConstant.ERROR_LOGIN_NAME_PASSWORD_WRONG);
			return mav;
		}
		
		super.getSessionInfo(request.getSession()).setByUser(user);
		ModelAndView mav = new ModelAndView();
		mav.addObject("loginBean", loginBean);
		mav.addObject("loginResult", "登录成功");
		mav.setViewName("redirect:dashboard");
		return mav;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		super.clearSession(request);
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/forget", method = RequestMethod.GET)
	public String gorget() {
		return "forget";
	}

	@ResponseBody
	@RequestMapping(value = "/update-pwd", method = RequestMethod.POST)
	public String updatePwd(
			@RequestParam(required = true) String pwd, 
			@RequestParam(required = true) String phoneOrEmail,
			@RequestParam(required = true) String code
	) throws IOException {
		return "false";
	}
	
	@ResponseBody
	@RequestMapping(value = "/chk-phone", method = RequestMethod.POST)
	public String chkPhone(@RequestParam(required = true) String phone) throws IOException {
		return  "";
	}

	@ResponseBody
	@RequestMapping(value = "/send-sms", method = RequestMethod.POST)
	public String sendsms(@RequestParam(required = true) String phone, @RequestParam(required = true) char type) throws IOException {
		return "";
	}

	@ResponseBody
	@RequestMapping(value = "/chk-sms", method = RequestMethod.POST)
	public String chksms(@RequestParam(required = true) String phone, @RequestParam(required = true) String code) throws IOException {
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/chk-email", method = RequestMethod.POST)
	public String chkEmail(@RequestParam(required = true) String email) throws IOException {
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/send-email", method = RequestMethod.POST)
	public String sendEmail(@RequestParam(required = true) String email, @RequestParam(required = true) char type) throws IOException {
		return  "";
	}
}
