<html>
<head>
<title>Insert title here</title>
</head>
<body>
    <h1>Random Number Generator</h1>
    <%
    String[] quotes = {
            "The only way to do great work is to love what you do. - Steve Jobs",
            "Believe you can and you're halfway there. - Theodore Roosevelt",
            "Success is not the key to happiness. Happiness is the key to success. - Albert Schweitzer",
            "In the middle of every difficulty lies opportunity. - Albert Einstein",
            "Don't watch the clock; do what it does. Keep going. - Sam Levenson",
            "The best way to predict the future is to create it. - Peter Drucker",
            "You are never too old to set another goal or to dream a new dream. - C.S. Lewis",
            "The only limit to our realization of tomorrow will be our doubts of today. - Franklin D. Roosevelt",
            "Success is walking from failure to failure with no loss of enthusiasm. - Winston Churchill",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt"
        };
          
            int index = (int)(Math.random() * quotes.length);
            
            out.println(quotes[index]);
    %>
</body>
</html>