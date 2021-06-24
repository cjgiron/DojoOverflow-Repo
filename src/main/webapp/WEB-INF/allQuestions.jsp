<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Questions Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
	rel="stylesheet" 
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
	crossorigin="anonymous">
</head>
<body>
	<h1>Questions</h1>
	<div style="padding: 50px;">
		<table class="table">
			<thead>
				<tr>
					<th>Questions</th>
					<th>Tags</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${questions}" var="question">
					<tr>
						<td><a href="/questions/${question.id}"><c:out value="${question.text}"></c:out></a></td>
						<td><c:out value="${question.tagsInput}"></c:out></td>
						<td><a href="/questions/${question.id}/edit">Edit</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<span style="margin-left: 50px"><a href="/questions/new">Ask a Question</a></span>

</body>
</html>