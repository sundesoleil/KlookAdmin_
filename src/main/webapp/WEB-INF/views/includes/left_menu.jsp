<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must_revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
 %>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="icon" href="/resources/images/favicon.png" />
	<link rel="stylesheet" href="/resources/css/reset.css" />
	<link rel="stylesheet" href="/resources/css/left_menu.css" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
	<script>
		$(function(){
			let menu_num = "${menu_num}";
			$(".menu a").eq(menu_num).addClass("current");
		})
		
	<%--<c:if test="${user == null}">
			location.href="/";
		</c:if>--%>
	</script> 
</head>
<body>
	<div class="left_side">
			<a href="/products" id="logo">
				<img src="/resources/images/logo.JPEG" />
			</a>
			<%-- <c:if test="${user != null }">
				<div class="user_menu">
					<span>${user }</span>
					<a href="/logout">로그아웃</a>
				</div>
			</c:if>  --%>
			<div class="menu">
				<a href="/products"><i class="fas fa-box"></i>상품관리</a>
				<a href="/main_category"><i class="fas fa-tags"></i>카테고리(대)</a>
				<a href="/sub_category"><i class="fas fa-tag"></i>카테고리(소)</a>
				<a href="/city"><i class="fas fa-city"></i>도시관리</a>
				<a href="/booking"><i class="fas fa-book"></i>예약관리</a>
				<a href="/review"><i class="fas fa-feather-alt"></i>리뷰관리</a>
				<a href="/settlement"><i class="fas fa-hand-holding-usd"></i>결제관리</a>
				<a href="/member"><i class="fas fa-users"></i>회원관리</a>
			</div>
		</div>
</body>
</html>