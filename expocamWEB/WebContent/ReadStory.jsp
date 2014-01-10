<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Read stories</title>
</head>
<%@ page import="expocam.sessionbeans.*" %>
<%@ page import="expocam.entitybeans.*" %>
<%@ page import="java.util.*" %>
<%@ page import="expocam.util.ContextUtil" %>
<%@ page import="javax.rmi.PortableRemoteObject" %>
<body>
<table width="795px" border="0" align="center">
  <tr>
    <td>
	<div align="center"><h3>Read and vote your favorite stories!</h3></div>
    </td>
  </tr>
  <tr>
<!-- QUI -->
    <td>
    	<div align="center">
					
						<h3><u>Here are the stories!</u></h3><br>
					<%
					Object obj = ContextUtil.getInitialContext().lookup("ManagerStory/remote");
  	 				ManagerStoryRemote man = (ManagerStoryRemote) PortableRemoteObject.narrow(obj, ManagerStoryRemote.class);
					RegisteredUser u = (RegisteredUser) request.getSession().getAttribute("user");
					try{
						List<Story> elenco = man.getAllStory();
						if (!elenco.isEmpty())  
				        { 
							//"<p>"+e.getNome()+"</p><img src=\"image/ok.png\" height=\"20px\" width=\"20px\"><img src=\"image/no.png\" height=\"20px\" width=\"20px\" style=\"margin-left: 15px\"><br />"
							for (Story e: elenco)	{ out.println(e.getContent()+"</br><form action=\"VoteStoryServlet\" method=\"post\"><input type=\"submit\" value=\"+1\">");}
				        }
					}catch(Exception e){
						out.println("Non ci sono richieste di aiuto per te!");
					}
					%> 
					<br /><br /><br />
			</div>
    </td>
    </tr>
    </table>
</body>
</html>