<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Weather Java</title>
		<link rel="stylesheet" href="/css/style.css" />
	</head>
	
	<body>
		<div class="widget">
			<div class="location">
		        <h1 class="location-timezone">${city}</h1>
		    </div>
		
		    <div class="temperature">
		        <div class="degree-section">
		            <h2 class="temperature-degree">${currentTemp}</h2>
		            <span>F</span>
		        </div>
		       	<div class="weather-icon"><img class="icon" src="/img/${icon}.png" /></div>
		        <div class="temperature-description">${desc}</div>
		    </div>
	    </div>
	    <script src="/js/app.js"></script>
	</body>
</html>