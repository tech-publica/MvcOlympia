<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>

<head>
<title>List Customers</title>

<!-- reference our style sheet -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Olympia Sport Club Manager</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">


			<input type="button" value="Add Reservation"
				onclick="window.location.href='showFormToAdd'; return false;"
				class="add-button" />

			<!--  add our html table here -->

			<table>
				<tr>
					<th>ID</th>
					<th>Start</th>
					<th>End</th>
					<th>Client</th>
					<th>Court</th>
					<th>Cost</th>
					<th></th>
			
				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="res" items="${reservations}">

					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="/reservation/showFormToUpdate">
						<c:param name="id" value="${res.id}" />
					</c:url>

					<!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/reservation/delete">
						<c:param name="id" value="${res.id}" />
					</c:url>

					<tr>
						<td>${res.id}</td>
						<td>${res.start}</td>
						<td>${res.end}</td>
                        <td>${res.client.name}</td>
						<td>${res.court.name}</td>
						<td>${res.cost}</td>
						<td>
						<a href="${updateLink}">Update</a>
						<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>

					</tr>

				</c:forEach>

			</table>

		</div>

	</div>


</body>

</html>









