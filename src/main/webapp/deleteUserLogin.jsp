<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Users</title>
<script src="http://code.jquery.com/jquery-1.7.js" type="text/javascript"></script>

<script type="text/javascript" >
function deleteUserData() {
	
	$(document).ready(function() {

        $("#deleteUser").submit(function(e) {
            e.preventDefault();
            $.ajax({
                type : "DELETE",
                url : "http://localhost:8080/LoginApplication/rest/login/deleteusers",
                data : $("#deleteUser").serialize(),
                success : function(response) {
                	document.getElementById("delete_status").innerHTML = response;
                },
                error: function (error) {
                	document.getElementById("delete_status").innerHTML = "UnSuccessfull in Deletion";
                }
            });
            e.preventDefault();
        });

    });
	
 }
</script>
</head>
<body>
	<form name="deleteUser" id="deleteUser">
		<label>User Name:</label><input type="text" name="name" id="name"/><br/><br>
		<button onclick="deleteUserData()">Delete User</button>
		
	</form>
	<br>
	<div id="delete_status"></div>

</body>
</html>