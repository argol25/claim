<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Claim data</title>
</head>
<body>
	<a href="claimdata">Provide claim data</a><br />
	<table border="1">
		<thead>
			<tr>
				<th>WBS Element</th>
				<th>OT Type</th>
				<th>Sat</th>
				<th>Sun</th>
				<th>Mon</th>
				<th>Tue</th>
				<th>Wed</th>
				<th>Tue</th>
				<th>Fri</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${results}" var="code" varStatus="status" >
				<tr>
					<td>${code.WBSel}</td>
					<td>${code.OType}</td>
					<td>${code.sat}</td>
					<td>${code.sun}</td>
					<td>${code.mon}</td>
					<td>${code.tue}</td>
					<td>${code.wen}</td>
					<td>${code.thu}</td>
					<td>${code.fri}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>