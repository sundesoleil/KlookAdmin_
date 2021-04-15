<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>클룩 :: 관리자 페이지</title>
	<link rel="icon" href="/resources/images/favicon.png" />
	<link rel="stylesheet" href="/resources/css/products.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" integrity="sha512-aOG0c6nPNzGk+5zjwyJaoRUgCdOrfSDhmMID2u4+OIslr0GjpLKo7Xm0Ao3xmpM4T8AmIouRkqwj1nrdVsLKEQ==" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.structure.min.css" integrity="sha512-oM24YOsgj1yCDHwW895ZtK7zoDQgscnwkCLXcPUNsTRwoW1T1nDIuwkZq/O6oLYjpuz4DfEDr02Pguu68r4/3w==" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.min.css" integrity="sha512-9h7XRlUeUwcHUf9bNiWSTO9ovOWFELxTlViP801e5BbwNJ5ir9ua6L20tEroWZdm+HFBAWBLx2qH4l4QHHlRyg==" crossorigin="anonymous" />
	<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous"></script>
	<script src="/resources/js/products.js"></script>
</head>
<body>
	<div class="wrap">
	<%@include file="/WEB-INF/views/includes/left_menu.jsp" %>
		<div class="right_side">
			<div class="right_side_header">
				<h1><i class="fas fa-box"></i>상품관리</h1>
				<div class="search_box">
					<input type="text" />
					<img src="/resources/images/DM_20210324195650_057.PNG" />
				</div>
				<button id="product_add"><i class="fas fa-box"></i> 상품추가</button>
			</div>
			<div class="product_list">
				<div class="prod_header">
					<div>NO</div>
					<div>이미지</div>
					<div>지역</div>
					<div>카테고리(대)</div>
					<div>카테고리(소)</div>
					<div>이름</div>
					<div>상품설명</div>
					<div>가격</div>
					<div>할인</div>
					<div>적립</div>
					<div>등록일</div>
					<div>수정</div>
					<div>삭제</div>
				</div>
				<div class="products">
					<c:forEach items="${list }" var="item">
						<div class="product_item">
							<div class="prod_seq">${item.kp_seq}</div>
							<div class="prod_img">
								<c:if test="${item.image_uri == null }">
									<img src="#" />
								</c:if>
								<c:if test="${item.image_uri != null }">
									<img src="${item.image_uri}"/>
								</c:if>
							</div> 
							<div class="prod_city">${item.city_name}</div>
							<div class="prod_main_category">${item.main_category_name}</div>
							<div class="prod_sub_category">${item.sub_category_name}</div>
							<div class="prod_name">${item.kp_name}</div>
							<div class="prod_description">${item.kp_description}</div>
							<div class="prod_price">${item.kp_price}</div>
							<div class="prod_discount">
								<c:if test="${item.kp_discount == 0 }">
									<input type="checkbox" disabled/>
								</c:if>
								<c:if test="${item.kp_discount == 1 }">
									<input type="checkbox" disabled checked />
								</c:if>
							</div>
							<div class="prod_point">
								<c:if test="${item.kp_point == 0 }">
									<input type="checkbox" disabled/>
								</c:if>
								<c:if test="${item.kp_point == 1 }">
									<input type="checkbox" disabled checked />
								</c:if>
							</div>
							<div class="prod_reg_date">
								<fmt:formatDate value="${item.kp_reg_date}" pattern="yyyy-MM-dd HH:mm" />
							</div>
							<div class="prod_modify">
								<button type="button" data-seq="${item.kp_seq}">수정</button>
							</div>
							<div class="prod_delete">
								<button type="button" data-seq="${item.kp_seq}">삭제</button>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	
	<div class="prod_insert_modal">
		<div class="modal_content">
			<h1>
				<i class="fas fa-box"></i>
				<span>상품추가</span>
			</h1>
			<p class="item_title">상품이름</p>
			<input type="text" id="prod_name"/>
			<p class="item_title">상세설명</p>
			<input type="text" id="prod_description"/>
			<div class="cate_brand_area">
				<div class="category">
					<p class="item_title">도시</p> 
					<select id="prod_city">
						<option value="1">카테고리1</option>
						<option value="2">카테고리2</option>
						<option value="3">카테고리3</option>
					</select>
				</div>
				<div class="category">
					<p class="item_title">카테고리(대)</p>
					<select id="prod_main_category">
						<option value="1">카테고리1</option>
						<option value="2">카테고리2</option>
						<option value="3">카테고리3</option>
					</select>
				</div>
				<div class="category">
					<p class="item_title">카테고리(소)</p>
					<select id="prod_sub_category">
						<option value="1">카테고리1</option>
						<option value="2">카테고리2</option>
						<option value="3">카테고리3</option>
					</select>
				</div>
		</div>
		<div class="price_wrap">
			<div class="price_area">
				<p class="item_title">가격</p>
				<input type="number" id="prod_price" />
			</div>
			<div class="discount_area">
				<p class="item_title">할인</p>
				<input type="checkbox" id="prod_discount" />
				<input type="number" id="prod_discount_value" />
				</div>
				<div class="point_area">
					<p class="item_title">포인트</p>
					<input type="checkbox" id="prod_point" />
					<input type="number" id="prod_point_value" />
				</div>
			</div>
			<p class="item_title">상품 이미지</p>
			<form id="prod_img_form">
				<span class="prev_img"></span>
				<input type="file" name="file" accept=".jpg, .png, .jpeg, .webp "/>
			</form>
			<div class="button_area">
				<button id="save">저장</button>
				<button id="modify">수정</button>
				<button id="cancel">취소</button>
			</div>
		</div>
	</div>
</body>
</html>