<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/view/include/blogheader.jsp" />

<div>
	<a
		href="<c:url value="${pageContext.servletContext.contextPath }/admin/basic"/>">기본설정</a>
	<a
		href="<c:url value="${pageContext.servletContext.contextPath }/admin/category"/>">카테고리</a>
	<a
		href="<c:url value="${pageContext.servletContext.contextPath }/admin/write"/>">글
		작성</a>
</div>


<form id="admin-basic-form" name="registerForm"
	action="<c:url value="${pageContext.servletContext.contextPath }/admin/basic"/>"
	method="post" enctype="multipart/form-data">
	<label for="blogTitle">블로그 제목</label><br> <input type="text"
		name="blogTitle" value="${blogTitle }"><br> <label
		for="logoFile">로고이미지</label>
	<c:choose>
		<c:when test="${empty logoFile}">
			<img id="logo-image" src="<c:url value="/images/default.png" />"
				class=logo width="100px" height="100px">
		</c:when>
		<c:otherwise>
			<img id="logo-image" src="<c:url value="/images/${logoFile}" />"
				class=logo width="100px" height="100px">
		</c:otherwise>
	</c:choose><br>
	<input type="file" name="file" /> <input type="submit" value="기본설정변경">
</form>

<p>
	<span class="text"> </span>
</p>

<jsp:include page="/WEB-INF/view/include/footer.jsp" />

