<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jblog</title>
<link rel="stylesheet" href="<c:url value="/asset/css/main/logoform.css" />" />

<script src="<c:url value="/asset/javascript/jquery/jquery-3.6.0.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/asset/javascript/users.js"/>"></script>
</head>
<body>
	<div class="container">
		<img id="logo-image" src="<c:url value="/images/logo.png" />" class=logo>
		<div class="row">
			<c:choose>
				<c:when test="${empty authUser}">
					<a href="<c:url value="/user/login" />">로그인</a>
					<a href="<c:url value="/usr/join" />">회원가입</a>
				</c:when>
				<c:otherwise>
					<a href="<c:url value="/user/logout" />">로그아웃</a>
					<a href="<c:url value="/blog" />">내블로그</a>
				</c:otherwise>
			</c:choose>
		</div>
		<div></div>
	</div>

	<script>
		document.getElementById("logo-image").onclick = function(event) {
			location.href = "/jblog/";
		};
	</script>