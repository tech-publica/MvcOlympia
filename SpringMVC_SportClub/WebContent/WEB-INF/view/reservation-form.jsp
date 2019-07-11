<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
<title>Save Reservation</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-reservation-style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Olympia Sport Club Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Reservation</h3>

		<form:form action="saveReservation" modelAttribute="reservationViewModel"
			method="POST">

			<!-- need to associate this data with customer id -->
			<form:hidden path="id" />

			<table>
				<tbody>
					<tr>
						<td><label>Starts at:</label></td>
						<td><form:input type="datetime-local" path="start" /></td>
					</tr>

					<tr>
						<td><label>Ends at:</label></td>
						<td><form:input type="datetime-local" path="end" /></td>
					</tr>

					<tr>
						<td><label>Client id:</label></td>
						<td><form:input path="clientId" /></td>
					</tr>

					<tr>
						<td><label>Court id:</label></td>
						<td><form:input path="courtId" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>


				</tbody>
			</table>


		</form:form>

		<div style=""></div>

		<p>
			<a href="${pageContext.request.contextPath}/customer/list">Back
				to List</a>
		</p>

	</div>

</body>

</html>










