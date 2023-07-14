package pds.file;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import pds.model.PdsItem;
import pds.service.AddPdsItemService;

public class FileUploadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String contentType = req.getContentType();
		if (contentType != null
				&& contentType.toLowerCase().startsWith("multipart/")) {
			PdsItem uploadedItem = saveUploadFile(req);
			req.setAttribute("uploadedItem", uploadedItem);
			RequestDispatcher dispatcher = req
					.getRequestDispatcher("/09_upload_class/2_pds/uploaded.jsp");
			dispatcher.forward(req, resp);
		} else {
			RequestDispatcher dispatcher = req
					.getRequestDispatcher("/09_upload_class/2_pds/invalid.jsp");
			dispatcher.forward(req, resp);
		}
	}

	private PdsItem saveUploadFile(HttpServletRequest req) throws IOException,
			ServletException {
		Part descPart = req.getPart("description");
		String description = readParameterValue(descPart);
		Part filePart = req.getPart("file");
		String fileName = getFileName(filePart);
		String realPath = FileSaveHelper.save("C:\\workspaces\\JSP\\SecondJSP\\WebContent\\09_upload_class\\uploadData\\",
				filePart.getInputStream());

		PdsItem addRequest = new PdsItem();
		addRequest.setFileName(fileName);
		addRequest.setFileSize(filePart.getSize());
		addRequest.setDescription(description);
		addRequest.setRealPath(realPath);

		PdsItem pdsItem = AddPdsItemService.getInstance().add(addRequest);
		return pdsItem;
	}

	private String getFileName(Part part) throws UnsupportedEncodingException {
		for (String cd : part.getHeader("Content-Disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	private String readParameterValue(Part part) throws IOException {
		InputStreamReader reader = new InputStreamReader(part.getInputStream(),"UTF-8");
		char[] data = new char[512];
		int len = -1;
		StringBuilder builder = new StringBuilder();
		while ((len = reader.read(data)) != -1) {
			builder.append(data, 0, len);
		}
		return builder.toString();
	}

}
