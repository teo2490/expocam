package servlet;

import java.io.IOException;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import expocam.sessionbeans.ManagerRegisteredUserRemote;
import expocam.util.ContextUtil;

/**
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		try {
			Object obj = ContextUtil.getInitialContext().lookup("ManagerRegisteredUser/remote");
			ManagerRegisteredUserRemote manager = (ManagerRegisteredUserRemote) PortableRemoteObject.narrow(obj, ManagerRegisteredUserRemote.class);
			String email = request.getParameter("mail");
			String password = request.getParameter("psw");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
						
			boolean exist = false;
			if(manager.esisteMail(email))	exist = true;
			
			if(exist == false)
				manager.aggiungiUtente(email,password,nome,cognome);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher disp;
		disp = request.getRequestDispatcher("Home.jsp");
		disp.forward(request, response);
	}

}
