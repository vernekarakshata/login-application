<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Users</title>
<script src="http://code.jquery.com/jquery-1.7.js" 
            type="text/javascript"></script>

<script type="text/javascript" >
	$(document).ready(function(){	
		$("button").click(function(){
			
			$.get('http://localhost:8080/LoginApplication/rest/login/displayusers', function(data, status){
				
				// alert("Data: " + data + "\nStatus: " + status);
				 
				var output = "";
				 
				 $.each(data, function(id,obj){ 
				        $.each(obj, function(key, value){
				            output = output + key + "  :  " + value + "  ";
				        });
				        output = output + "<br><br>";
				    });
				 
				document.getElementById("id1").innerHTML = output;
			});
		});
				
	});


</script>
</head>
<body >
<button id = "Callbutton" type = "button" > Display </button>


<div id="id1"></div>
</body>
</html>