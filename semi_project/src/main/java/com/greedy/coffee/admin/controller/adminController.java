package com.greedy.coffee.admin.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greedy.coffee.common.Pagenation;
import com.greedy.coffee.common.PagingButtonInfo;
import com.greedy.coffee.event.dto.EventDTO;
import com.greedy.coffee.event.service.EventService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class adminController {
	
	private final EventService eventService;
	
	public adminController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@GetMapping("/page")
	public String admin() {
		
		return "admin/adminPage";
	}	
	
	@GetMapping("list")
	public String eventBoard(@RequestParam(defaultValue="1") int page, Model model) {
		
		Page<EventDTO> eventList = eventService.selectBoardList(page);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(eventList);
		log.info("[eventBoard] eventList : {}", eventList);
		model.addAttribute("eventList", eventList);
		
		return "admin/eventBoard";
	} 
	
	@GetMapping("/orderStatus")
	public String orderStausBoard() {
		return "admin/orderStatus";
	}
	
	
}
