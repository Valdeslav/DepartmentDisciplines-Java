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
import java.util.List;

public class TopicListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(IoCContainer ioc = new IoCContainer()) {
            TopicService service = ioc.get(TopicService.class);
            Long disciplineId = Long.parseLong(req.getParameter("disciplineId"));
            List<Topic> topics = service.readByDiscipline(disciplineId);
            req.setAttribute("topics", topics);
            req.getRequestDispatcher("/WEB-INF/jsp/topic/list.jsp").forward(req, resp);
        } catch(ServiceException | IoCException e) {
            throw new ServletException(e);
        }
    }

}
