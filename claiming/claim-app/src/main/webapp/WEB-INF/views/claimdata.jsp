<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add OT data</title>
</head>
<body>
	<a href="claimresults">Check how to claim</a><br />
	<form:form modelAttribute="claimDto" method="POST">
		<table border="1">
			<tbody>
				<tr>
					<th>Account</th>
					<th>Start Date</th>
					<th>Start Hour</th>
					<th>Standby Hours</th>
					<th>End Date</th>
					<th>End Hour</th>
					<th>Standby Hours</th>
				</tr>
				<tr>
					<td>
						<form:input path="accountName" id="accountName" />
						<c:if test="${pageContext.request.method=='POST'}"><form:errors path="accountName"/></c:if>				
					</td>
					<td>
						<form:input path="startTime" id="startTime" placexmlns="for e.g. customer#1"/>
						<c:if test="${pageContext.request.method=='POST'}"><form:errors path="startTime" /></c:if>
					</td>
					<td>
						<form:input path="stHour" id="stHour"/>
						<c:if test="${pageContext.request.method=='POST'}"><form:errors path="stHour"/></c:if>
					</td>
					<td>
						<form:input path="sb1Day" id="sb1Day"/>
						<c:if test="${pageContext.request.method=='POST'}"><form:errors path="sb1Day"/></c:if>	
					</td>
					<td>
						<form:input path="endTime" id="endTime"/>
						<c:if test="${pageContext.request.method=='POST'}"><form:errors path="endTime"/></c:if>
					</td>
					<td>
						<form:input path="endHour" id="endHour"/>
						<c:if test="${pageContext.request.method=='POST'}"><form:errors path="endHour"/></c:if>
					</td>
					<td>
						<form:input path="sb2Day" id="sb2Day"/>
						<c:if test="${pageContext.request.method=='POST'}"><form:errors path="sb2Day"/></c:if>	
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="Dodaj!" /></td>
				</tr>
			</tbody>	
		</table>
	</form:form>
</body>
</html>