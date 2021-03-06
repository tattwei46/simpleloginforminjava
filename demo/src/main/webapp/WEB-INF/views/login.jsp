<%@ include file="common/header.jspf"%>
<body>
	<a href="/login?lang=en" role="button">English</a>
	<a href="/login?lang=fr" role="button">French</a>
	<div class="container">
		<form class="text-center border border-light p-5" action="/login"
			method="post">

			<p class="h4 mb-4">
			
			<h3>${login_signin}</h3>
			</p>

			<!-- Email -->
			<Fieldset>
				<input type="text" name="username" class="form-control mb-4"
					placeholder="${login_username}" required="required">
			</Fieldset>
			</br>
			<!-- Password -->
			<FieldSet>
				<input type="password" name="password" class="form-control mb-4"
					placeholder="${login_password}" required="required">
			</FieldSet>
			</br>

			<!-- Sign in button -->
			<button class="btn btn-info btn-block my-4" type="submit">${login_submit}
			</button>


			<h3>
				<font color="red">${errorMessage}</font>
			</h3>

		</form>
		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</div>
	<%@ include file="common/footer.jspf"%>