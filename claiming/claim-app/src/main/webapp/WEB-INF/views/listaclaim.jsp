<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>List of claim data</title>
	</head>
	<body>
		<a href="<a:url value="claimdata" />"> Add claim data </a>
		<br />
		<table border="1">
			<thead>
				<tr>
					<th>#</th>
					<th>Account</th>
					<th>Start Date</th>
					<th>Start Hour</th>
					<th>Standby Hours</th>
					<th>End Date</th>
					<th>End Hour</th>
					<th>Standby Hours</th>
				</tr>
			</thead>
			<tbody>
				<a:forEach items="${claim}" var="claim" varStatus="status">
					<tr>
						<td>${status.index}</td>
						<td>${claim.accountName}</td>
						<td>${claim.startTime}</td>
						<td>${claim.stHour}</td>
						<td>${claim.sb1Day}</td>
						<td>${claim.endTime}</td>
						<td>${claim.endHour}</td>
						<td>${claim.sb2Day}</td>
					</tr>
				</a:forEach>
			</tbody>
		</table>
	</body>
</html>