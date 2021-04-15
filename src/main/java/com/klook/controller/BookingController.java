package com.klook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.klook.service.BookingService;
import com.klook.vo.BookingVO;

@Controller
public class BookingController {
	@Autowired
	BookingService service;
	
	@GetMapping("/booking")
	public String getBooking(Model model, @RequestParam @Nullable Integer offset) {
		if(offset == null) {
			offset = 0;
		}
		List<BookingVO> bookingList = service.selectBookings(offset);
		
		model.addAttribute("bookingList", bookingList);
		model.addAttribute("menu_num", 4);
		
		return "/booking/list";
	}
}