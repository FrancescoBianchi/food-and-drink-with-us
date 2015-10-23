<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Who can I invite for some food and drinks?</title>
</head>
<body>
	<h1>Who can I invite for some food and drinks?</h1>
	<div id="guests">
	<g:each in="${guests}" var="guest">
		<div class="guest">
			<div class="image"><img src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg"></></div>
			<div class="description">${guest.name} (${guest.userId})</div>
		</div> 
	</g:each>
	</div>
</body>
</html>