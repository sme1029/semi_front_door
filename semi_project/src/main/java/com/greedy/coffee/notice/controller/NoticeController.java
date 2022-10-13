package com.greedy.coffee.notice.controller;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.coffee.common.Pagenation;
import com.greedy.coffee.common.PagingButtonInfo;
import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.notice.dto.NoticeDTO;
import com.greedy.coffee.notice.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	private final NoticeService noticeService;
	private final MessageSourceAccessor messageSourceAccesor;
	
	
	public NoticeController(NoticeService noticeService, MessageSourceAccessor messageSourceAccesor) {
		this.noticeService = noticeService;
		this.messageSourceAccesor = messageSourceAccesor;
		
	}

	@GetMapping("noticeList")
	public String noticeList(@RequestParam(defaultValue="1") int page, 
							Model model) {
		
		log.info("[NoticeController] ========================================= ");
		log.info("[NoticeController] param page : {}", page);
		
		Page<NoticeDTO> noticeList = noticeService.selectNoticeList(page);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(noticeList);
		
		log.info("[NoticeController] noticeList : {}", noticeList.getContent());
		log.info("[NoticeController] paging : {}", paging);
		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("paging", paging);
		
		log.info("[NoticeController] ========================================= ");
		
		return "notice/noticeList";
	}
	
	@GetMapping("/noticeRegist")
	public String goNoticeRegist() {
		return "notice/noticeRegist";
	}
	
	@PostMapping("/noticeRegist")
	public String noticeRegist(NoticeDTO notice, @AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) {
		
		log.info("[NoticeController] ========================================= ");
		log.info("[NoticeController] noticeRegist request : {}", notice);
		
		
		//notice.setMember(member);	
		//notice.setMember(new MemberDTO());
		noticeService.noticeRegist(notice);
		
		rttr.addFlashAttribute("message", messageSourceAccesor.getMessage("notice.regist"));
		
		log.info("[NoticeController] ========================================= ");
		
		return "redirect:/notice/noticeList";
	}
	
//	@GetMapping("/notice/detail")
//	public String goNoticeDetail(@AuthenticationPrincipal MemberDTO member) {
//		
//		// 1. 요청 게시글 번호를 parameter로 전달 받는다
//		// 2. 요청 게시글 번호 기준으로 NoticeDTO 하나의 객체를 조회해온다.
//		// 3. 로그인 유저의 role에 따라 다른 응답 화면으로 응답한다.
//		
//		if(member.getMemRole().equals("ROLE_ADMIN")) {
//			return "";
//		}
//		
//		return "";
//	}
//	
//	@GetMapping("/noticeModify")
//	public String goNoticeModify() {
//		return "notice/noticeModify";
//	}
//
}
