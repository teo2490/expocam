<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign up</title>
<script type="text/javascript">
		/* funzione Controllo Inserimento Dati*/
		function check() {
			if(document.getElementById("mail").value == "" ||
				document.getElementById("psw").value == "" ||
				document.getElementById("nome").value == "" ||
				document.getElementById("cognome").value == "" ) {
		  		alert("Uno o più campi sono incompleti.");
		 		return false;
			}
			return true;
		}
		</script>
</head>
<body>
	<td>
    	<div align="center">
    	<form action="SignUpServlet" method="post" onSubmit="return check()">
					<fieldset>
					<h3><u>REGISTRATI</u></h3><br>
						<label for="id">Email:</label>
						<br />
						<input type="text" name="mail" id="mail" />
						<br />
						<label for="password">Password:</label>
						<br />
						<input type="password" name="psw" id="psw" />
                        <br />
                        <label for="nome">Nome:</label>
                        <br />
                        <input type="text" name="nome" id="nome" />
                        <br />
                        <label for="cognome">Cognome:</label>
                        <br />
                        <input type="text" name="cognome" id="cognome" />
                        <br /><br />
					    <input type="submit" name="submit2" value="OK" />
					</fieldset>
		</form>
		</div>
    </td>
</body>
</html>