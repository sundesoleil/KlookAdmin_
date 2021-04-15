/*sub_category.js */
$(function(){
	$("#add_category").click(function(){
			$(".category_add_modal").show();
			$("#save").show();
			$("#modify").hide();

			$.ajax({
				url:"/main_category_list",
				type:"get",
				success:function(data){
					$("#prod_category").html("");
					for(let i =0; i<data.length; i++){
						let template = '<option value="' + data[i].kmc_seq + '">' + data[i].kmc_name + '</option>';
						$("#prod_category").append(template);
					}
				}
			});
		})
			$("#save").click(function(){
				if($("#category_name").val() == ''){
					return;
				}
				let data = {
					"ksc_mc_seq":$("#prod_category option:selected").val(),
					"ksc_name":$("#category_name").val()
				}
				$.ajax({
					url:"/sub_category",
					type:"put",
					contentType:"application/json",
					data:JSON.stringify(data),
					success:function(data){
						alert("카테고리가 추가되었습니다");
						$("category_add_modal").hide();
						location.reload();
					},
					error:function(){
						alert("에러");
					}
				})
			})
		$("#cancel").click(function(){
			$("category_add_modal").hide();
			location.reload();
		})
		
		$(".item_modify button").click(function(){
			$(".category_add_modal").show();
			$(".modal_content h1 span").html("카테고리수정");
			$("#save").hide();
			$("#modify").show();
			
			
			let seq = $(this).attr("data-seq");
			$.ajax({
				url:"/sub_category/" + seq,
				type:"get",
				success:function(data){
					let product = data.product;
					$("#modify").attr("data-seq", product.ksc_seq);
					$("#category_name").val(product.ksc_name);
				
					$.ajax({
					url:"/main_category_list",
					type:"get",
					success:function(data){
					$("#prod_category").html("");
					for(let i =0; i<data.length; i++){
						let template = '<option value="' + data[i].kmc_seq + '">' + data[i].kmc_name + '</option>';
						$("#prod_category").append(template);
					}
					$("#prod_category option").prop("selected", false);
					$("#prod_category").val(product.ksc_mc_seq).prop("selected", true);
					}
				});
			},
			error:function(e){
				alert("에러");
				console.log(e);
			}
		})
			
	})
	$("#modify").click(function(){
		if(!confirm("수정하시겠습니까?")) return;
		let data = {
			"ksc_seq":$(this).attr("data-seq"),
			"ksc_mc_seq":$("#prod_category option:selected").val(),
			"ksc_name":$("#category_name").val()
			}
		
		$.ajax({
			url:"/sub_category",
			type:"patch",
			contentType:"application/json",
			data:JSON.stringify(data),
			success:function(data){
				alert(data.message);
				location.reload();
			},
			error:function(e){
				console.log(e);
				alert("에러");
			}
		})
	})
	$(".item_delete button").click(function(){
		if(!confirm("삭제하시겠습니까?")) return;
		let seq = $(this).attr("data-seq");
		$.ajax({
			url:"/sub_category/" + seq,
			type:"delete",
			success: (data) => {
				alert(data.message);
				location.reload();
			}
		})
	})
})