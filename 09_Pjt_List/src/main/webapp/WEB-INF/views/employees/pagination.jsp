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
	$(function(){
		
		// recordPerPage의 변경
		$('#recordPerPage').on('change', function(){
			location.href = '${contextPath}/employees/change/record.do?recordPerPage=' + $(this).val();	// session에 recordPerPage 올리기
		})
		// 세션에 저장된 recordPerPage 값으로 <select> 태그의 값을 세팅
		let recordPerPage = '${sessionScope.recordPerPage}' == '' ? '10' : '${sessionScope.recordPerPage}';
		$('#recordPerPage').val(recordPerPage);
		
		// 제목을 클릭하면 정렬 방식을 바꿈(ASC, DESC)
		$('.title').on('click', function(){
			location.href = '${contextPath}/employees/pagination.do?column=' + $(this).data('column') + '&order=' + $(this).data('order') + "&page=${page}";
		})
		// 언니 안녕....♡ 내가 누구냐면 힌트를 줄게 난 용띠야
		// 안녕 성민아......
	})
</script>
<style>
	.title {
		cursor: pointer;
	}
	.title:hover {
		color: gray;
	}
	.title:active {
		color: silver;
	}
	.pagination {
		width: 350px;
		margin: 0 auto;
		text-align: center;
	}
	.pagination span, .pagination a {
		display: inline-block;
		margin: 0 15px;
	}
	.hidden {
		visibility: hidden;
	}
	.strong {
		font-weight: 900;
	}
	.link {
		color: orange;
	}
	table {
		width: 1200px;
	}
	table td:nth-of-type(1){ width: 70px; }
	table td:nth-of-type(2){ width: 100px; }
	table td:nth-of-type(3){ width: 150px; }
</style>
</head>
<body>

	<div>
		<a href="${contextPath}/employees/search.do">사원 조회 화면으로 이동</a>
	</div>
	<div>
		<h1>사원 목록</h1>
		<div>
			<select id="recordPerPage">
				<option value="10">10개</option>
				<option value="20">20개</option>
				<option value="30">30개</option>
			</select>
		</div>
		<hr>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td><span class="title" data-column="E.EMPLOYEE_ID" data-order="${order}">사원번호</span></td>
					<td><span class="title" data-column="E.FIRST_NAME" data-order="${order}">사원명</span></td>
					<td><span class="title" data-column="E.EMAIL" data-order="${order}">이메일</span></td>
					<td><span class="title" data-column="E.PHONE_NUMBER" data-order="${order}">전화번호</span></td>
					<td><span class="title" data-column="E.HIRE_DATE" data-order="${order}">입사일자</span></td>
					<td><span class="title" data-column="E.JOB_ID" data-order="${order}">직업</span></td>
					<td><span class="title" data-column="E.SALARY" data-order="${order}">연봉</span></td>
					<td><span class="title" data-column="E.COMMISSION_PCT" data-order="${order}">커미션</span></td>
					<td><span class="title" data-column="E.MANAGER_ID" data-order="${order}">매니저</span></td>
					<td><span class="title" data-column="E.DEPARTMENT_ID" data-order="${order}">부서번호</span></td>
					<td><span class="title" data-column="D.DEPARTMENT_NAME" data-order="${order}">부서명</span></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${employees }" var="emp" varStatus="vs">
					<tr>
						<td>${beginNo - vs.index }</td>
						<td>${emp.employeeId }</td>
						<td>${emp.firstName } ${emp.lastName }</td>
						<td>${emp.email }</td>
						<td>${emp.phoneNumber }</td>
						<td>${emp.hireDate }</td>
						<td>${emp.jobId }</td>
						<td>${emp.salary }</td>
						<td>${emp.commissionPct }</td>
						<td>${emp.managerId }</td>
						<td>${emp.deptDTO.departmentId }</td>
						<td>${emp.deptDTO.departmentName }</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="12">
						${pagination }
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	
</body>
</html>