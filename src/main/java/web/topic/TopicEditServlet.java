package web.topic;

import domain.Topic;
import ioc.IoCContainer;
import ioc.IoCException;
import service.ServiceException;
import service.TopicService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TopicEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        Long disciplineId = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {}
        try {
            disciplineId = Long.parseLong(req.getParameter("disciplineId"));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ожидался параметр disciplineId!");
        }
        if(id != null) {
            try(IoCContainer ioc = new IoCContainer()) {
                TopicService service = ioc.get(TopicService.class);
                Topic topic = service.read(id);
                req.setAttribute("topic", topic);
            } catch(ServiceException | IoCException e) {
                throw new ServletException(e);
            }
        }
        req.setAttribute("disciplineId", disciplineId);
        req.getRequestDispatcher("/WEB-INF/jsp/topic/edit.jsp").forward(req, resp);
    }
}
