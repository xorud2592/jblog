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

<div id="container">
	<div id="wapper">
		<div id="content">
			<div id="site-introduction">
				<table border="1" width="640">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					
					<c:forEach items="${list }" var="vo">
						<tr>
							<td>${vo.cateNo }</td>
							<td>${vo.cateName }</td>
							<td>${vo.postSize }</td>
							<td>${vo.description }</td>
							<td><a href="${pageContext.servletContext.contextPath }/admin/delete/category/${vo.cateNo}">삭제</a></td>
						</tr>
					</c:forEach>
					<!-- /Loop -->
				</table>
			</div>
			<div id="insert-form">
				<form id="admin-category-form" name="Category-Form"
					action="<c:url value="${pageContext.servletContext.contextPath }/admin/category"/>"
					method="post">
					<h3>새로운 카테고리 추가</h3>
					<label for="cateName">카테고리명</label>
					<input type="text" name="cateName" /><br>
					<label for="description">설명</label>
					<input type="text" name="description" /><br>
					<input type="submit" value="카테고리 추가">
				</form>

			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/view/include/footer.jsp" />