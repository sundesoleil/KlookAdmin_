/*city.js */
$(function(){
	$("#add_category").click(function(){
		$(".category_add_modal").addClass("open");
		$("#save").show();
		$("#modify").hide();
	});
	$("#save").click(function(){
		if($("#category_name").val() == ''){
			return;
		}
		$.ajax({
			url:"/api/insert_city?name=" + $("#category_name").val(),
			type:"get",
			success:function(data){
				
				if(data.status == 'failed'){
					alert(data.message);
				}else{
					alert(data.message);
					location.reload();
				}
/*			$.ajax({
			url:"/api/insert_city?name=" + $("#category_name").val(),
			type:"get",
			success:function(data){
				let form = $("#prod_img_form");
				let formData = new FormData(form[0]);
				$.ajax({
					url:"/city_img/" + data.city_seq + "?city="+ $("#category_name").val() + 
					"&name="+$("#prod_name").val(),
					type:"put",
					data:formData,
					contentType:false,
					processData:false,
					success:function(){
						alert("도시가 추가되었습니다.");
						$(".prod_insert_modal").hide();
			
						},
						error:function(){
							alert("에러");
					}
				})
	},
	error:function(){
		alert("에러");
	}
	})*/
			},
			error:function(){
				alert("에러");
			}
		})
		
		
	})
	$("#cancel").click(function(){
		$(".category_add_modal").removeClass("open");
		$("#category_name").val("");
	})
	
	$(".delete_btn").click(function(){
		let name = $(this).parent().parent().find(".item_name").html();
		if(confirm(name+" 선택한 도시를 삭제하시겠습니까?") == false) return;
		let seq = $(this).parent().parent().find(".item_no").html();
		
		$.ajax({
			url:"/city?seq="+seq,
			type:"delete",
			success:function(data){ // data > api 쪽에서 return 해주는 값
				alert(data.message);
				location.reload();
			} 
		})
		

	})

	$(".modify_btn").click(function(){
		window.modify_seq = $(this).parent().parent().find(".item_no").html(); // 전역변수 선언
		let name = $(this).parent().parent().find(".item_name").html();
		window.originalName = name;
		
		//$("#modify").prop("disabled",true);
		$(".category_add_modal").addClass("open");
		$("#save").hide();
		$("#modify").show();
		$("#category_name").val(name);
		$(".category_add_modal h1 span").html("카테고리수정");
	})
	
	$("#modify").click(function(){
		
		if(confirm("수정하시겠습니까?") == false)
			return;
	
		$.ajax({
		url:"/city/" + window.modify_seq + "?name=" + $("#category_name").val(),
		type:"patch",
		success:function(data){
			alert(data.message);
			if(data.status == 'success'){
				location.reload();
			}
			let form = $("#prod_img_form");
		let formData = new FormData(form[0]);
		
		$.ajax({
			url:"/product_img/" + data.kp_seq + "?city=" + $("#category_name").val() +
			"&name="+ $("#category_name").val() ,
			type:"put",
			data:formData,
			contentType:false,
			processData:false,
			success:function(){
				console.log("이미지 전송 끝");
			}
		})
				},
				error:function(){
					alert("에러");
				}
		})

	})
/*	$("#category_name").on("input", function(){
		console.log($("#category_name").val());
		if(window.originalName == $("#category_name").val() || $("#category_name").val() == ''){
			$("#modify").prop("disabled", true);
		}
		else{
			$("#modify").prop("disabled", false);
		}
	})*/
})