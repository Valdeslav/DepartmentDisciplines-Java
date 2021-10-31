package web.topic;

import ioc.IoCContainer;
import ioc.IoCException;
import service.ServiceException;
import service.TopicService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TopicDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        Long disciplineId = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
            try(IoCContainer ioc = new IoCContainer()) {
                TopicService service = ioc.get(TopicService.class);
                service.delete(id);
            } catch(ServiceException | IoCException e) {
                throw new ServletException(e);
            }
        } catch (NumberFormatException e) {}
        try {
            disciplineId = Long.parseLong(req.getParameter("disciplineId"));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ожидался параметр disciplineId!");
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/topic/list.html?disciplineId=" + disciplineId);
    }
}
