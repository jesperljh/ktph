/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.ProductDAO;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jesperlim
 */
@WebServlet(name = "UpdateReportStatusServlet", urlPatterns = {"/UpdateReportStatusServlet"})
public class UpdateReportStatusServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        int id = Integer.parseInt(request.getParameter("id"));
        String service = request.getParameter("service");
        PrintWriter out = response.getWriter();
        if (service.equals("report")) {
            String responseText = "<button type=\"button\" disabled class=\"btn btn-danger pull-right\" style=\"margin-top: 10px; margin-right: 5px\">Reported!</button>";
            ProductDAO productDAO = new ProductDAO();
            productDAO.UpdateReport(id);
            Product productInfo = productDAO.getProductDetail(id);
            if (productInfo.isReport()) {
                responseText = "<button type=\"button\" disabled class=\"btn btn-danger pull-right\" style=\"margin-top: 10px; margin-right: 5px\">Reported!</button>";
            }
            out.println(responseText);
            out.close();
            return;
        }
        if (service.equals("delete")) {
            ProductDAO productDAO = new ProductDAO();
            productDAO.DeleteProduct(id);
            response.sendRedirect("admin.jsp");
            return;
        }
        if (service.equals("ignore")) {
            ProductDAO productDAO = new ProductDAO();
            productDAO.UpdateReport(id);
            Product productInfo = productDAO.getProductDetail(id);
            response.sendRedirect("admin.jsp");
            return;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
