
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	let addResult = '${addResult}';
	if(addResult != ''){
		if(addResult == 1){
			alert('삽입 성공!');
		} else {
			alert('삽입 실패!');
		}
	}
	
	$(function(){
		$('.product').on('click', function(){
			location.href = '${contextPath}/product/detail.do?prodNo=' + $(this).find('span').text();
		});
	})
</script>
<style>
	.product {
		width: 200px;
		border: 1px solid gray;
		
	}
	.product:hover {
		cursor: pointer;
		background-color: silver;
	}
</style>
</head>
<body>

	<div>
		<h3>제품 편집화면</h3>
		<form method="post" action="${contextPath }/product/modify.do">
			<div>
				<label for="prodName">제품이름</label>
				<input id="prodName" name="prodName" required="required" value="${productDTO.prodName }">
			</div>
			<div>
				<label for="prodPrice">제품가격</label>
				<input id="prodPrice" name="prodPrice" value="${productDTO.prodPrice }">
			</div>
			<div>
				<button>제품정보수정하기</button>
				<input type="reset" value="초기화">
			</div>
		</form>
	</div>
	
</body>
</html>