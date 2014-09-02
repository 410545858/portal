package com.frank.startup.portal.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.frank.startup.portal.alipay.config.AlipayConfig;
import com.frank.startup.portal.alipay.util.AlipayNotify;
import com.frank.startup.portal.alipay.util.AlipaySubmit;
import com.frank.startup.portal.entity.Order;
import com.frank.startup.portal.util.UUIDGenerator;

@Controller
@RequestMapping("/pay")
public class AlipayController extends BaseController {
	
	@RequestMapping(value = "/alipay", method = RequestMethod.POST)
	public ModelAndView alipayapi(
			HttpServletRequest request,
			Integer		orderId,
			Character	type,
			String		price,
			Integer		daysNum,
			Integer		packageId,
			String		incrementIds,
			String		subject,		// 订单名称
			String		body			// 订单描述
	) {
		ModelAndView mav = new ModelAndView("package/alipay");
		int userId = super.getCurretnUser(request).getId();
		Order order=null;
		if (orderId != null) { // 订单已存在
			if (order == null) {
				return mav;
			}
		} else { // 订单不存在
			
			String out_trade_no = UUIDGenerator.genOrderId(userId); // 26位商户订单号
			order = new Order();
			order.setOrderId(out_trade_no);
			order.setCreateTime(new Date());
			order.setCreator(userId + "");
			order.setDescription(subject);
			order.setNickName("user" + userId);
			order.setPrice(Double.valueOf(price));
			order.setStatus("0");
			order.setType(type);
			order.setUserId(userId);
			order.setUpdater("system");
		}

		// 把请求参数打包成数组
		
//		sParaTemp.put("show_url", show_url); // 商品展示地址
//		sParaTemp.put("anti_phishing_key", anti_phishing_key); // 防钓鱼时间戳
//		sParaTemp.put("exter_invoke_ip", exter_invoke_ip); // 客户端的IP地址, 非局域网的外网IP地址，如：221.0.0.1
		
		mav.addObject("html", genAlipayHtml( userId,  order));

		return mav;
		
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/notify")
	public String notifyUrl(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		for (String key : parameterMap.keySet()) {
			map.put(key, request.getParameter(key));
		}
		
		System.out.println(map);
		if (map.get("trade_status").equals("TRADE_FINISHED") || map.get("trade_status").equals("TRADE_SUCCESS")) {
			System.out.println("TRADE_FINISHED");
			if (AlipayNotify.verify(map)) {
				System.out.println("verify_FINISHED");
//				orderService.updateOrder(map.get("out_trade_no"), map.get("trade_no"));
			}
		}
		return "success";
	}

	@RequestMapping(value = "/return")
	public String returnUrl(HttpServletRequest request, String trade_status) {
		if (trade_status != null && trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
		}
		return "redirect:/user/order";
	}

	private String genAlipayHtml(int userId, Order order) {
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", AlipayConfig.PARTNER);
		sParaTemp.put("_input_charset", AlipayConfig.INPUT_CHARSET);
		sParaTemp.put("payment_type", AlipayConfig.PAYMENT_TYPE);
		sParaTemp.put("notify_url", AlipayConfig.NOTIFY_URL);
		sParaTemp.put("return_url", AlipayConfig.REUTRN_URL);
		sParaTemp.put("seller_email", AlipayConfig.NAME);
		
		sParaTemp.put("out_trade_no", order.getOrderId());
		sParaTemp.put("subject", order.getDescription());
		sParaTemp.put("total_fee", "0.01"); // 付款金额 TODO
		
		StringBuilder sb = new StringBuilder();
		sb.delete(sb.length() - 2, sb.length());
		sParaTemp.put("body", sb.toString());
//		sParaTemp.put("show_url", show_url); // 商品展示地址
//		sParaTemp.put("anti_phishing_key", anti_phishing_key); // 防钓鱼时间戳
//		sParaTemp.put("exter_invoke_ip", exter_invoke_ip); // 客户端的IP地址, 非局域网的外网IP地址，如：221.0.0.1
		return AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
	}
}
