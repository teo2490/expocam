package servlet;

import java.io.IOException;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import expocam.sessionbeans.ManagerStoryRemote;
import expocam.util.ContextUtil;

/**
 * Servlet implementation class VoteStoryServlet
 */
public class VoteStoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteStoryServlet() {
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
		try {
			Object obj = ContextUtil.getInitialContext().lookup("ManagerStory/remote");
			ManagerStoryRemote manager = (ManagerStoryRemote) PortableRemoteObject.narrow(obj, ManagerStoryRemote.class);
			String id = request.getParameter("id");
			
			manager.addOneVote(id);
		} catch (NamingException e) {
			e.printStackTrace(); 
		}
		/*RequestDispatcher disp;
		request.setAttribute("messaggio", "Aiuto confermato!");
		disp = request.getRequestDispatcher("GestAiuto.jsp");
		disp.forward(request, response);*/
	}
}