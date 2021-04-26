<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jblog</title>

<link rel="stylesheet" href="<c:url value="/asset/css/blog/blog.css" />" />
<script src="<c:url value="/asset/javascript/jquery/jquery-3.6.0.js"/>"
	type="text/javascript"></script>
</head>

<body>
	<div class=title-container>
		<h1>${blogTitle }</h1>

		<div class=nav>
			<c:choose>
				<c:when test="${url == userName}">
					<a href="<c:url value="${userName }/admin/basic" />">내블로그 관리</a>
					<a href="<c:url value="/user/logout" />">로그아웃</a>
				</c:when>

				<c:when test="${!empty authUser}">
					<a href="<c:url value="/user/logout" />">로그아웃</a>
				</c:when>

				<c:otherwise>
					<a href="<c:url value="/user/login" />">로그인</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>