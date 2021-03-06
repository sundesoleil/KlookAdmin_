// products.js
$(function(){
	window.removeList = new Array();
	$("#product_add").click(function(){
		modalReset();
		$(".prod_insert_modal").show();
		$(".modal_content h1 span").html("μνμΆκ°");
		$("#save").show();
		$("#modify").hide();
		
		$.ajax({
			url:"/city_list",
			type:"get",
			success:function(data){
				$("#prod_city").html("");
				for(let i =0; i<data.length; i++){
					let template = '<option value="' + data[i].kc_seq + '">' + data[i].kc_name + '</option>';
					$("#prod_city").append(template);
				}
			}
		});
		
		$.ajax({
			url:"/main_category_list",
			type:"get",
			success:function(data){
				$("#prod_main_category").html("");
				for(let i =0; i<data.length; i++){
					let template = '<option value="' + data[i].kmc_seq + '">' + data[i].kmc_name + '</option>';
					$("#prod_main_category").append(template);
				}
			}
		})
		$.ajax({
			url:"/sub_category_list",
			type:"get",
			success:function(data){
				$("#prod_sub_category").html("");
				for(let i =0; i<data.length; i++){
					let template = '<option value="' + data[i].ksc_seq + '">' + data[i].ksc_name + '</option>';
					$("#prod_sub_category").append(template);
				}
			}
		})
	})
	$("#save").click(function(){
		$(".prod_insert_modal").hide();
		let data = {
		  "kp_name":$("#prod_name").val(),
		  "kp_description":$("#prod_description").val(),
		  "kp_city_seq":$("#prod_city option:selected").val(),
		  "kp_mc_seq":$("#prod_main_category option:selected").val(),
		  "kp_sc_seq":$("#prod_sub_category option:selected").val(), 
		  "kp_price":$("#prod_price").val(),
		  "kp_discount":$("#prod_discount").prop("checked")?1:0,
		  "kp_discount_rate":$("#prod_discount_value").val(),
		  "kp_point":$("#prod_point").prop("checked")?1:0,
		  "kp_point_rate":$("#prod_point_value").val(),
		}
		
		$.ajax({
			url:"/product",
			type:"put",
			contentType:"application/json",
			data:JSON.stringify(data),
			success:function(data){
				let form = $("#prod_img_form");
				let formData = new FormData(form[0]);
				$.ajax({
					url:"/product_img/" + data.prod_seq + "?city=" + $("#prod_city option:selected").html() +
					"&name="+$("#prod_name").val(),
					type:"put",
					data:formData,
					contentType:false,
					processData:false,
					success:function(){
						alert("μ νμ΄ μΆκ°λμμ΅λλ€.");
						$(".prod_insert_modal").hide();
						location.reload();
		/*				if($("#prod_detail_img_form input").length != 0){
							let formData = new FormData($("#prod_detail_img_form")[0]);
							$.ajax({
								url:"/product_img_detail"+ data.prod_seq + "?city=" + $("#prod_city option:selected").html() +
								"&name="+$("#prod_name").val(),
								type:"put",
								data:formData,
								contentType:false,
								processData:false,
								success:function(){
									alert("μ μ₯λ¨");
									location.reload();
								}
							
							})
						}*/
				}
				})

				
			},
			error:function(){
				alert("μλ¬");
			}
		})
	});
	
	$("#modify").click(function(){
		$(".prod_insert_modal").hide();
	});
	$("#cancel").click(function(){
		$(".prod_insert_modal").hide();
	});
	$(".prod_delete button").click(function(){
		if(!confirm("μ­μ νμκ² μ΅λκΉ?")) return;
		let seq = $(this).attr("data-seq");
		$.ajax({
			url:"/product/" + seq,
			type:"delete",
			success: (data) => {
				
				alert(data.message);
				location.reload();
			}
		})
	})
	$(".prod_modify button").click(function(){
		
		$(".prod_insert_modal").show();
		$(".modal_content h1 span").html("μνμμ ");
		$("#save").hide();
		$("#modify").show();
		
		let seq = $(this).attr("data-seq");
		$.ajax({
			url:"product/" + seq,
			type:"get",
			success:function(data){
				let product = data.product;
				$("#modify").attr("data-seq", product.kp_seq);
				$("#prod_name").val(product.kp_name);
				$("#prod_description").val(product.kp_description);
				$("#prod_price").val(product.kp_price);
				$("#prod_discount").prop("checked", product.kp_discount == 1);
				$("#prod_discount_value").val(product.kp_discount_rate);
				$("#prod_img_form > input").val();
				if(product.kp_point_rate != null && product.kp_point_rate != ''){
					$("#prod_point").prop("checked", true);
					$("#prod_point_value").val(product.kp_point_rate);
				}
				if(product.image_name != null){
					$(".prev_img").html(product.image_name);
				}else{
					$(".prev_img").html("μ΄λ―Έμ§ μμ");
				}
				
				$(".img_file_list").html("");
				for(let i=0; i<data.detailImgs.length; i++){
					$(".img_file_list").append(
					'<div class="detail_file">'+
						'<span>'+data.detailImgs[i].kpdi_uri+'</span>'+
						'<input type="file" name="imgs_files">'+
						'<button class="detail_img_delete" type="button">&times;</button>'+
					'</div>'
					);
				}
			
			$(".detail_img_delete").click(function(){
				window.removeList.push($(this).parent().find("span").html());
				console.log(window.removeList);
				$(this).parent().remove();
			})
				
			$.ajax({
			url:"/city_list",
			type:"get",
			success:function(data){
				$("#prod_city").html("");
				for(let i =0; i<data.length; i++){
					let template = '<option value="' + data[i].kc_seq + '">' + data[i].kc_name + '</option>';
					$("#prod_city").append(template);
				}
				$("#prod_city option").prop("selected", false);
				$("#prod_city").val(product.kp_city_seq).prop("selected", true);
				}
			});
			
		
				$.ajax({
					url:"/main_category_list",
					type:"get",
					success:function(data){
						$("#prod_main_category").html("");
						for(let i =0; i<data.length; i++){
							let template = '<option value="' + data[i].kmc_seq + '">' + data[i].kmc_name + '</option>';
							$("#prod_main_category").append(template);
						}
						$("#prod_main_category option").prop("selected", false);
						$("#prod_main_category").val(product.kp_mc_seq).prop("selected", true);
					}
				});
				$.ajax({
					url:"/sub_category_list",
					type:"get",
					success:function(data){
						$("#prod_sub_category").html("");
						for(let i =0; i<data.length; i++){
							let template = '<option value="' + data[i].ksc_seq + '">' + data[i].ksc_name + '</option>';
							$("#prod_sub_category").append(template);
						}
						$("#prod_sub_category option").prop("selected", false);
						$("#prod_sub_category").val(product.kp_sc_seq).prop("selected", true);
					}
				});			
			},
			error:function(e){
				alert("μλ¬");
				console.log(e);
			}
		})
	})
	
	$("#modify").click(function(){
		if(!confirm("μμ νμκ² μ΅λκΉ?"))return;
		
		let data = {
		
		  "kp_seq":$(this).attr("data-seq"),
		  "kp_city_seq":$("#prod_city option:selected").val(),
		  "kp_mc_seq":$("#prod_main_category option:selected").val(),
		  "kp_sc_seq":$("#prod_sub_category option:selected").val(),
		  "kp_name":$("#prod_name").val(),
		  "kp_description":$("#prod_description").val(),
		  "kp_price":$("#prod_price").val(),
		  "kp_discount":$("#prod_discount").prop("checked")?1:0,
		  "kp_discount_rate":$("#prod_discount_value").val(),
		  "kp_point":$("#prod_point").prop("checked")?1:0,
 		  "kp_point_value":$("#prod_point_value").val(),
		}
		
		let form = $("#prod_img_form");
		let formData = new FormData(form[0]);
		
		$.ajax({
			url:"/product_img/" + data.kp_seq + "?city=" + $("#prod_city option:selected").html() +
			"&name="+$("#prod_name").val(),
			type:"put",
			data:formData,
			contentType:false,
			processData:false,
			success:function(){
				console.log("μ΄λ―Έμ§ μ μ‘ λ");
			}
		})
	
		$.ajax({
			url:"/product",
			type:"patch",
			contentType:"application/json",
			data:JSON.stringify(data),
			success:function(data){
/*				if(window.removeList.length > 0){
					let param = "";
					window.removeList.forEach(uri => {
						param += uri;
						param += ",";
					});
					param = param.substr(0, param.length - 1);
					alert(param);
					$.ajax({
						url:"/product_detail_img?uris=" + param,
						type:"delete",
						success:function(data){
							alert(data.message);
							location.reload();
						}
					})
				}
				else{*/
					alert(data.message);
					location.reload();
	/*			}*/
			},
			error:function(e){
				console.log(e);
				alert("μλ¬");
			}
		})
	})
	
	function modalReset(){
		$("#prod_name").val("");
		$("#prod_description").val("");
		$("#prod_price").val("");
		$("#prod_discount").prop("checked", false);
		$("#prod_discount_value").val("");
		$("#prod_point").val("checked", false);
		$("#prod_point_value").val("");
		$("#prod_img_form > input").val("");
		window.removeList = new Array();
	}
	$(".modal_content").draggable();
	
	$("#img_add").click(function(){
		$(".img_file_list").append(
			'<div class="detail_file">'+
				'<span></span>'+
				'<input type="file" name="imgs_files">'+
				'<button class="detail_img_delete" type="button">&times;</button>'+
			'</div>'
		);
	})
})

