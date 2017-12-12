package kmitl.afinal.seproject.servlet;

import kmitl.afinal.seproject.dao.SheetDao;
import kmitl.afinal.seproject.dao.SheetPdfDao;
import kmitl.afinal.seproject.model.Sheet;
import kmitl.afinal.seproject.model.SheetPdf;
import kmitl.afinal.seproject.model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@MultipartConfig
@WebServlet(name = "UploadServlet", urlPatterns = "/upload")
public class UploadServlet extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private long maxFileSize = 500000 * 1024; // 50 Mb.
    private int maxMemSize = 4 * 1024; // 4 kb.
    private File file;

    public void init() {
        // Get the file location where it would be stored.
        filePath = getServletContext().getRealPath("/");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (!isMultipart) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File(filePath));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List<FileItem> fileItems = upload.parseRequest(request);

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");

            Sheet sheet = uploadSheetInfo(fileItems, request);

            if (sheet.getType().equals("pdf")) {
                uploadPdfFile(fileItems, sheet, out);
            } else {
                out.println("no supported file type.");
            }

            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Sheet uploadSheetInfo(List<FileItem> fileItems, HttpServletRequest request) throws SQLException {
        Sheet sheet = new Sheet();

        for (FileItem item : fileItems) {
            if (item.isFormField()) {

                String fieldName = item.getFieldName();
                String fieldValue = item.getString();

                if ("type".equals(fieldName)) {
                    sheet.setType(fieldValue);
                } else if ("title".equals(fieldName)) {
                    sheet.setTitle(fieldValue);
                } else if ("faculty".equals(fieldName)) {
                    sheet.setFaculty_id(fieldValue.equals("0") ? null : Integer.parseInt(fieldValue));
                } else if ("department".equals(fieldName)) {
                    sheet.setDepartment_id(fieldValue.equals("0") ? null : Integer.parseInt(fieldValue));
                } else if ("branch".equals(fieldName)) {
                    sheet.setBranch_id(fieldValue.equals("0") ? null : Integer.parseInt(fieldValue));
                } else if ("subject".equals(fieldName)) {
                    sheet.setSubject_id(fieldValue.equals("0") ? null : fieldValue);
                }
            }
        }

        User user = (User) request.getSession().getAttribute("user");
        sheet.setCreate_by(user.getUsername());

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        int id = SheetDao.with(connection).insert(sheet);
        sheet.setId(id);

        return sheet;
    }

    private void uploadPdfFile(List<FileItem> fileItems, Sheet sheet, PrintWriter out) throws Exception {
        File dir = new File(filePath + "sheet" + File.separator + "pdf" + File.separator);
        String fileName = sheet.getId() + ".pdf";

        for (FileItem item : fileItems) {
            if (!item.isFormField()) {
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                file = new File(dir + File.separator + fileName);
                item.write(file);

                Connection connection = (Connection) getServletContext().getAttribute("connection");

                SheetPdf sheetPdf = new SheetPdf();
                sheetPdf.setSheetId(sheet.getId());
                sheetPdf.setPath(File.separator + "sheet" + File.separator + "pdf" + File.separator + fileName);

                SheetPdfDao.with(connection).insert(sheetPdf);

                out.println("Uploaded Filename: " + fileName + " at " + dir + File.separator + "<br>");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("GET method used with " + getClass().getName() + ": POST method required.");
    }
}
