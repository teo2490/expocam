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
<body onLoad="load()">
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
	<br /><br />
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
					RegisteredUser u = (RegisteredUser) request.getSession().getAttribute("utente");
					Object ob = ContextUtil.getInitialContext().lookup("ManagerStory/remote");
					ManagerStoryRemote manager = (ManagerStoryRemote) PortableRemoteObject.narrow(ob, ManagerStoryRemote.class);
					try{
						List<Story> elenco = man.getAllStory();
						//out.println("ecco elenco: "+elenco.get(0));
						if (!elenco.isEmpty())  
				        { 
							//"<p>"+e.getNome()+"</p><img src=\"image/ok.png\" height=\"20px\" width=\"20px\"><img src=\"image/no.png\" height=\"20px\" width=\"20px\" style=\"margin-left: 15px\"><br />"
							for (Story e: elenco){
								String idStory = Integer.toString(e.getId());
								out.println(e.getContent());
								out.println("</br><div>");
								if(!manager.storyAlreadyVoted(u, idStory)) {
									out.println("<form action=\"VoteStoryServlet\" method=\"post\" ><input type=\"hidden\" name=\"id\" id=\"id\" value=\""+e.getId()+"\"><input type=\"submit\" name =\"submit\" value=\"Vote!\"></form>");
								}
								if(!manager.storyAlreadyReported(u, idStory)) {
								out.println("<form action=\"ReportStoryServlet\" method=\"post\" ><input type=\"hidden\" name=\"id\" id=\"id\" value=\""+e.getId()+"\"><input type=\"submit\" name =\"submit\" value=\"Report\"></form>");
								}
								out.println("</div></br></br>");
							}
				        }
					}catch(Exception e){
						out.println("There aren't story!");
					}
					%> 
					<br /><br /><br />
			</div>
    </td>
    </tr>
    </table>
</body>
</html>