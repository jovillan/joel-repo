<html>
<head>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript">
 		$( document ).ready(function() {
 			$( "#manager" ).click(function() {
 				window.location.href("/jspring-sec-client/client/admin");
 			});

 			$( "#resource" ).click(function() {
 				window.location.href("/jspring-sec-client/client/user");
 			});

 			$( "#caplogin" ).click(function() {
 				window.location.href("/jspring-sec-client/client/loginpage");
 			});
 		});
 	</script>
</head>
<body>
<h2>Application Security Home page</h2>

<button id="manager">manager</button>
<button id="resource">resource</button>
<button id="caplogin">cap login</button>

</body>
</html>
