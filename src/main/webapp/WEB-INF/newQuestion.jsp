<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Question</title>
</head>
<body>
	<form:form action="/questions" method="post" modelAttribute="question">
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
	   <input type="submit" value="Create"/>
	</form:form>
	<a href="/questions">Back to Questions</a>
</body>
</html>