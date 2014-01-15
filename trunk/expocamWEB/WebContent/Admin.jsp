<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Upload Image</title>
		<style type="text/css">
			@import url(css/main.css);
		</style>	
	</head>
	<%@ page import="expocam.sessionbeans.*" %>
<%@ page import="expocam.entitybeans.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.rmi.PortableRemoteObject" %>
<%@ page import="expocam.util.ContextUtil" %>
	<body>
		<div id="content">
			<div id="subcontent">
				<div id="header">
					<div id="logo">
						&nbsp;
					</div>					
					<div id="menu">
				        <ul>
				            <li><a href="LogoutServlet">Logout</a></li>
				            <li><a href="HomeUtente.jsp">Home</a></li>					            
				        </ul>
				    </div>
				</div>
				<%
					String messaggio = (String) request.getAttribute("messaggio");
					if(messaggio != null) {
						out.println("<p>" + messaggio + "</p>");
					}
				%>
				<br/><br/><br/>
<form action="ShowTagForPhotoServlet" method="post">
<table width="795px" border="0" align="center">
  <tr>
    <td>
	<div align="center"><h3>TAG</h3></div>
    </td>
  </tr>
  <tr>
    <td>
    <%
    Object obja = ContextUtil.getInitialContext().lookup("ManagerPhoto/remote");
	ManagerPhotoRemote manp = (ManagerPhotoRemote) PortableRemoteObject.narrow(obja, ManagerPhotoRemote.class);
	List<String> elencoa = manp.getAllID();
	if (!elencoa.isEmpty()) 
    {  
		out.println("<select name=\"id\">");
		out.println("<option value=\"null\">Choose a photo..</option>");
		for (String e: elencoa)	{ 
			out.println("<option value = \""+e+"\" id=\"abil\" >"+e+"</option>");
			}
		out.println("</select>");
		out.println("<input type=\"submit\" name=\"submit\" value=\"OK\" />");
    } 
	else {
		out.println("<p>No photos in the DB!</p>");
	}
    %>
    </td>
  </tr>
</table>	
</form>		
			</div>
		</div>
	</body>
</html>