<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Unify | Welcome...</title>

<!-- Meta -->
<meta charset="UTF-8">
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

<link rel="stylesheet"
	href="<c:url value="/resources/htmlTemplates/unify1_4/assets/plugins/sky-forms/version-2.0.1/css/custom-sky-forms.css" />">



<!-- CSS Theme -->
<link rel="stylesheet"
	href="<c:url value="/resources/htmlTemplates/unify1_4/assets/css/themes/default.css" />"
	id="style_color">

<!-- CSS Customization -->
<link rel="stylesheet"
	href="<c:url value="/resources/htmlTemplates/unify1_4/assets/css/custom.css" />">

</head>

<body>


	<div class="wrapper">



		<!--=== End Header ===-->

		<!--=== Breadcrumbs ===-->
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">Form Sliders</h1>
				<ul class="pull-right breadcrumb">
					<li><a href="index.html">Home</a></li>
					<li><a href="">Features</a></li>
					<li class="active">Forms</li>
				</ul>
			</div>
		</div>
		<!--/breadcrumbs-->
		<!--=== End Breadcrumbs ===-->

		<!--=== Content Part ===-->
		<div class="container content">
			<div class="row">


				<!-- Begin Content -->
				<div class="col-md-6">


					<!-- Last Slider Forms -->
					<%-- <form action="" id="sky-form" class="sky-form label-rounded">
						<header>Rounded Slider Forms</header>

						<fieldset>
							<section> <label class="label rounded-x">Regular
								slider (<span id="slider1-value-rounded">0</span>)
							</label>
							<div id="slider1-rounded"></div>
							</section>

							<section> <label class="label rounded-x">Range
								slider (<span id="slider2-value1-rounded">75</span> - <span
								id="slider2-value2-rounded">300</span>)
							</label>
							<div id="slider2-rounded"></div>
							</section>

							<section> <label class="label rounded-x">Step
								slider (<span id="slider3-value-rounded">0</span>)
							</label>
							<div id="slider3-rounded"></div>
							</section>
						</fieldset>
					</form> --%>
					<!-- End Last Slider Forms -->

					<form:form role="form" method="post" id="sky-form" class="sky-form label-rounded"
						action="/" modelAttribute="orderModel">
						<header>form header</header>
						
						<section> <label class="label rounded-x">Number lines = 
							<span id="slider1-value-rounded">1</span>
						</label>
						<div id="slider1-rounded"></div>
						</section>
						<section>
							total value = <span id="totalValue">5</span>
						</section>
						<section>
							nrOfPricingUnits = 
							<form:input path="nrOfPricingUnits" />
						</section>
					</form:form>

				</div>
				<!-- End Content -->

			</div>
		</div>
		<!--/container-->
		<!--=== End Content Part ===-->



	</div>
	<!--/End Wrapepr-->

	<!-- JS Global Compulsory -->
	<script type="text/javascript"
		src="/resources/htmlTemplates/unify1_4/assets/plugins/jquery-1.10.2.min.js"></script>
	<script type="text/javascript"
		src="/resources/htmlTemplates/unify1_4/assets/plugins/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript"
		src="/resources/htmlTemplates/unify1_4/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!-- JS Implementing Plugins -->
	<script type="text/javascript"
		src="/resources/htmlTemplates/unify1_4/assets/plugins/back-to-top.js"></script>
	<!-- Slider Form -->
	<script
		src="/resources/htmlTemplates/unify1_4/assets/plugins/sky-forms/version-2.0.1/js/jquery-ui.min.js"></script>
	<!-- JS Page Level -->
	<script type="text/javascript"
		src="/resources/htmlTemplates/unify1_4/assets/js/app.js"></script>
	<script type="text/javascript"
		src="/resources/htmlTemplates/unify1_4/assets/js/plugins/form-sliders.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			App.init();
			FormSliders.initFormSliders();
			
			$("#slider1-rounded").mouseup(function(){
				changeMyValue();
			  });
			
		});
		
		function changeMyValue() {
			$("#nrOfPricingUnits").val($("#slider1-value-rounded").text());
		}
	</script>
	


</body>
</html>