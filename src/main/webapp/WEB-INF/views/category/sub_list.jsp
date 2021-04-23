<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>클룩 :: 카테고리(소) 관리</title>
	<link rel="icon" href="/resources/images/favicon.png" />
	<link rel="stylesheet" href="/resources/css/sub_category.css" />
	<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="/resources/js/sub_category.js"></script>
</head>
<body>
		<%@include file="/WEB-INF/views/includes/left_menu.jsp" %>
		<div class="wrap">	
			<div class="header_area">
				<h1><i class="fas fa-tag"></i>카테고리(소) 관리</h1>
				<div class="header_right">
					<div class="search_box">
						<input type="text" id="search_keyword" />
						<img src="/resources/images/DM_20210324195650_057.PNG" />
					</div>
					<button id="add_category"><i class="fas fa-tag"></i>카테고리(소) 추가</button>
				</div>
			</div>
			<div class="list">
				<div class="list_header">
					<div>NO</div>
					<div>대분류</div>
					<div>소분류</div>
					<div>등록일</div>
	<!-- 				<div>상품수</div> -->
					<div>수정</div>
					<div>삭제</div>
				</div>
				<div class="list_area">
					<c:forEach items="${sub_list }" var="item">
						<div class="list_item"> 
							<div class="item_no">${item.ksc_seq}</div>
							<div class="item_no">${item.category_name}</div>
							<div class="item_name">${item.ksc_name }</div>
							<div class="item_reg_date">
								<fmt:formatDate value="${item.ksc_reg_date}" pattern="yyyy-MM-dd HH:mm" />
							</div>
					<!-- 		<div class="item_count">123</div> -->
							<div class="item_modify">
								<button type="button" class="modify_btn" data-seq="${item.ksc_seq}">수정</button>
							</div>
							<div class="item_delete">
								<button type="button" class="delete_btn" data-seq="${item.ksc_seq}">삭제</button>
							</div>
						</div>
					</c:forEach>
				</div>	
			</div>
		</div>
		<div class="category_add_modal">
			<div class="modal_content">
				<h1><i class="fas fa-tags"></i><span>카테고리 추가</span></h1>
				<div class="category">
					<p class="item_title">카테고리(대)</p>
					<select id="prod_category">
						<option value="1">카테고리1</option>
						<option value="2">카테고리2</option>
						<option value="3">카테고리3</option>
					</select>
				</div>
				<div class="input_row">
					<p>카테고리명</p>
					<input type="text" id="category_name" name="category_name" placeholder="카테고리 명을 입력하세요"/>
				</div>
				<div class="input_row" id="buttons">
					<button id="save">저장</button>
					<button id="modify">수정</button>
					<button id="cancel">취소</button>
				</div>
			</div>
		</div>
</body>
</html>