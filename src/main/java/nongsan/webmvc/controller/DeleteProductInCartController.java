package nongsan.webmvc.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nongsan.webmvc.model.Item;
import nongsan.webmvc.model.Order;
import nongsan.webmvc.model.Product;
import nongsan.webmvc.service.IProductService;

public class DeleteProductInCartController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    IProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        HttpSession session = req.getSession(true);
        Product product = productService.findProductById(id);
        Order order = (Order) session.getAttribute("order");
        List<Item> listItems = order.getItems();
        for (Item item : listItems) {
            if (item.getProduct().getId().equals(product.getId())) {
                order.setSumPrice(order.getSumPrice() - item.getPrice());
                listItems.remove(item);
                break;
            }
        }
        order.setItems(listItems);
        session.setAttribute("order", order);
        resp.sendRedirect(req.getContextPath() + "/view/client/cart");
        if (order.getSumPrice() == 0) {
            session.setAttribute("sumprice", 0);
        } else {
            session.setAttribute("sumprice", order.getSumPrice());
        }
    }
}
