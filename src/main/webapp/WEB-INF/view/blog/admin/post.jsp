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

<form id="admin-post-form" name="post-Form"
	action="<c:url value="${pageContext.servletContext.contextPath }/admin/write"/>"
	method="post">
	<h3>새로운 카테고리 추가</h3>
	<label for="postTitle">제목</label> <input type="text" name="postTitle" />
	<select name="cateNo">
		<c:forEach items="${list }" var="vo">
			<option value="${vo.cateNo }">${vo.cateName }</option>
		</c:forEach>
	</select> <br>
	<label for="postContent">내용</label>
	<textarea rows="10" cols="50" name="postContent"></textarea><br> 
	<input type="submit" value="포스트 하기 추가">
</form>
<jsp:include page="/WEB-INF/view/include/footer.jsp" />