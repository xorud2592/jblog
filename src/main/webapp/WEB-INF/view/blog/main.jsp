<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/view/include/blogheader.jsp" />

<div>
	<c:choose>
		<c:when test="${empty logoFile}">
			<img id="logo-image" src="<c:url value="/images/default.png" />"
				class=logo width="100px" height="100px">
		</c:when>
		<c:otherwise>
			<img id="logo-image" src="<c:url value="/images/${logoFile}" />"
				class=logo width="100px" height="100px">
		</c:otherwise>
	</c:choose>
	<br>
	<ul>
		카테고리
		<c:forEach items="${categoryList }" var="vo">
			<li><a href="${pageContext.servletContext.contextPath }/${url }/category/${vo.cateNo }">${vo.cateName}</a> </li>
		</c:forEach>
	</ul>
</div>

<div>
	<p>${postContent }</p>
</div>


<ul>
		<c:forEach items="${postList }" var="vo">
			<li><a href="${pageContext.servletContext.contextPath }/${url }/post/${vo.postNo }">${vo.postTitle }</a> </li>
		</c:forEach>
</ul>

<div id="footer">
	<p>${url }의블로그</p>
</div>
<jsp:include page="/WEB-INF/view/include/footer.jsp" />