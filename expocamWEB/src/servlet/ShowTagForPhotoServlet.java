package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import expocam.entitybeans.Photo;
import expocam.sessionbeans.ManagerPhotoRemote;
import expocam.sessionbeans.ManagerTagRemote;
import expocam.util.ContextUtil;

/**
 * Servlet implementation class ShowTagForPhotoServlet
 */
public class ShowTagForPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTagForPhotoServlet() {
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
		// TODO Auto-generated method stub
		try {
			Object obj = ContextUtil.getInitialContext().lookup("ManagerPhoto/remote");
			ManagerPhotoRemote manager = (ManagerPhotoRemote) PortableRemoteObject.narrow(obj, ManagerPhotoRemote.class);
			Object obja = ContextUtil.getInitialContext().lookup("ManagerTag/remote");
			ManagerTagRemote m = (ManagerTagRemote) PortableRemoteObject.narrow(obja, ManagerTagRemote.class);
			String id = request.getParameter("id");
			int idd = Integer.parseInt(id);
			Photo p = manager.getPhotoByID(idd);

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			//OutputStream o = response.getOutputStream();
			//out.println(p.getImage());
			out.println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>Read stories</title><script language=\"JavaScript\">function apri(url) { newin = window.open(url,'titolo','scrollbars=no,resizable=yes, width=400,height=400,status=no,location=no,toolbar=no');}</script></head><body>");
			String name = manager.getTag(id);
			List<String> tag = m.getTag(id);
			out.println("<a href=\'javascript:apri(\"ShowPhotoServlet?id="+id+"\");\'>"+name+"</a><br /><br />");
			for(int i=0; i<tag.size(); i++){
				out.println(tag.get(i)+"<br />"); 
			}
			out.println("</body></html>");
			out.flush();
			out.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
