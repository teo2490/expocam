package servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webutils.ImageUtils;
import webutils.MultipartFormProcesser;
import webutils.Navigation;

import exception.NavigationException;
import exception.NoImageUploadedException;
import expocam.entitybeans.RegisteredUser;
import expocam.sessionbeans.ManagerPhotoRemote;
import expocam.sessionbeans.ManagerRegisteredUserRemote;
import expocam.util.ContextUtil;

/**
 * Servlet implementation class UploadPhotoServlet
 */
public class UploadPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private ManagerRegisteredUserRemote userBean;
	

	private ManagerPhotoRemote imageBean;

	private static final long MAX_ALLOWED_SIZE = 1024 * 1024 * 7; // 7 MB
	private static final int AVATAR_WIDTH_PX = 200;
	private static final int AVATAR_HEIGHT_PX = 200;

	private static final String EDITPROFILE_JSP = "WEB-INF/Upload.jsp";
	private String name;
	byte[] resized = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadPhotoServlet() {
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
		Navigation nav = new Navigation(request, response);
		RegisteredUser updated = nav.getLoggedUser();

		
		MultipartFormProcesser form = null;
		try {
			form = nav.getMultipart();
			form.process();
		} catch (NavigationException e) {
			throw new ServletException(e); // error HTTP 500
		}

		name = form.getValue("name");
		
		RequestDispatcher disp;

		if(form.getFileSize() > 0) {
				try {
					updated = uploadUserImage(updated, form.getFile(), form.getFileSize());
				} catch (NoImageUploadedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		request.setAttribute("messaggio", "Upload Complete");
		disp = request.getRequestDispatcher("HomeUtente.jsp");
		disp.forward(request, response);
		//nav.fwd(EDITPROFILE_JSP);

	}
	private RegisteredUser uploadUserImage(RegisteredUser user, InputStream file, long size) throws NoImageUploadedException{
		Object obj;
		try {
			obj = ContextUtil.getInitialContext().lookup("ManagerPhoto/remote");
			ManagerPhotoRemote manager = (ManagerPhotoRemote) PortableRemoteObject.narrow(obj, ManagerPhotoRemote.class);

		if(file == null || size > MAX_ALLOWED_SIZE || user == null) {
			throw new NoImageUploadedException();
		}
		try {
			BufferedImage img = ImageIO.read(file);
			if(img == null) {
				throw new NoImageUploadedException();
			}
			//resized = ImageUtils.getScaledInstance(AVATAR_WIDTH_PX, AVATAR_HEIGHT_PX, img);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, "png", baos );
			baos.flush();
			resized = baos.toByteArray();
			baos.close();
			manager.addImage(name, resized, user);
			return user;
		} catch (IOException e) {
			throw new NoImageUploadedException(e);
		}
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return user;
	}

	
}
