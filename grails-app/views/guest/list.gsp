<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Who can I invite for some food and drinks?</title>
</head>
<body>
    <table>
        <tr>
            <td>Name</td>
            <td>User ID</td>
        </tr>
        <g:each in="${guests}" var="guest">
        <tr>
            <td>${guest.name}</td>
            <td>${guest.userId}</td>
        </tr>
        </g:each>        
    </table>
</body>
</html>