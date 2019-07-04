<%@ include file="common/header.jspf" %>
	<a href="/login?lang=en" role="button">English</a>
	<a href="/login?lang=fr" role="button">French</a>
	<div class="container">
		<%
			session = request.getSession(false);
			if (session.getAttribute("username") == null && session.getAttribute("roles") == null) {
				response.sendRedirect("login.jsp");
			}
		%>
		<h3>
			${welcome_name}:
			<%
				out.print(session.getAttribute("username"));
			%>
		</h3>
		<h3>
			${welcome_role}:
			<%
				String roles = session.getAttribute("roles").toString();
				out.print(roles.substring(1, roles.length() - 1));
			%>
		</h3>

		<a class="btn btn-danger" href="/logout" role="button">${welcome_logout}</a>

		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</div>
<%@ include file="common/footer.jspf" %>