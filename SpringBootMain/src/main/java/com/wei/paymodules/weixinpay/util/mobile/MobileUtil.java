package com.wei.paymodules.weixinpay.util.mobile;

import com.google.gson.Gson;

import com.wei.paymodules.weixinpay.util.ConfigUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信H5支付工具类
 * 创建者 科帮网
 * 创建时间	2017年7月31日
 */
public class MobileUtil {
	/**
	 * 获取用户openID
	 * @Author  科帮网
	 * @param code
	 * @return  String
	 * @Date	2017年7月31日
	 * 更新日志
	 * 2017年7月31日  科帮网 首次创建
	 *
	 */
	public static String getOpenId(String code){
		if (code != null) {
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
					+ "appid="+ ConfigUtil.APP_ID
					+ "&secret="+ ConfigUtil.APP_SECRET + "&code="
					+code + "&grant_type=authorization_code";
			String returnData = getReturnData(url);
			Gson gson = new Gson();
			OpenIdClass openIdClass = gson.fromJson(returnData,
					OpenIdClass.class);
			if (openIdClass.getOpenid() != null) {
				return openIdClass.getOpenid();
			}
		}
		return "**************";
	}
	public static String getReturnData(String urlString) {
		String res = "";
		try {
			URL url = new URL(urlString);
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url
					.openConnection();
			conn.connect();
			java.io.BufferedReader in = new java.io.BufferedReader(
					new java.io.InputStreamReader(conn.getInputStream(),
							"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				res += line;
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * 回调request 参数解析为map格式
	 * @Author  科帮网
	 * @param request
	 * @return
	 * @throws Exception  Map<String,String>
	 * @Date	2017年7月31日
	 * 更新日志
	 * 2017年7月31日  科帮网 首次创建
	 *
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request)
			throws Exception {
		// 解析结果存储在HashMap
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}
		// 释放资源
		inputStream.close();
		inputStream = null;
		return map;
	}
}
