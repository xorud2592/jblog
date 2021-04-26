<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/mainheader.jsp" />
<div class="search">
	<form action="">
		<input type="text" width="500px"> <input type="submit"
			value="검색"><br> <input type="radio" name="search"
			value="name">블로그제목 <input type="radio" name="search"
			value="user">블로거
	</form>
</div>

<jsp:include page="/WEB-INF/view/include/footer.jsp" />