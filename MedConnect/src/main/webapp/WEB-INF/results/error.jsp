<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MedConnect - Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #222831;
            color: #EEEEEE;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .error-container {
            text-align: center;
            background-color: #31363f;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .error-container h1 {
            font-size: 3em;
            margin: 0;
        }

        .error-container p {
            font-size: 1.2em;
            margin: 10px 0;
        }

        .error-container a {
            display: inline-block;
            margin-top: 15px;
            padding: 10px 20px;
            font-size: 1em;
            color: #EEEEEE;
            background-color: #76ABAE;
            border-radius: 4px;
            text-decoration: none;
        }

        .error-container a:hover {
            background-color: rgba(118, 171, 174, 0.81);
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>Oops!</h1>
    <p>Qualcosa è andato storto.</p>
    <p>Error <%= request.getAttribute("statusCode") != null ? request.getAttribute("statusCode") : "Non disponibile" %></p>
    <p>Messaggio Errore: <%= request.getAttribute("exception") != null ? ((Exception) request.getAttribute("exception")).getMessage() : "Non disponibile" %></p>
    <p>Tipo Eccezione: <%= request.getAttribute("exception") != null ? ((Exception) request.getAttribute("exception")).getClass().getName() : "N/A" %></p>
    </p>
    <a href="<%= request.getContextPath() %>">Torna alla Home</a>
</div>
</body>
</html>