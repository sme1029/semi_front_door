package com.greedy.coffee.member.controller;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
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
	
	/* 로그인 */
	@GetMapping("/login")
	public String goLogin() {
		return "member/login";
	}
	
	/* 로그인 실패 */
	@PostMapping("/loginfailed")
	public String loginFailed(RedirectAttributes rttr) {
		
		log.info(" ========== MemberLoginFailed - loginFailed ==========");
		
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));
		
		return "redirect:/member/login";
		
	}
	
	/* 회원 가입 페이지 */
	@GetMapping("/terms")
	public String goTerms() {
		
		return "member/terms";
				
	}
	
	/* 회원 가입 */
	@GetMapping("regist")
	public String goRegistMember() {
		
		return "member/regist";
				
	}
	
	/* 회원 가입 */
	@PostMapping("/regist")
	public String registMember(@ModelAttribute MemberDTO member, RedirectAttributes rttr ) {
	
		log.info(" ========== MemberController - registMember ==========");
		
		member.setMemPwd(passwordEncoder.encode(member.getMemPwd()));
		
		log.info(" ========== MemberController registMember request Member : " + member);
		
		memberServcie.registMember(member);
		
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.regist"));
		
		log.info(" ========== MemberController - registMember ==========");
		
		return "redirect:/member/welcome";
		
	}
	
	/* 가입 환영 */
	@GetMapping("/welcome")
	public String goWelcome() {
		
		return "member/welcome";
		
	}
	
	
	
	/* 아이디 중복 체크 - Unsync */
	@PostMapping("/idRedupCheck")
	public ResponseEntity<String> checkReduplication(@RequestBody MemberDTO member) {
		
		log.info(" ========== MemberController - checkReduplication ==========");
		
		String result = "사용 가능한 아이디입니다.";
		log.info("MemberController Check ID - {}", member.getMemId());
		
		if(memberServcie.selectMemberById(member.getMemId())) {
			
			result = "중복 된 아이디입니다.";
			log.info("MemberController is Reduplicated");
			
		}
		
		log.info(" ========== MemberController - checkReduplication ==========");
		
		return ResponseEntity.ok(result);
		
	}
	
	/* 아이디 찾기 페이지 */
	@GetMapping("findId")
	public String goFindId() {
		
		return "member/findId";
		
	}
	
	/* 아이디 찾음 페이지 */	
	@GetMapping("foundId")
	public String goFoundId() {
		
		return "member/foundId";
		
	}
	
	/* 비밀번호 찾기 페이지 */
	@GetMapping("findPassword")
	public String gofindPassword() {
		
		return "member/findPassword";
		
	}
	
	/* 비밀번호 찾음 페이지 */	
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
