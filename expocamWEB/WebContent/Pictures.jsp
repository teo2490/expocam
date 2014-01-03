<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your Pictures</title>
<style type="text/css">
			@import url(css/main.css);
		</style>
</head>
<body>

<tr>
    <td>
    	<form action="PictureServlet" method="post" enctype="multipart/form-data">
    			<div align="center">
					<fieldset>
						<h3><u>NEW PICTURE</u></h3><br>
					    <input type="text" name="name" />
					    <input type="file" name="file" />
					    <input type="submit" />
					</fieldset>
				</div>
		</form>
    </td>
  </tr>

</body>
</html>