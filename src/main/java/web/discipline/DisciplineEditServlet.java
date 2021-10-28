package web.discipline;

import domain.Discipline;
import ioc.IoCContainer;
import ioc.IoCException;
import service.DisciplineService;
import service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Provider;

public class DisciplineEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {}
        if(id != null) {
            try(IoCContainer ioc = new IoCContainer()) {
                DisciplineService service = ioc.get(DisciplineService.class);
                Discipline discipline = service.read(id);
                req.setAttribute("discipline", discipline);
            } catch (ServiceException | IoCException e) {
                throw new ServletException();
            }
        }
        req.getRequestDispatcher("/WEB-INF/jsp/discipline/edit.jsp").forward(req, resp);
    }
}
