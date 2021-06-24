<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
	rel="stylesheet" 
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
	crossorigin="anonymous">
<title>Question Profile</title>
</head>
<body>
	<h1><c:out value="${question.text}"></c:out></h1>
	<h3>Tags: <c:out value="${question.tagsInput}"></c:out></h3>
	<a href="/questions/${question.id}/edit">Edit Question</a>
	<div style="display: flex; justify-content: center;">
		<div style="margin-right: 200px;">
			<table class="table" style="outline: solid black 1px">
				<thead>
					<tr>
						<th>Answer</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${answers}" var="answer">
						<tr>
							<td><c:out value="${answer.content}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div>
			<h3>Add Your Answer</h3>
			<form:form action="/questions/${question.id}/answer" method="post" modelAttribute="answer">
				<form:input type="hidden" value="${question.id}" path="question"/>
			    <p>
			        <form:label path="content">Answer:</form:label>
			        <form:errors path="content"/>
			        <form:textarea path="content"/>
			    </p>
			   <input type="submit" value="Answer It!"/>
			</form:form>
		</div>
	</div>
	<a href="/questions">Back to Questions</a>	
</body>
</html>