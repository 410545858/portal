package com.frank.startup.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.frank.startup.portal.common.Constant;
import com.frank.startup.portal.util.VerifyCodeUtils;

/**
 * @ClassName:     CaptchaImageCreateControlle.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2014-07-15 12:48:48 
 */
@Controller
@RequestMapping("/checkcode")
public class CaptchaImageCreateControlle extends BaseController {
/**
	private Producer captchaProducer = null;

	@Autowired
	public void setCaptchaProducer(Producer captchaProducer) {
		this.captchaProducer = captchaProducer;
	}
*/
	@RequestMapping(value = { "/comparecode" }, method = RequestMethod.GET)
	@ResponseBody
	public Boolean compareCode(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(required = true) String code) {
		String code2 =  (String)request.getSession().getAttribute("");
		if(code2 == null || code2.equals("")){
			return false;
		}else if(code2.equals(code)){
			return true;
		}
		return false;
	}
	@RequestMapping("/captcha-image")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the text for the image
/**     ---更换验证码显示图--- 
		String capText = captchaProducer.createText();
		// store the text in the session
		request.getSession().setAttribute(Constant.KEY_KAPTCHA, capText);
		// create the image with the text
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
*/
		
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		request.getSession().setAttribute(Constant.KEY_KAPTCHA, verifyCode);
		request.getSession().setAttribute("rand", verifyCode.toLowerCase()); 
		int w = 150, h = 60;  
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        
		return null;
	}
}
