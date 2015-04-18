<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap2.css">	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/navbar.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/divider.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/button.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/form.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/page.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/step.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
	
	<script src="<%=request.getContextPath() %>/js/jquery.js"></script>
	<script src="<%=request.getContextPath() %>/js/basic.js"></script>
	<script src="<%=request.getContextPath() %>/js/register.js"></script>
</head>
<body>
    <%@ include file="../topbar.jsp" %>
	<div class="container">
		<div class="row">
			<div class="span9 offset2">				
					<div class="row">
						<div class="span4 offset2">
							<div class="registerArea">
								<div class="ui form">
								  <div class="field">
								    <label>用户名<span id="usernameTip" class="tip"></span></label>
								    <input type="text" name="username" id="username">
								  </div>							
								  <div class="field">
								    <label>邮箱<span id="emailTip" class="tip"></span></label>
								    <input type="text" name="email" id="email">
								  </div>
								  <div class="field">
								    <label>密码<span id="passwordTip" class="tip"></span></label>
								    <input type="password" name="password" id="password">
								  </div>
								  <div class="field">
								    <label>确认密码<span id="cfmPwdTip" class="tip"></span></label>
								    <input type="password" name="cfmPwd" id="cfmPwd">
								  </div>
	
								  <div class="field">
								  	<div class="ui green button" id="registerBtn">注册</div>
								  </div>								
								</div>				
	
							</div>						
						</div>

					</div>					


			</div>
		</div>
	</div>
</body>
</html>