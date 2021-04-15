/*member_list.js */
$(function(){
	$.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년'
    });

	$("#startDate, #endDate").datepicker();
	
	let offset = getParam("offset");
	let curPage = Number(offset);
	let pageCnt = 0;
	
	$.ajax({
		url:"/member_cnt",
		type:"get",
		success:function(result){
			pageCnt = Math.ceil(result.count/10);
			$(".pagers").html(""); // .pagers 내부 html 삭제
			let start = 0;
			if(curPage - 4 >= 0){
				start = curPage-4;
			}
			else{
				start = 0;
			}
			
			for(let i=start; i < pageCnt; i++){
				let template;
				if(offset == i){
					template ='<a href="/member?offset='+ i +'"class="current">'+(i+1)+'</a>';
				}
				else{
					template ='<a href="/member?offset='+ i + '">'+(i+1)+'</a>';
				}
				$(".pagers").append(template);
				if(i-start == 8){
					break;
				}
			}
		}
		
	})
	
	$("#prev").click(function(){
		let newOffset = Number(getParam("offset")) - 1;
		if(newOffset < 0) return;
		location.href="/member?offset=" + newOffset;
	})
	$("#prev-10").click(function(){
		let newOffset = Number(getParam("offset")) - 10;
		if(newOffset < 0) newOffset = 0;
		location.href="/member?offset=" + newOffset;
	})
	$("#next").click(function(){
		let newOffset = Number(getParam("offset")) + 1
		if(newOffset > pageCnt - 1) return;
		location.href="/member?offset=" + newOffset;
	})
	$("#next-10").click(function(){
		let newOffset = Number(getParam("offset")) + 10;
		if(newOffset > pageCnt - 1) newOffset = pageCnt - 1;
		location.href="/member?offset=" + newOffset;
	})

		$(".delete").click(function(){
			if(!confirm("삭제하시겠습니까?")) return;
			$.ajax({
				url:"/settlement/" + $(this).parent().find(".member_seq").html(),
				type:"delete",
				success:function(result){
					alert(result.message);
					location.reload();
				},
				error:function(e){
					alert("에러");
					console.log(e);
				}
			})
		})
})

function getParam(sname) {

    var params = location.search.substr(location.search.indexOf("?") + 1);
    var sval = "";

    params = params.split("&");

    for (var i = 0; i < params.length; i++) {
        temp = params[i].split("=");
        if ([temp[0]] == sname) { sval = temp[1]; }
    }
    return sval;
}