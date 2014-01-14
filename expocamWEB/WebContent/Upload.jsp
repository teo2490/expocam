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
<table width="795px" border="0" align="center">
  <tr>
    <td>
	<div align="center"><h3>UPLOAD IMAGE</h3></div>
    </td>
  </tr>
  <tr>
    <td>
		<form method="post" action="UploadPhotoServlet" enctype="multipart/form-data">
	<div class="row">
		<div class="span5">
		
		<label for="name">Name</label>
		<input type="text" name="name" id="name" />		
			
		<label for="file">Profile image (max. 7MB, will be resized and cropped)</label>
		<input type="file" id="file" name="file" />
		
		</div>
		
	</div>
	
	<div class="form-actions">
			<button type="submit">Submit</button>
	</div>
	</form>
    </td>
  </tr>
</table>			
			</div>
		</div>
	</body>
</html>