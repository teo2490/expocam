package servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import expocam.entitybeans.Photo;
import expocam.entitybeans.RegisteredUser;
import expocam.sessionbeans.ManagerPhotoRemote;
import expocam.sessionbeans.ManagerRegisteredUserRemote;
import expocam.util.ContextUtil;

/**
 * Servlet implementation class ShowPhotoServlet
 */
public class ShowPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Object obj = ContextUtil.getInitialContext().lookup("ManagerPhoto/remote");
			ManagerPhotoRemote manager = (ManagerPhotoRemote) PortableRemoteObject.narrow(obj, ManagerPhotoRemote.class);
			String id = request.getParameter("id");
			int idd = Integer.parseInt(id);
			Photo p = manager.getPhotoByID(idd);

			response.setContentType("image/jpg");
			OutputStream o = response.getOutputStream();
			o.write(p.getImage());
			o.flush();
			o.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
