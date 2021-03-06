<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico">

<!-- CSS Global Compulsory -->
<link rel="stylesheet"
	href="<c:url value="/resources/htmlTemplates/unify1_4/assets/plugins/bootstrap/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/htmlTemplates/unify1_4/assets/css/style.css" />">

<!-- CSS Implementing Plugins -->
<link rel="stylesheet"
	href="<c:url value="/resources/htmlTemplates/unify1_4/assets/plugins/line-icons/line-icons.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/htmlTemplates/unify1_4/assets/plugins/font-awesome/css/font-awesome.min.css" />">

<!-- CSS Page Style -->
<link rel="stylesheet"
	href="<c:url value="/resources/htmlTemplates/unify1_4/assets/css/pages/page_pricing.css" />">

<!-- CSS Theme -->
<link rel="stylesheet"
	href="<c:url value="/resources/htmlTemplates/unify1_4/assets/css/themes/default.css" />"
	id="style_color">

<!-- CSS Customization -->
<link rel="stylesheet"
	href="<c:url value="/resources/htmlTemplates/unify1_4/assets/css/custom.css" />">
<title>my-smart-line</title>
<!-- Google analytics -->
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-53020812-1', 'auto');
  ga('send', 'pageview');

</script>
</head>
<body>
	<%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String logInOutUrl = userService.createLoginURL("/");
		String logInOutEmail = " ";
		String logInOutText = "login";
		boolean logedIn = false;

		if (user != null) {
			logInOutUrl = userService.createLogoutURL("/");
			logInOutEmail = user.getEmail();
			logInOutText = "log out";
			logedIn = true;
		}
	%>
	<div class="wrapper">
		<!--=== Header ===-->
		<div class="header">
			<!-- Topbar -->
			<div class="topbar">
				<div class="container">
					<!-- Topbar Navigation -->
					<ul class="loginbar pull-right">
						<li><i class="fa fa-globe"></i> <a>Languages</a>
							<ul class="lenguages">
								<li><a href="?lang=en">English </a></li>
								<li><a href="?lang=fr">French</a></li>
								<li><a href="?lang=ro">Romanian</a></li>
								<!-- <li><a href="?lang=ru">Russian</a></li> -->
							</ul></li>
						<li class="topbar-devider"></li>
						<li><%=logInOutEmail%></li>
					</ul>
					<!-- End Topbar Navigation -->
				</div>
			</div>
			<!-- End Topbar -->

			<!-- Navbar -->
			<div class="navbar navbar-default" role="navigation">
				<div class="container">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".navbar-responsive-collapse">
							<span class="sr-only">Toggle navigation</span> <span
								class="fa fa-bars"></span>
						</button>
						<a class="navbar-brand" href="/"> <img id="logo-header"
							src="<c:url value="/resources/htmlTemplates/unify1_4/assets/img/logo1-default.png" />"
							alt="Logo">
						</a>
					</div>
				</div>
			</div>
			<!-- End Navbar -->
		</div>
		<!--=== End Header ===-->
		<!--==== TILES ====-->
		<tiles:insertAttribute name="body" />
		<!--==== End TILES ====-->
		
	</div>
	<!--/wrapper-->

	<!-- JS Global Compulsory -->
	<script type="text/javascript"
		src="<c:url value="/resources/htmlTemplates/unify1_4/assets/plugins/jquery-1.10.2.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/htmlTemplates/unify1_4/assets/plugins/jquery-migrate-1.2.1.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/htmlTemplates/unify1_4/assets/plugins/bootstrap/js/bootstrap.min.js" />"></script>
	<!-- JS Implementing Plugins -->
	
	<!-- JS Page Level -->
	<script type="text/javascript"
		src="<c:url value="/resources/htmlTemplates/unify1_4/assets/js/app.js" />"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			App.init();
		});
	</script>
	
</body>
</html>