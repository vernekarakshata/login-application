<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Users</title>
</head>
<body>
	<form action="rest/login/addusers" method="POST">
		<label>User Name:</label><input type="text" name="name"/><br/><br/>
		<label>Password:</label><input type="password" name="password"/><br><br/>
		<input type="submit" value="Add Users">
	</form>

</body>
</html>