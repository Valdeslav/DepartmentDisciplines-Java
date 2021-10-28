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
import java.util.List;

public class DisciplineListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(IoCContainer ioc = new IoCContainer()) {
            DisciplineService service = ioc.get(DisciplineService.class);
            List<Discipline> disciplines = service.readAll();
            req.setAttribute("disciplines", disciplines);
            req.getRequestDispatcher("/WEB-INF/jsp/discipline/list.jsp").forward(req, resp);
        } catch (ServiceException | IoCException e) {
            throw new ServletException(e);
        }

    }
}
