<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Random Number Generator</title>
</head>
<body>
    <h1>Random Number Generator</h1>
    <%
        // Generate a random number between 1 and 100
                // Generate a random number
   //     double randomNumber = Math.random();
        java.util.Random random = new java.util.Random();
        int randomNumber = random.nextInt(100) + 1; // Generates a random number between 1 and 100 (inclusive)
    %>
    



    <p>Random Number: <%= randomNumber %></p>
</body>
</html>
