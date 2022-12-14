package com.greedy.coffee.member.controller;

import java.io.IOException;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.member.service.AuthenticationService;
import com.greedy.coffee.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

	private final PasswordEncoder passwordEncoder;
	private final AuthenticationService authenticationService;
	private final MemberService memberServcie;
	private final MessageSourceAccessor messageSourceAccessor;
	
	public MemberController(PasswordEncoder passwordEncoder, AuthenticationService authenticationService, MemberService memberServcie, MessageSourceAccessor messageSourceAccessor) {
		this.passwordEncoder = passwordEncoder;
		this.authenticationService = authenticationService;
		this.memberServcie = memberServcie;
		this.messageSourceAccessor = messageSourceAccessor;
	}
	
	/* ????????? */
	@GetMapping("/login")
	public String goLogin() {
		return "member/login";
	}
	
	/* ????????? ?????? */
	@PostMapping("/loginfailed")
	public String loginFailed(RedirectAttributes rttr) {
		
		log.info(" ========== MemberLoginFailed - loginFailed ==========");
		
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));
		
		return "redirect:/member/login";
		
	}
	
	/* ?????? ?????? ????????? */
	@GetMapping("/terms")
	public String goTerms() {
		
		return "member/terms";
				
	}
	
	/* ?????? ?????? */
	@GetMapping("regist")
	public String goRegistMember() {
		
		return "member/regist";
				
	}
	
	/* ?????? ?????? */
	@PostMapping("/regist")
	public String registMember(@ModelAttribute MemberDTO member, RedirectAttributes rttr ) {
		
		log.info(" ========== MemberController - registMember ==========");
		
//		long temp = Integer.parseInt(member.getMemPhone1().toString() + member.getMemPhone2().toString() + member.getMemPhone3().toString());
		
//		member.setMemPhone = Integer.parseInt(temp)	// ????????? DB??? ???????????? 010??? ??? 0??? ?????????
		
		log.info("Phone1 : " + member.getMemPhone1());
		
		log.info("Phone2 : " + member.getMemPhone2().toString());
		
		log.info("Phone3 : " + member.getMemPhone3().toString());
		
		String temp = member.getMemPhone1() + member.getMemPhone2().toString() + member.getMemPhone3().toString();
		
		log.info(temp);
		
		member.setMemPhone("0" + temp);
		
		member.setMemPwd(passwordEncoder.encode(member.getMemPwd()));
		
		log.info(" ========== MemberController registMember request Member : " + member);
		
		memberServcie.registMember(member);
		
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.regist"));
		
		log.info(" ========== MemberController - registMember ==========");
		
		return "redirect:/member/welcome";
		
	}
	
	/* ?????? ?????? */
	@GetMapping("/welcome")
	public String goWelcome() {
		
		return "member/welcome";
		
	}
	
	/* ????????? ?????? ?????? - Unsync */
	@PostMapping("/idRedupCheck")
	public ResponseEntity<String> checkReduplication(@RequestBody MemberDTO member) {
		
		log.info(" ========== MemberController - checkReduplication ==========");
		
		String result = "?????? ????????? ??????????????????.";
		log.info("MemberController Check ID - {}", member.getMemId());
		
		if(memberServcie.selectMemberById(member.getMemId())) {
			
			result = "?????? ??? ??????????????????.";
			log.info("MemberController is Reduplicated");
			
		}
		
		log.info(" ========== MemberController - checkReduplication ==========");
		
		return ResponseEntity.ok(result);
		
	}
	
	@PostMapping("/")
	public String redirectMain() {
		
		return "redirect:/";
	}
	

	
	/* ????????? ?????? ????????? */
	@GetMapping("findId")
	public String goFindId() {
		
		return "member/findId";
		
	}
	
	// ?????? ?????? ????????? ??????
	@PostMapping("findId")
	@ResponseBody
	public String findMemberId(@RequestParam("mname") String mname, @RequestParam("mphone") String mphone) {
		// System.out.println(mname+ " : "+mphone);

		return findMemberId(mname, mphone);
	}
	
	/* ????????? ?????? ????????? */	
	@GetMapping("foundId")
	public String goFoundId() {
		
		return "member/foundId";
		
	}
	
	/* ???????????? ?????? ????????? */
	@GetMapping("findPassword")
	public String gofindPassword() {
		
		return "member/findPassword";
		
	}
	
	/* ???????????? ?????? ????????? */	
	@GetMapping("foundPassword")
	public String goFoundPassword() {
		
		return "member/foundPassword";
		
	}
	
	protected Authentication createNewAuthentication(Authentication currentAuth, String memId) {
		
		UserDetails newPrincipal = authenticationService.loadUserByUsername(memId);
    	UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
    	newAuth.setDetails(currentAuth.getDetails());
    	return newAuth;
		
	}
	
}
