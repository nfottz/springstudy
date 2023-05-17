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
  
  // 전역 변수 (각종 검사 통과 유무를 저장하는 변수)
  var verifyId = false;
  var verifyPw = false;
  var verifyEmail = false;
  
  // 함수 정의
  // 1. 아이디 검사
  function fnCheckId(){
    $('#id').on('keyup', function(){
      
      // 입력한 아이디
      let id = $(this).val();
      
      // 정규식(5~20자, 소문자/숫자/하이픈(-)/밑줄(_) 사용가능, 첫글자는 소문자/숫자)
      let regId = /^[a-z0-9][a-z0-9-_]{4,19}$/
      
      // 정규식 검사
      verifyId = regId.test(id);
      if(verifyId == false){
        $('#msgId').text('아이디는 5~20자, 소문자/숫자/하이픈(-)/밑줄(_) 사용가능, 첫글자는 소문자/숫자');
        return; // 여기서 함수 실행을 종료한다.(이후에 나오는 ajax(중복 체크) 실행을 막기 위함)
      }
      
      // 아이디 중복 체크 ajax
      $.ajax({
    	  type: 'get',
    	  url: '${contextPath}/user/verifyId.do',
    	  data: 'id=' + id,
    	  dataType: 'json',
    	  success: function(resData){  // resData = {"enableId": true} 또는 {"enableId": false}
    		  verifyId = resData.enableId;
    		  if(verifyId){
    			  $('#msgId').text('사용 가능한 아이디입니다.');
    		  } else {
    			  $('#msgId').text('이미 사용중인 아이디입니다.');
    		  }
    	  }
      })
    })
  }
  
  // 2. 비밀번호 검사(정규식)
  function fnCheckPw(){
	  
	  $('#pw').on('keyup', function(){
		  
		  // 입력한 비밀번호
		  let pw = $(this).val();
		  
		  // 정규식(4~20자, 소문자+대문자+숫자+특수문자 사용가능, 3개 이상 조합)
		  let pwLength = pw.length;
		  let validCount = /[a-z]/.test(pw)   // 소문자를 가지고 있으면 true(1), 없으면 false(0)
		                 + /[A-Z]/.test(pw)
		                 + /[0-9]/.test(pw)
		                 + /[^a-zA-Z0-9]/.test(pw);
		  verifyPw = (pwLength >= 4) && (pwLength <= 20) && (validCount >= 3);
		  if(verifyPw){
			  $('#msgPw').text('사용 가능한 비밀번호입니다.');
		  } else {
			  $('#msgPw').text('4~20자, 소문자+대문자+숫자+특수문자 사용가능, 3개 이상 조합');
		  }
	  })
  }
  
  // 3. 비밀번호 확인
  function fnCheckPwAgain(){
	  
	  $('#rePw').on('keyup', function(){
		  
		  let pw = $('#pw').val();
		  let rePw = $(this).val();
		  
		  verifyRePw = (rePw != '') && (rePw == pw);
		  if(verifyRePw){
			  $('#msgRePw').text('');
		  } else {
			  $('#msgRePw').text('비밀번호 입력을 확인하세요.');
		  }
		  
	  })
	  
  }
  
  // 4. 이름
  function fnCheckName(){
	  
	  $('#name').on('keyup', function(){
		  verifyName = $(this).val() != '';
	  })
	  
  }
  
  // 5. 휴대전화
  function fnCheckMobile(){
	  
	  $('#mobile').on('keyup', function(){
		  
		  let mobile = $(this).val();
		  let regMobile = /^010[0-9]{7,8}$/;
		  
		  verifyMobile = regMobile.test(mobile);
		  if(verifyMobile){
			  $('#msgMobile').text('');
		  } else {
			  $('#msgMobile').text('휴대전화 입력을 확인하세요.');
		  }
		  
	  })
	  
  }
  
  // 6. 년/월/일(select option 만들기, 월에 따라 보이는 일이 다르게)
  function fnCreateDate(){
	  
  }
  
  // 7. 이메일 검사 및 인증코드 전송
  function fnCheckEmail(){
	  
	  $('#btnGetCode').on('click', function(){
		  
			// 입력한 이메일
			let email = $('#email').val();
			
		  new Promise(function(resolve, reject){
			
			  
			  // 정규식
			  let regEmail = /^[a-zA-Z0-9-_]+@[a-zA-z0-9]{2,}(\.[a-zA-z]{2,6}){1,2}$/;
			  
			  // 정규식 검사
			  verifyEmail = regEmail.test(email);
			  if(verifyEmail == false){
				  reject(1);  // catch 메소드에 정의된 function 호출, 인수로 2 전달
				  return;
			  }
			  
			  // 이메일 중복 체크
			  $.ajax({
				  type: 'get',
				  url: '${contextPath}/user/verifyEmail.do',
				  data: 'email=' + email,
				  dataType: 'json',
				  success: function(resData){
					  if(resData.enableEmail) resolve();
					  else reject(2);
				  }
			  })
			  
		  }).then(function(){
			  
			  // 이메일로 인증 번호를 보내는 ajax
			  $.ajax({
				  type: 'get',
				  url: '${contextPath}/user/sendAuthCode.do',
				  data: 'email=' + email,
				  dataType: 'json',
				  success: function(resData){   // resData = {"authCode": "6T43G9"} 사용자에게 전송한 인증코드
					  
					  // 메일로 받은 인증코드 입력 후 인증하기 버튼을 클릭한 경우
					  $('#btnVerifyCode').on('click', function(){
						  
						  verifyEmail = resData.authCode == $('#authCode').val();
						  // 사용자에게 전송한 인증코드 == 사용자가 입력한 인증코드
						  if(verifyEmail){ 
							  alert('인증되었습니다.');
						  } else {
							  alert('인증에 실패했습니다.');
						  }
						  
					  })
					  
				  },
				  error: function(jqXHR){
					  alert('인증번호가 발송되지 않았습니다.');
					  verifyEmail = false;
				  }
			  })
			  
		  }).catch(function(number){
			  let msg = '';
			  switch(number){
			  case 1:
				  msg = '이메일 형식이 올바르지 않습니다.'; // 정규식 실패
				  break;
			  case 2:
				  msg = '이미 사용 중인 이메일입니다.'; // 이메일 중복 체크 실패
				  break;
			  }
			  $('#msgEmail').text(msg);
			  verifyEmail = false;
		  })
		  
	  })
	  
	  /*
	  new Promise(function(resolve, reject){
		  
		  reject(1); // 정규식 실패
		  
		  $.ajax({
			  success: function(){
				  resolve();  // 이메일 중복 체크 통과
				  reject(2);  // 이메일 중복 체크 실패
			  }
		  })
	  }).then(function(){  // resolve() 함수 호출할 때 처리되는 함수(ajax - success)
		  // 인증메일전송
		  $.ajax({
			  
		  })
	  }).catch(function(number){ // reject() 함수 호출할 때 처리되는 함수(ajax - error)
		  // 정규식 실패 메시지 + 이메일 중복 체크 실패 메시지
		  // number == 1 인 경우 정규식 실패 메시지
		  // number == 2 인 경우 이메일 중복 체크 실패 메시지
	  })
	  */
  }
  
  // 8. 회원가입
  function fnJoin(){
	  
	  $('#frmJoin').on('submit', function(event){
		  
		  if(verifyId == false){
			  alert('아이디를 확인하세요.');
			  event.preventDefault();
			  return;
		  } else if(verifyPw == false || verifyRePw == false){
			  alert('비밀번호를 확인하세요.');
			  event.preventDefault();
			  return;
		  } else if(verifyName == false){
			  alert('이름을 확인하세요.');
			  event.preventDefault();
        return;
		  } else if(verifyMobile == false){
	        alert('휴대전화번호를 확인하세요.');
	        event.preventDefault();
	        return;
      } else if($('#birthyear').val() == '' || $('#birthmonth').val() == '' || $('#birthdate').val() == ''){
	        alert('생년월일을 확인하세요.');
	        event.preventDefault();
	        return;
      } else if(verifyEmail == false){
	        alert('가입을 위해서 이메일 인증이 필요합니다.');
	        event.preventDefault();
	        return;
	      }
	  })
	  
  }
  
  // 함수 호출
  $(function(){
	  fnCheckId();
	  fnCheckPw();
	  fnCheckEmail();
  })

</script>
</head>
<body>

  <div>
  
    <h1>회원 가입</h1>
  
    <div>* 표시는 필수 입력사항입니다.</div>
    
    <hr>
    
    <form id="frmJoin" method="post" action="${contextPath}/user/join.do">
    
      <!-- agree.jsp에서 전달된 location, event 속성 -->
      <input type="hidden" name="location" value="${location }">
      <input type="hidden" name="event" value="${event }">
      <div>
        <label for="id">아이디*</label>
        <input type="text" name="id" id="id">
        <span id="msgId"></span>
      </div>
      
      <div>
        <label for="pw">비밀번호*</label>
        <input type="password" name="pw" id="pw">
        <span id="msgPw"></span>
      </div>
      
      <div>
        <label for="rePw">비밀번호 확인*</label>
        <input type="password" id="rePw">
        <span id="msgRePw"></span>
      </div>
      
      <div>
        <label for="name">이름*</label>
        <input type="text" name="name" id="name">
      </div>
      
      <div>
        <span>성별*</span>
        <input type="radio" name="gender" id="none" value="NO" checked="checked">
        <label for="none">선택 안함</label>
        <input type="radio" name="gender" id="male" value="M">
        <label for="male">남자</label>
        <input type="radio" name="gender" id="female" value="F">
        <label for="female">여자</label>
      </div>
    
      <div>
        <label for="mobile">휴대전화*</label>
        <input type="text" name="mobile" id="mobile">
        <span id="msgMobile"></span>
      </div>
    
      <div>
        <label for="birthyear">생년월일*</label>
        <select name="birthyear" id="birthyear"></select>
        <select name="birthmonth" id="birthmonth"></select>
        <select name="birthdate" id="birthdate"></select>       
      </div>
      
      <div>
        <input type="text" name="postcode" id="postcode" onclick="execDaumPostcode()" placeholder="우편번호" readonly="readonly">
        <input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
        <input type="text" name="roadAddress" id="roadAddress" placeholder="도로명주소">
        <input type="text" name="jibunAddress" id="jibunAddress" placeholder="지번주소">
        <span id="guide" style="color:#999;display:none"></span>
        <input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소">
        <input type="text" name="extraAddress" id="extraAddress" placeholder="참고항목">
        
        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        <script>
            //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
            function execDaumPostcode() {
                new daum.Postcode({
                    oncomplete: function(data) {
                        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
        
                        // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                        var roadAddr = data.roadAddress; // 도로명 주소 변수
                        var extraRoadAddr = ''; // 참고 항목 변수
        
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                            extraRoadAddr += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if(data.buildingName !== '' && data.apartment === 'Y'){
                           extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if(extraRoadAddr !== ''){
                            extraRoadAddr = ' (' + extraRoadAddr + ')';
                        }
        
                        // 우편번호와 주소 정보를 해당 필드에 넣는다.
                        document.getElementById('postcode').value = data.zonecode;
                        document.getElementById("roadAddress").value = roadAddr;
                        document.getElementById("jibunAddress").value = data.jibunAddress;
                        
                        // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                        if(roadAddr !== ''){
                            document.getElementById("extraAddress").value = extraRoadAddr;
                        } else {
                            document.getElementById("extraAddress").value = '';
                        }
        
                        var guideTextBox = document.getElementById("guide");
                        // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                        if(data.autoRoadAddress) {
                            var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                            guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                            guideTextBox.style.display = 'block';
        
                        } else if(data.autoJibunAddress) {
                            var expJibunAddr = data.autoJibunAddress;
                            guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                            guideTextBox.style.display = 'block';
                        } else {
                            guideTextBox.innerHTML = '';
                            guideTextBox.style.display = 'none';
                        }
                    }
                }).open();
            }
        </script>
      </div>
      
      <div>
        <label for="email">이메일*</label>
        <input type="text" name="email" id="email">
        <input type="button" value="인증번호받기" id="btnGetCode">
        <span id="msgEmail"></span><br>
        <input type="text" id="authCode" placeholder="인증코드 입력">
        <input type="button" value="인증하기" id="btnVerifyCode">
      </div>
      
      <hr>
      
      <div>
        <button>가입하기</button>
        <input type="button" value="취소하기">
      </div>
    
    </form>
  
  </div>
  
</body>
</html>