package com.ict.kakao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KaKaoMap_01 {
	
	@RequestMapping("kakaomap01.do")
	public ModelAndView kakaoMap01() {
		return new ModelAndView("kakao_map01");
	}
	@RequestMapping("kakaomap02.do")
	public ModelAndView kakaoMap02() {
		return new ModelAndView("kakao_map02");
	}
	@RequestMapping("kakaomap03.do")
	public ModelAndView kakaoMap03() {
		return new ModelAndView("kakao_map03");
	}
	@RequestMapping("kakaomap04.do")
	public ModelAndView kakaoMap04() {
		return new ModelAndView("kakao_map04");
	}
}
