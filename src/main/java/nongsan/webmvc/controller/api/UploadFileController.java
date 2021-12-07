package nongsan.webmvc.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import nongsan.webmvc.dto.FileDTO;
import nongsan.webmvc.model.Product;
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
            try {
                Product product = productService.findProductById(id);
                fileDTO = storageStrategy.generateSignedUrlUpdate(product.getImage_link());
                objectMapper.writeValue(resp.getOutputStream(), fileDTO);
            } catch (Exception e) {
                objectMapper.writeValue(resp.getOutputStream(), e);
            }
            return;
        }
    }
}
