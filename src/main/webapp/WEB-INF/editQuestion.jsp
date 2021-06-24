<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Question</title>
</head>
<body>
	<form:form action="/questions/${question.id}" method="post" modelAttribute="question">
		<input type="hidden" name="_method" value="put">
	    <p>
	        <form:label path="text">Question:</form:label>
	        <form:errors path="text"/>
	        <form:textarea path="text"/>
	    </p>
	    <p>
	        <form:label path="tagsInput">Tags:</form:label>
	        <form:errors path="tagsInput"/>
	        <form:input path="tagsInput"/>
	    </p>
	   <input type="submit" value="Update"/>
	</form:form>
	<a href="/questions">Back to Questions</a>
</body>
</html>