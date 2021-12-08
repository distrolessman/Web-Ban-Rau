package nongsan.webmvc.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import nongsan.webmvc.dto.FileDTO;
import nongsan.webmvc.model.Product;
import nongsan.webmvc.service.IBoardNewService;
import nongsan.webmvc.service.IProductService;
import nongsan.webmvc.service.IStorageStrategy;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/upload-image"})
public class UploadFileController extends HttpServlet {
    final ObjectMapper objectMapper = new ObjectMapper();
    @Inject
    IStorageStrategy storageStrategy;
    @Inject
    IProductService productService;
    @Inject
    IBoardNewService boardNewService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        String id = req.getParameter("id");
        if (path == null) {
            resp.sendRedirect("/");
            return;
        }
        resp.setHeader("Content-Type", "application/json");
        FileDTO fileDTO = new FileDTO();
        if (id == null) {
            try {
                fileDTO = storageStrategy.generateSignedUrl(path);
                objectMapper.writeValue(resp.getOutputStream(), fileDTO);
            } catch (Exception e) {
                objectMapper.writeValue(resp.getOutputStream(), e);
            }
            return;
        } else {
            String imgLink = "";
            switch (path) {
                case "products":
                    imgLink = productService.findProductById(id).getImage_link();
                    break;
                case "boardnews":
                    imgLink = boardNewService.findBoardNewById(id).getImage_link();
                    break;
                default:
                    imgLink = null;
                    objectMapper.writeValue(resp.getOutputStream(), imgLink);
                    break;
            }
            try {
                fileDTO = storageStrategy.generateSignedUrlUpdate(imgLink);
                objectMapper.writeValue(resp.getOutputStream(), fileDTO);
            } catch (Exception e) {
                objectMapper.writeValue(resp.getOutputStream(), e);
            }
            return;
        }
    }
}
