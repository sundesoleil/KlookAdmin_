package com.klook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.klook.vo.BookingVO;

@Mapper
public interface BookingMapper {
	public BookingVO selectBookingBySeq(Integer seq);
	public List<BookingVO> selectBookings();
	public Integer selectBookingCount();
	public void deleteBooking(Integer seq);
}
	