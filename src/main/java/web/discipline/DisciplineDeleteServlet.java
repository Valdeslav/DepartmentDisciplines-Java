package web.discipline;

import ioc.IoCContainer;
import ioc.IoCException;
import service.DisciplineService;
import service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisciplineDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
            try(IoCContainer ioc = new IoCContainer()) {
                DisciplineService service = ioc.get(DisciplineService.class);
                service.delete(id);
            } catch (ServiceException | IoCException e) {
                throw new ServletException(e);
            }
        } catch (NumberFormatException e ){}
        resp.sendRedirect(getServletContext().getContextPath() + "/discipline/list.html");
    }
}
