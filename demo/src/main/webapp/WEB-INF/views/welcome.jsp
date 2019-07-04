<%@ include file="common/header.jspf" %>
	<div class="container">
		<%
			session = request.getSession(false);
			if (session.getAttribute("username") == null && session.getAttribute("roles") == null) {
				response.sendRedirect("login.jsp");
			}
		%>
		<h3>
			Name:
			<%
				out.print(session.getAttribute("username"));
			%>
		</h3>
		<h3>
			Role:
			<%
				String roles = session.getAttribute("roles").toString();
				out.print(roles.substring(1, roles.length() - 1));
			%>
		</h3>

		<a class="btn btn-danger" href="/logout" role="button">Logout</a>

		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</div>
<%@ include file="common/footer.jspf" %>