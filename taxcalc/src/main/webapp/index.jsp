<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!doctype html>
<html lang="en-US" ng-app="skeep">
<head>
	<meta charset="UTF-8">
	<title>{{appTitle}}</title>
	<script type="text/javascript" src="lib/angular.js"></script>
	<script type="text/javascript" src="lib/angular-route.js"></script>
	<script type="text/javascript" src="js/app.js"></script>
</head>

<body>
<div ng-controller="skeepC1">
	<nav>
		<div>
			<div>
				<span><a href="#/">Home</a>|<a href="#/modify">Modify Form</a>|<a href="#/addRule">Add Tax Rule</a></span>
				<br/>
				<h1>{{appTitle}}</h1>
				<br/>
			</div>
		</div>
	</nav>
</div>	
<div ng-view>


</div>
</body>

</html>