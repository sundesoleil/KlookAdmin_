<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>클룩 :: 결제관리</title>
	<link rel="icon" href="/resources/images/favicon.png" />
	<link rel="stylesheet" href="/resources/css/reset.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" integrity="sha512-aOG0c6nPNzGk+5zjwyJaoRUgCdOrfSDhmMID2u4+OIslr0GjpLKo7Xm0Ao3xmpM4T8AmIouRkqwj1nrdVsLKEQ==" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.structure.min.css" integrity="sha512-oM24YOsgj1yCDHwW895ZtK7zoDQgscnwkCLXcPUNsTRwoW1T1nDIuwkZq/O6oLYjpuz4DfEDr02Pguu68r4/3w==" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.min.css" integrity="sha512-9h7XRlUeUwcHUf9bNiWSTO9ovOWFELxTlViP801e5BbwNJ5ir9ua6L20tEroWZdm+HFBAWBLx2qH4l4QHHlRyg==" crossorigin="anonymous" />
	<link rel="stylesheet" href="/resources/css/settlement.css" />
	
	<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous"></script>
	<script src="/resources/js/settlement.js"></script>
</head>
<body>
	<%@include file="/WEB-INF/views/includes/left_menu.jsp" %>
	<div class="wrap">
		<h1><i class="fas fa-hand-holding-usd"></i>결제관리</h1>
		<div class="search_area">
			<div class="search_date">
				<span>결제일</span>
				<input type="text" id="startDate" autocomplete="off"/>
				<span>~</span>
				<input type="text" id="endDate" autocomplete="off"/>
			</div>
			<div class="search_keyword">
				<select id="target">
					<option value="1">결제번호</option>
					<option value="2">회원이름</option>
				</select>
				<input type="text" id="keyword" />
				<button id="search">검색</button>
			</div>
		</div>
		<div class="member_contents">
			<div class="contents_header">
				<div>번호</div>
				<div>회원아이디</div>
				<div>상품명</div>
				<div>결제방법</div>
				<div>결제금액</div>
				<div>결제일</div>
			</div>
			<div class="member_list">
				<c:forEach items="${settlementList }" var="settlement">
				<div class="member_item">
					<div class="member_seq">${settlement.ks_seq }</div>
					<div>${settlement.email }</div>
					<div>${settlement.product_name }</div>
					<div>${settlement.ks_method }</div>
					<div>${settlement.ks_amount }</div>
					<div><fmt:formatDate value="${settlement.ks_pay_date }" pattern="yyyy-MM-dd HH:mm" /></div>
					<a href="#" class="delete"><i class="fas fa-trash"></i></a>
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