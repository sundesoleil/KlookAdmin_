<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>클룩 :: 예약관리</title>
	<link rel="icon" href="/resources/images/favicon.png" />
	<link rel="stylesheet" href="/resources/css/reset.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" integrity="sha512-aOG0c6nPNzGk+5zjwyJaoRUgCdOrfSDhmMID2u4+OIslr0GjpLKo7Xm0Ao3xmpM4T8AmIouRkqwj1nrdVsLKEQ==" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.structure.min.css" integrity="sha512-oM24YOsgj1yCDHwW895ZtK7zoDQgscnwkCLXcPUNsTRwoW1T1nDIuwkZq/O6oLYjpuz4DfEDr02Pguu68r4/3w==" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.min.css" integrity="sha512-9h7XRlUeUwcHUf9bNiWSTO9ovOWFELxTlViP801e5BbwNJ5ir9ua6L20tEroWZdm+HFBAWBLx2qH4l4QHHlRyg==" crossorigin="anonymous" />
	<link rel="stylesheet" href="/resources/css/member_list.css" />
	
	<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous"></script>
	<script src="/resources/js/booking.js"></script>
</head>
<body>
	<%@include file="/WEB-INF/views/includes/left_menu.jsp" %>
	<div class="wrap">
		<h1><i class="fas fa-book"></i>예약관리</h1>
		<div class="search_area">
			<div class="search_date">
				<span>예약일자</span>
				<input type="text" id="startDate" autocomplete="off"/>
				<span>~</span>
				<input type="text" id="endDate" autocomplete="off"/>
			</div>
			<div class="search_keyword">
				<select id="target">
					<option value="1">예약번호</option>
					<option value="1">이메일</option>
				</select>
				<input type="text" id="keyword" />
				<button id="search">검색</button>
			</div>
		</div>
		<div class="member_contents">
			<div class="contents_header">
				<div>번호</div>
				<div>예약날짜</div>
				<div>결제여부</div>
				<div>결제일</div>
				<div>회원번호</div>
				<div>예약상품번호</div>
			</div>
			<div class="member_list">
				<c:forEach items="${bookingList }" var="booking">
				<div class="member_item">
					<div class="member_seq">${booking.kb_seq }</div>
					<div><fmt:formatDate value="${booking.kb_book_date }" pattern="yyyy-MM-dd HH:mm" /></div>
					<div>
						<c:if test="${booking.kb_payment == 0 }">
							<input type="checkbox" disabled/>
						</c:if>
						<c:if test="${booking.kb_payment == 1 }">
							<input type="checkbox" checked disabled/>
						</c:if>
					</div>
					<div><fmt:formatDate value="${booking.kb_payment_date }" pattern="yyyy-MM-dd HH:mm" /></div>
					<div>${booking.kb_member_seq }</div>
					<div>${booking.kb_prod_seq }</div>
					<a href="#" class="delete" data-seq="${booking.kb_seq}"><i class="fas fa-trash"></i></a>
				</div>
				</c:forEach>
			</div>
		</div>
		<div class="pager_area">
			<button id="prev-10">
				<i class="fas fa-chevron-left"></i>
				<i class="fas fa-chevron-left"></i>
			</button>
			<button id="prev">
				<i class="fas fa-chevron-left"></i>
			</button>
			<div class="pagers"></div>
			<button id="next">
				<i class="fas fa-chevron-right"></i>
			</button>
			<button id="next-10">
				<i class="fas fa-chevron-right"></i>
				<i class="fas fa-chevron-right"></i>
			</button>
		</div>
	</div>
</body>
</html>