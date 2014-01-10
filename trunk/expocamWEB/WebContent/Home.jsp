<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<style type="text/css">
			@import url(css/main.css);
</style>
<script type="text/javascript">
		/* funzione Controllo Inserimento Dati*/
		function check() {
			if(document.getElementById("id").value == "" ||
				document.getElementById("password").value == "") {
		  		alert("Uno o più campi sono incompleti.");
		 		return false;
			}
			return true;
		}
		</script>
</head>
<%-- <%@ page import="expocam.sessionbeans.*" %>
<%@ page import="expocam.entitybeans.*" %>
<%@ page import="java.util.*" %>
<%@ page import="expocam.util.ContextUtil" %> --%>
<%@ page import="javax.rmi.PortableRemoteObject" %>
<body>
<div>			
  <tr>
  	<td>
		<div align="center"><img src="image/expocam.png"  width="450" height="300"/></div>
	</td>
  </tr>
			
				<%
					String messaggio = (String) request.getAttribute("messaggio");
					if(messaggio != null) {
						out.println("<p>" + messaggio + "</p>");
					}
				%>
                <table width="795px" border="0" align="center">
  <tr>
    <td>
    	<form action="LoginServlet" method="post" onSubmit="return check()">
    			<div align="center">
					<fieldset>
						<h3><u>LOGIN</u></h3><br>
						<label for="id">E-mail:</label>
						<br />
						<input type="text" name="id" id="id" />
						<br />
						<label for="password">Password:</label>
						<br />
						<input type="password" name="password" id="password" />
						<br /><br />
						<input type="submit" name="submit" value="OK" />
					</fieldset>
				</div>
				<div id="menu">
				        	<a>Are you not registered?</a></br>
				            <a href="ReadStoryForNotRegisteredUser.jsp">Read stories anyway!</a></br>
				            <a href="SignUp.jsp">Sign up!</a>
				</div>
		</form>
    </td>
  </tr>
  
</table>
</div>	
</body>
</html>