package com.ict.exam;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

// @Controller
// public class TestAjaxController {
//	 return 이 servlet-context.xml 의 ViewResolver 에 가서 prefix,suffix 를 받아서 뷰를 만들어서 이동
//	@RequestMapping("test01.do")
//	public ModelAndView Test01() {
//		ModelAndView mv = new ModelAndView("result");
//		// 내용(일처리)
//		return mv;
//	}
//	
//	@RequestMapping("test01.do")
//	public String Test02() {
//		ModelAndView mv = new ModelAndView("result");
//		// 내용(일처리)
//		return mv;
//	}
// }

@RestController
public class TestAjaxController {
	// servlet-context.xml로 리턴되지 않고 브라우저에 출력된다.
	// 반환형이 String 경우 문서 타입이 contentType="text/html" 타입으로 알아서 처리 
	@RequestMapping("test01.do")
	@ResponseBody
	public String Hello() {
		return "<h2> Hello World ICT CAMP </h2>";
	}
	
	@RequestMapping(value="xml01.do", produces="text/xml; charset=utf-8")
	@ResponseBody
	public String XML_Test() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<products>");
		sb.append("<product>");
		sb.append("<name>흰우유</name>");
		sb.append("<price>950</price>");
		sb.append("</product>");
		sb.append("<product>");
		sb.append("<name>딸기우유</name>");
		sb.append("<price>1050</price>");
		sb.append("</product>");
		sb.append("<product>");
		sb.append("<name>초코우유</name>");
		sb.append("<price>1100</price>");
		sb.append("</product>");
		sb.append("<product>");
		sb.append("<name>바나나우유</name>");
		sb.append("<price>1550</price>");
		sb.append("</product>");
		sb.append("</products>");
		return sb.toString();
	}
	@RequestMapping(value="xml02.do", produces="text/xml; charset=utf-8")
	@ResponseBody
	public String XML_Test2() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<products>");
		sb.append("<product count=\"5\" name=\"제너시스\" />");
		sb.append("<product count=\"7\" name=\"카렌스\" />");
		sb.append("<product count=\"9\" name=\"카니발\" />");
		sb.append("<product count=\"5\" name=\"카스타\" />");
		sb.append("<product count=\"2\" name=\"트위치\" />");
		sb.append("</products>");
		return sb.toString();
	}
	
	@RequestMapping(value="xml03.do", produces="text/xml; charset=utf-8")
	@ResponseBody
	public String XML_Test3() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<products>");
		sb.append("<product count=\"5\" name=\"제너시스\"> 현대자동자 </product>");
		sb.append("<product count=\"7\" name=\"카렌스\"> 기아자동자 </product>");
		sb.append("<product count=\"9\" name=\"카니발\"> 기아자동자 </product>");
		sb.append("<product count=\"5\" name=\"카스타\"> 기아자동자 </product>");
		sb.append("<product count=\"2\" name=\"트위치\"> 르노자동자 </product>");
		sb.append("</products>");
		return sb.toString();
	}
	
	// 전달하는 방법
	@RequestMapping(value="xml04.do", produces="text/xml; charset=utf-8")
	@ResponseBody
	public String XML_Test4() {
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL("http://www.kma.go.kr/XML/weather/sfc_web_map.xml");
			URLConnection conn = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String msg = "";
			while((msg = br.readLine()) != null) {
				sb.append(msg);
			}
		} catch (Exception e) {
		}
		return sb.toString();
	}
	
	// 파싱하는 방법 : DOM 방식
	@RequestMapping(value="xml05.do", produces="text/html; charset=utf-8")
	@ResponseBody
	public String XML_Test5() {
		StringBuffer sb = new StringBuffer();
		sb.append("<table>");
		sb.append("<thead><tr><th>지역</th><th>온도</th><th>상태</th><th>아이콘</th></tr></thead>");
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			URL url = new URL("http://www.kma.go.kr/XML/weather/sfc_web_map.xml");
			InputStream is = url.openStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			Document document = builder.parse(bis);
			sb.append("<tbody>");
			NodeList local = document.getElementsByTagName("local");
			for(int i=0; i <local.getLength(); i++) {
				sb.append("<tr>");
				sb.append("<td>" + local.item(i).getFirstChild().getNodeValue() + "</td>");
				sb.append("<td>" + ((Element)(local.item(i))).getAttribute("ta") + "</td>");
				sb.append("<td>" + ((Element)(local.item(i))).getAttribute("desc") + "</td>");
				sb.append("<td><img src='http://www.kma.go.kr/images/icon/NW/NB" 
				+ ((Element)(local.item(i))).getAttribute("icon") + ".png'></td>");
				sb.append("</tr>");
			}
			sb.append("</tbody>");
			sb.append("</table>");
		} catch (Exception e) {
		}
		return sb.toString();
	}
	
	@RequestMapping(value="jsontest01.do", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String JSON_Test() {
		StringBuffer sb = new StringBuffer();
		try {
			sb.append("[");
			sb.append("{\"name\" : \"흰우유\", \"price\" : \"950\"},");
			sb.append("{\"name\" : \"딸기우유\", \"price\" : \"1050\"},");
			sb.append("{\"name\" : \"초코우유\", \"price\" : \"1150\"},");
			sb.append("{\"name\" : \"바나나우유\", \"price\" : \"1550\"}");
			sb.append("]");
		} catch (Exception e) {
		}
		return sb.toString();
	}
	
	@RequestMapping(value="jsontest02.do", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String JSON_Test2() {
		StringBuilder sb = new StringBuilder();
		try {
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=7Jn37duPnqJP2hXtNvhcywuZlcu2XWgEJYHRSJIIwWps7J94qVJ8gOWdJOJSqoQ9rH2YQCMaCFMtlFsxFPAv8A%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
	        urlBuilder.append("&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode("11B10101", "UTF-8")); /*11B10101 서울, 11B20201 인천 등 ( 별첨엑셀자료 참고)*/
	        urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode("202302270600", "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력- YYYYMMDD0600(1800) 최근 24시간 자료만 제공*/
	        
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	      
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
		} catch (Exception e) {
		}
		return sb.toString();
	}
}














