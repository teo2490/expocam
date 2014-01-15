package servlet;

import java.io.IOException;

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
import expocam.sessionbeans.ManagerStoryRemote;
import expocam.sessionbeans.ManagerTagRemote;
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
			String content = story;
			getPhotoTag(story);
			
			manager.newStory(story, u);
			
			RequestDispatcher disp;
			request.setAttribute("messaggio", "Story Saved");
			disp = request.getRequestDispatcher("HomeUtente.jsp");
			disp.forward(request, response);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private void getPhotoTag (String content) {
		try {
			Object obj = ContextUtil.getInitialContext().lookup("ManagerTag/remote");
			ManagerTagRemote managerTag = (ManagerTagRemote) PortableRemoteObject.narrow(obj, ManagerTagRemote.class);
			String startTag = "\">";
			String finishTag = "</a>";
			String startPic = "id=";
			//String finishPic = "\"";
			String[] tokensTag = content.split(finishTag);
			for(int i=0; i<tokensTag.length; i++){
				if(tokensTag[i].toString().indexOf(startTag)>-1){
					String[] tag = tokensTag[i].toString().split(startTag);
					String tagPic = tag[1].toString();
					System.out.println("--tag-->"+tagPic);
					String[] tokensPic = tag[0].split(startPic);
					String idPic = tokensPic[1].toString();
					System.out.println("--pic-->"+idPic);
					managerTag.addNewTag(idPic, tagPic);
				}
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
