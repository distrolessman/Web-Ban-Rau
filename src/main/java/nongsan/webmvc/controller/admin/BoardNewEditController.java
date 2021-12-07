package nongsan.webmvc.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.model.BoardNew;
import nongsan.webmvc.service.IBoardNewService;
import nongsan.webmvc.service.impl.BoardNewServicesImpl;

public class BoardNewEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IBoardNewService boardNewService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        BoardNew boardnew = boardNewService.findBoardNewById(id);
        if (boardnew != null) {
            req.setAttribute("boardnew", boardnew);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/editboardnew.jsp");
            dispatcher.forward(req, resp);
        } else
            resp.sendRedirect(req.getContextPath() + "/view/client/404.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String id = req.getParameter("new-id");
        String newTitle = req.getParameter("new-title");
        String newContent = req.getParameter("new-content");
        String newImageLink = req.getParameter("new-image_link");
        String newAuthor = req.getParameter("new-author");
        String newCreated = req.getParameter("new-created");
        if (boardNewService.updateBoardNew(id, newTitle, newContent, newImageLink, newAuthor, newCreated))
            resp.sendRedirect(req.getContextPath() + "/admin/new/list");
        else
            resp.sendRedirect(req.getContextPath() + "/view/client/500.jsp");
    }
}
