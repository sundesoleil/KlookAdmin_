package com.klook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klook.mapper.BookingMapper;
import com.klook.vo.BookingVO;

@Service
public class BookingService {
	@Autowired
	BookingMapper mapper;
	
	public BookingVO selectBookingBySeq(Integer seq) {
		return mapper.selectBookingBySeq(seq);
	}
	public List<BookingVO> selectBookings(Integer offset){
		offset = offset * 10;
		return mapper.selectBookings();
	}
	public void deleteBooking(Integer seq) {
		mapper.deleteBooking(seq);
	}
	public Integer selectBookingCount() {
		return mapper.selectBookingCount();
	}
}
