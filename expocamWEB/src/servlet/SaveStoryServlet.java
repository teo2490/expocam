package servlet;

import java.io.IOException;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import expocam.entitybeans.RegisteredUser;
import expocam.sessionbeans.ManagerRegisteredUserRemote;
import expocam.sessionbeans.ManagerStoryRemote;
import expocam.util.ContextUtil;

/**
 * Servlet implementation class SaveStoryServlet
 */
public class SaveStoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveStoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RegisteredUser u = (RegisteredUser) request.getSession().getAttribute("utente");
		try {
			Object obj = ContextUtil.getInitialContext().lookup("ManagerStory/remote");
			ManagerStoryRemote manager = (ManagerStoryRemote) PortableRemoteObject.narrow(obj, ManagerStoryRemote.class);
			String story = request.getParameter("codicePulito");
			
			manager.newStory(story, u);
			
			RequestDispatcher disp;
			request.setAttribute("messaggio", story);
			disp = request.getRequestDispatcher("HomeUtente.jsp");
			disp.forward(request, response);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
