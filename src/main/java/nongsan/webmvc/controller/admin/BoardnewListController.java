package nongsan.webmvc.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.model.BoardNew;
import nongsan.webmvc.service.IBoardNewService;
import nongsan.webmvc.service.impl.BoardNewServicesImpl;

//@WebServlet(urlPatterns = { "/admin/new/list" })
public class BoardnewListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IBoardNewService newService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BoardNew> boardnewList = newService.findAllBoardNew();
        req.setAttribute("boardnewlist", boardnewList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/show-new.jsp");
        dispatcher.forward(req, resp);
    }
}
