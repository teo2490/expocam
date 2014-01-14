package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import expocam.entitybeans.RegisteredUser;
import expocam.sessionbeans.ManagerImageRemote;
import expocam.sessionbeans.ManagerRegisteredUserRemote;
import expocam.util.ContextUtil;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			doPost(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest res, HttpServletResponse response)
			throws ServletException, IOException {
	 
		res.setCharacterEncoding("UTF-8");
        //response.setCharacterEncoding("UTF-8");
		// Commons file upload classes are specifically instantiated
		FileItemFactory factory = new DiskFileItemFactory();
	 
		ServletFileUpload upload = new ServletFileUpload(factory);
		ServletOutputStream out = null;
	 
		try {
			Object obj = ContextUtil.getInitialContext().lookup("ManagerImage/remote");
			ManagerImageRemote manager = (ManagerImageRemote) PortableRemoteObject.narrow(obj, ManagerImageRemote.class);
			RegisteredUser u = (RegisteredUser) res.getSession().getAttribute("utente");
			String name = null;
			// Parse the incoming HTTP request
			// Commons takes over incoming request at this point
			// Get an iterator for all the data that was sent
			List items = upload.parseRequest(res);
			Iterator iter = items.iterator();
	 
			// Set a response content type
			response.setContentType("text/html");
			RequestDispatcher disp;
			disp = res.getRequestDispatcher("Upload.jsp");
			// Setup the output stream for the return XML data
			out = response.getOutputStream();
	 
			// Iterate through the incoming request data
			while (iter.hasNext()) {
				// Get the current item in the iteration
				FileItem item = (FileItem) iter.next();
	 
				// If the current item is an HTML form field
				if (item.isFormField()) {
					// Return an XML node with the field name and value
					//out.println("this is a form data " + item.getFieldName() + "<br>");
					name = item.getString();
					// If the current item is file data
				} else {
					// Specify where on disk to write the file
					// Using a servlet init param to specify location on disk
					// Write the file data to disk
					// TODO: Place restrictions on upload data
					File disk = new File("/home/ingsw2/Desktop/jboss-5.1.0.GA/server/photo/"+item.getName());
					item.write(disk);
					
					res.setAttribute("messaggio", "Photo Uploaded");
					manager.addImage(name, "photo/"+item.getName(), u);
					//disp = res.getRequestDispatcher("Upload.jsp");
					//disp.forward(res, response); 
	 
					// Return an XML node with the file name and size (in bytes)
					out.println("Image uploaded! Back to <a href=\"HomeUtente.jsp\">Home</a>");
					//out.println(getServletContext().getRealPath("/WEB_INF"));
					//out.println("this is a file with name: " + item.getName());
				}
			}
	 
			// Close off the response XML data and stream
	 
			out.close();
			disp.forward(res, response);
			// Rudimentary handling of any exceptions
			// TODO: Something useful if an error occurs
		} catch (FileUploadException fue) {
			fue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
	}
}