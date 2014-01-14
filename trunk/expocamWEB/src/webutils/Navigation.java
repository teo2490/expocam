package webutils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import exception.NavigationException;
import expocam.entitybeans.RegisteredUser;

public class Navigation {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	public Navigation(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public void fwd(String destination) throws IOException, ServletException {
		request.getRequestDispatcher(destination).forward(request, response);
	}

	public void redirect(String destination) throws IOException, ServletException {
		redirect(destination, true);
	}
	
	public void redirect(String destination, boolean isLocal) throws IOException, ServletException {
		String prefix = isLocal ? request.getContextPath() : "";
		response.sendRedirect(prefix + destination);
	}
	
	public RegisteredUser getLoggedUser() {
		return (RegisteredUser) request.getSession().getAttribute("utente");
	}
	
	
	public void setLogin(RegisteredUser u) {
		request.getSession().setAttribute("utente", u);
	}
	
	public void setLogout() {
		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
	}
	
	/* Tiny wrapper around request\response objects... */
	@SuppressWarnings("unchecked")
	public <T> T getParam(String name) {
		return (T) request.getParameter(name);
	}
	
	public String[] getParamValues(String name){
		return  request.getParameterValues(name);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String name) {
		return (T) request.getAttribute(name);
	}
	
	public <T> void setAttribute(String name, T value) {
		request.setAttribute(name,  value);
	}
	

	
	public MultipartFormProcesser getMultipart() throws NavigationException {
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Parse the request
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			return new MultipartFormProcesser(items);
		} catch (FileUploadException e) {
			throw new NavigationException(e);
		}
	}

	/* send a 404 error. */
	public void sendNotFound() throws IOException {
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}

	public String getPath() {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
	}

	
}

