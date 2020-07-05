<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
<script src="http://code.jquery.com/jquery-1.7.js" type="text/javascript"></script>

<script type="text/javascript" >
function updatePasswordData() {
	
	$(document).ready(function() {

        $("#updatePassword").submit(function(e) {
            e.preventDefault();
            $.ajax({
                type : "PUT",
                url : "http://localhost:8080/LoginApplication/rest/login/updateusers",
                data : $("#updatePassword").serialize(),
                success : function(response) {
                	document.getElementById("update_status").innerHTML = response;
                },
                error: function (error) {
                	document.getElementById("update_status").innerHTML = "UnSuccessfull in Updation";
                }
            });
            e.preventDefault();
        });

    });
	
 }
</script>
</head>
<body>
	<form name="updatePassword" id="updatePassword">
		<label>User Name:</label><input type="text" name="name" id="name"/><br/><br>
		<label>Password:</label><input type="password" name="password" id="password"/><br><br>
		<button onclick="updatePasswordData()">Update Password</button>
		<div id="update_status"></div>
	</form>

</body>
</html>