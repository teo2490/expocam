<%@page import="expocam.entitybeans.RegisteredUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Write a new Story</title>
<script type="text/javascript" language="javascript">
    function load(){
      AttivaFrame("editArea").designMode = "On";       
    }
    
    function AttivaFrame(iFrameID){
      if (document.getElementById(iFrameID).contentDocument){  
		  //Mozilla
        return document.getElementById(iFrameID).contentDocument;
      } else {
		  //Internet Explorer
        return document.frames[iFrameID].document;
       }
     }
    function applicaComando(cmdStr,valCmdStr){
		if (!document.getElementById("editArea").contentDocument){
		   switch(valCmdStr){
			   case "h1":
				   valCmdStr = "heading 1";
					break;
			   case "h2":
				   valCmdStr = "heading 2";
					break;
			   case "h3":
				   valCmdStr = "heading 3";
					break;
			   case "p":
				   valCmdStr = "paragraph";
					break;
			}
		}
      AttivaFrame("editArea").execCommand(cmdStr,false,valCmdStr);
    } 
    function aggiungiLink(){
      var linkURL = prompt("Inserisci il link da aggiungere:", "");
      AttivaFrame("editArea").execCommand("createLink", false, linkURL);
    }    
    function aggiungiPhoto(photo){
    	var path = "ShowPhotoServlet?id="+photo;
        AttivaFrame("editArea").execCommand("createLink", false, path);
      }   
	 function vediCodice(){
	   var contenuto = AttivaFrame("editArea").body.innerHTML;
      document.getElementById("codice").innerHTML = contenuto.replace(/</g,"&lt;");
    }
    function pulisciCodice(){
	   var contenuto = AttivaFrame("editArea").body.innerHTML;
		contenuto = contenuto.replace(/<br\>/gi,"<br/>");
		contenuto = contenuto.replace(/(<p\>)(.*)(<\/p\>)/gi,"<p>$2</p>");
		contenuto = contenuto.replace(/<\a/gi,"<a");
		contenuto = contenuto.replace(/<\/a\>/gi,"</a>");
		contenuto = contenuto.replace(/<\div\>/gi,"<div>");
		contenuto = contenuto.replace(/<\/div\>/gi,"</div>");
		contenuto = contenuto.replace(/(<strong\>)(.*)(<\/strong\>)/gi,"<span style=\"font-style: bold;\">$2</span>");
		contenuto = contenuto.replace(/(<ul\>)(.*)(<\/ul\>)/gi,"<ul>$2</ul>");
		contenuto = contenuto.replace(/(<li\>)(.*)(<\/li\>)/gi,"<li>$2</li>");
		//internet explorer	
      contenuto = contenuto.replace(/(<font )(color)(=)(#?([A-Fa-f0-9]){3}(([A-Fa-f0-9]){3})?)(>)(.*)(<\/font\>)/gi,"<span style=\"$2:$4;\">$9</span>"); 
		contenuto = contenuto.replace(/(<p )(align)(=)([A-Za-z]*)(>)(.*)(<\/p\>)/gi,"<div style=\"text-align: $4;\">$6</div>");
		contenuto = contenuto.replace(/(<em\>)(.*)(<\/em\>)/gi,"<span style=\"font-style: italic;\">$2</span>");
		//opera
		contenuto = contenuto.replace(/(<font )(color)(=\")(#?([A-Fa-f0-9]){3}(([A-Fa-f0-9]){3})?)(\")(>)(.*)(<\/font\>)/gi,"<span style=\"$2:$4;\">$10</span>"); 
		contenuto = contenuto.replace(/(<div )(align)(=\")([A-Za-z]*)(\")(>)(.*)(<\/div\>)/gi,"<div style=\"text-align:$4;\">$7</div>");		
		contenuto = contenuto.replace(/(<i\>)(.*)(<\/i\>)/gi,"<span style=\"font-style: italic;\">$2</span>");

		document.getElementById("codicePulito").value = contenuto;	
	 }
  </script>  
  <style type="text/css">
  div#pulsantiera {
	background: ButtonFace;
	width: 402px;
	padding: 10px 0;
	text-align: center;
}
  #editArea {
  	width: 400px;
	border: 1px solid #CCC;
  }
  #pulsantiera a {
  background: ButtonFace;
  color: ButtonText;
  border: 1px solid ButtonFace;
  }
  #pulsantiera a.premuto {
  background: ButtonHighlight;
  border: 1px solid;
  border-color: ButtonHighlight ButtonShadow ButtonShadow ButtonHighlight;
  }
  #pulsantiera img {
	border: 0;
  }
  #pulsantiera a:hover {
  border: 1px solid;
  border-color: ButtonHighlight ButtonShadow ButtonShadow ButtonHighlight;
  }
  </style>
</head>
<%@ page import="expocam.sessionbeans.*" %>
<%@ page import="expocam.entitybeans.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.rmi.PortableRemoteObject" %>
<%@ page import="expocam.util.ContextUtil" %>
<body onLoad="load()">
<div align="center"><h3>Write a new Story</h3></div>
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
	<div align="center">
    <form action="SaveStoryServlet" method="POST" id="formEditArea" onSubmit="pulisciCodice()">
	 <input type="hidden" name="codicePulito" id="codicePulito"/>
    <iframe src="" id="editArea" name="editArea" width="300" frameborder="0"></iframe><br/>
	 <div id="pulsantiera">
	    <a href="javascript:void()" onClick="applicaComando('italic')"><img src="image/img/corsivo.gif"/></a>
	    <a href="javascript:void()" onClick="applicaComando('bold')"><img src="image/img/grassetto.gif"/></a>
	    <a href="javascript:void()" onClick="applicaComando('underline')"><img src="image/img/sottolineato.gif"/></a>
	    <a href="javascript:void()" onClick="aggiungiLink()"><img src="image/img/link.gif"/></a>
		 <a href="javascript:void()" onClick="applicaComando('justifyright')"><img src="image/img/allineadestra.gif"/></a>
		 <a href="javascript:void()" onClick="applicaComando('justifyleft')"><img src="image/img/allineasinistra.gif"/></a>
		 <a href="javascript:void()" onClick="applicaComando('justifycenter')"><img src="image/img/allineacentro.gif"/></a>
		 <a href="javascript:void()" onClick="applicaComando('justifyfull')"><img src="image/img/giustifica.gif"/></a>
		 <a href="javascript:void()" onClick="applicaComando('insertorderedlist')"><img src="image/img/listaordinata.gif"/></a>
		 <a href="javascript:void()" onClick="applicaComando('insertunorderedlist')"><img src="image/img/lista.gif"/></a>
		 <a href="javascript:void()" onClick="applicaComando('copy')"><img src="image/img/copia.gif"/></a>
		 <a href="javascript:void()" onClick="applicaComando('paste')"><img src="image/img/incolla.gif"/></a>
		 <a href="javascript:void()" onClick="applicaComando('undo')"><img src="image/img/undo.gif"/></a>
		 <a href="javascript:void()" onClick="applicaComando('redo')"><img src="image/img/redo.gif"/></a>
       <select onChange="applicaComando('formatblock',this.options[this.selectedIndex].value)">	 
          <option>Scegli formato</option>
          <option value="h1">Titolo Grande</option>
          <option value="h2">Titolo Medio</option>
          <option value="h3">Titolo Piccolo</option>
		    <option value="p">Paragrafo</option>
       </select>
       <select onChange="applicaComando('forecolor',this.options[this.selectedIndex].value)">	 
          <option>Scegli un colore</option>
          <option value="#00FF00">Verde</option>
          <option value="#FF0000">Rosso</option>
          <option value="#0000FF">Blu</option>
		    <option value="#000000">Nero</option>
		    <option value="#990000">Rosso Mattone</option>
       </select>
       <%
			Object obja = ContextUtil.getInitialContext().lookup("ManagerImage/remote");
			//ManagerImageRemote mana = (ManagerImageRemote) PortableRemoteObject.narrow(obja, ManagerImageRemote.class);
			RegisteredUser u = (RegisteredUser) request.getSession().getAttribute("utente");
			//List<Image> elencoa = mana.getListMyImage(u);
			List<Photo> elencoa = u.getImage();
			if (!elencoa.isEmpty()) 
	        { 
				out.println("<select onChange=\"aggiungiPhoto(this.options[this.selectedIndex].value)\" name=\"immagine\">");
				out.println("<option value=\"null\">Scegli una foto..</option>");
				for (Photo e: elencoa)	{ 
					out.println("<option value = \""+e.getId()+"\" id=\"abil\" >"+e.getName()+"</option>");
					}
				out.println("</select>");
	        } 
			else {
				out.println("<p>Upload some photos, please!</p>");
			}
			%>
		 <br/>
	 </div>
	 <input type="submit" value="salva"/>
	 </form>
	 </div>
</body>
</html>