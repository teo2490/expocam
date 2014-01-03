package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import expocam.entitybeans.RegisteredUser;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //---Impedisce di fare azioni senza essere loggato
        if(request.getSession().getAttribute("utente") instanceof RegisteredUser){
        	RegisteredUser u = (RegisteredUser) request.getSession().getAttribute("utente");
        	if(u.equals(null)){
            	return;
            }
        }
        else if(request.getSession().getAttribute("utente") == null){
        	return;
        }
        //---		
        request.getSession().removeAttribute("utente");
		request.getSession().invalidate();
		request.setAttribute("messaggio", "Logout is done!");
		request.getRequestDispatcher("Home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
