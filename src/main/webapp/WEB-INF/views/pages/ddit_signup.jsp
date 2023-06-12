<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="../assets/img/favicon.png">
  <title>
    대덕인재개발원 CRUD 연습
  </title>
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
  <!-- Nucleo Icons -->
  <link href="../assets/css/nucleo-icons.css" rel="stylesheet" />
  <link href="../assets/css/nucleo-svg.css" rel="stylesheet" />
  <!-- Font Awesome Icons -->
  <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
  <!-- Material Icons -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
  <!-- CSS Files -->
  <link id="pagestyle" href="../assets/css/material-dashboard.css?v=3.0.4" rel="stylesheet" />
</head>

<body class="">
  <main class="main-content  mt-0">
    <section>
      <div class="page-header min-vh-100">
        <div class="container">
          <div class="row">
            <div class="col-6 d-lg-flex d-none h-100 my-auto pe-0 position-absolute top-0 start-0 text-center justify-content-center flex-column">
              <div class="position-relative bg-gradient-info h-100 m-3 px-7 border-radius-lg d-flex flex-column justify-content-center" style="background-image: url('../assets/img/illustrations/illustration-lock.jpg'); background-size: cover;">
              </div>
            </div>
            <div class="col-xl-4 col-lg-5 col-md-7 d-flex flex-column ms-auto me-auto ms-lg-auto me-lg-5">
              <div class="card card-plain">
                <div class="card-header">
                  <h4 class="font-weight-bolder">회원가입</h4>
                  <p class="mb-0">회원등록 후, 저희 서비스와 함께해요!</p>
                </div>
                <div class="card-body">
                  <form role="form" id="signupForm" action="/singin.do" method="post">
                    <div class="input-group input-group-outline mb-3">
                      <label class="form-label"></label>
                      <input type="text" id="memId" placeholder="아이디" name="memId" class="form-control">
                    </div>
                    <div class="input-group input-group-outline mb-3">
                      <label class="form-label"></label>
                      <input type="text" id="memPw"name="memPw" placeholder="비밀번호" class="form-control">
                    </div>
                    <div class="input-group input-group-outline mb-3">
                      <label class="form-label"></label>
                      <input type="text" id="mem_Pw2" placeholder="비밀번호 재입력" class="form-control">
                    </div>
					<div class="input-group input-group-outline mb-3">
                      <label class="form-label"></label>
                      <input type="text" id="memName" placeholder="이름" name="memName" class="form-control">
                    </div>
					<div class="form-check mb-3">
					  <input class="form-check-input gender" name="memGender"  type="radio" name="flexRadioDefault" value="M" checked>
					  <label class="custom-control-label" for="customRadio1">남자</label>
					  <input class="form-check-input gender" name="memGender"  type="radio" name="flexRadioDefault" value="F">
					  <label class="custom-control-label" for="customRadio1">여자</label>
					</div>
					<div class="input-group input-group-outline mb-3">
                      <label class="form-label"></label>
                      <input type="text" id="memPhone" name="memPhone" placeholder="핸드폰번호" class="form-control">
                    </div>
					<div class="input-group input-group-outline mb-3">
                      <label class="form-label"></label>
                      <input type="text" id="memEmail" name="memEmail" placeholder="이메일"  class="form-control">
                    </div>
                    <div class="form-check form-switch">
					  <input class="form-check-input" type="checkbox" name="memAgree"  id="memAgree" >
					  <label class="form-check-label" for="flexSwitchCheckDefault">개인정보 동의</label>
					  <p class="mb-2 text-sm mx-auto">
						개인정보 동의  
						<a href="../pages/sign-in.html" class="text-primary text-gradient font-weight-bold">
						상세보기
						</a>	
					  </p>
					</div>
                    <div class="text-center">
                      <button type="button" id="signUpBtn" class="btn btn-lg bg-gradient-primary btn-lg w-100 mt-4 mb-0">가입하기</button>
                    </div>
                  </form>
                </div>
                <div class="card-footer text-center pt-0 px-lg-2 px-1">
                  <p class="mb-2 text-sm mx-auto">
                    우리 서비스 회원이세요?
                    <a href="/login.do" class="text-primary text-gradient font-weight-bold">로그인</a>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
  <!--   Core JS Files   -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
  <script src="../assets/js/core/bootstrap.min.js"></script>
</body>

<script type="text/javascript">

$(function(){
	
	if("${flag}" == "missing"){
		alert("누락된 입력 정보가 존재합니다.")
	}
	
	if("${flag}" == "error"){
		alert("회원가입 실패!")
	}
	
	$("#signUpBtn").on("click", function(){
		mem_Id = $("#memId").val();
		mem_Pw = $("#memPw").val();
		mem_Pw2 = $("#mem_Pw2").val();
		mem_Name = $("#memName").val();
		mem_Phone = $("#memPhone").val();
		mem_Email = $("#memEmail").val();
		mem_Agree = $("#mem_Agree");
			
			
		if(mem_Id == ""){
			alert("아이디를 입력해주세요");
			$("#memId").focus();
			return false;
		}
		if(mem_Pw == ""){
			alert("비밀번호를 입력해주세요");
			$("#memPw").focus();
			return false;
		}
			
		if(mem_Pw2 == ""){
			alert("비밀번호 재입력을 입력해주세요");
			$("#mem_Pw2").focus();
			return false;
		}
		
		if(mem_Pw2 != mem_Pw){
			alert("비밀번호가 같지 않습니다.");
			return false;
		}
			
		if(mem_Name == ""){
			alert("이름을 입력해주세요");
			$("#memName").focus();
			return false;
		}
			
		if(mem_Phone == ""){
			alert("‘핸드폰번호를 입력해주세요");
			$("#memPhone").focus();
			return false;
		}
			
		if(mem_Email == ""){
			alert("이메일을을 입력해주세요");
			$("#memEmail").focus();
			return false;
		}
		
		if(!mem_Agree.is(':checked')){
			alert("개인정보 동의를 체크해주세요");
			return false;
		}
			
		$("#signupForm").submit()
	})
	

		
	})
	
	
	
</script>



</html>