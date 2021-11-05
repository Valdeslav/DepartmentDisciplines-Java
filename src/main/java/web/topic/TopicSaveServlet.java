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

public class TopicSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            Topic topic = getTopic(req);
            try(IoCContainer ioc = new IoCContainer()) {
                TopicService service = ioc.get(TopicService.class);
                service.save(topic);
                resp.sendRedirect(getServletContext().getContextPath() + "/topic/list.html?disciplineId=" + topic.getDisciplineId());
            } catch (ServiceException | IoCException e) {
                throw new ServletException(e);
            }
        } catch (IllegalArgumentException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }

    private Topic getTopic(HttpServletRequest req) {
        Topic topic = new Topic();
        try {
            topic.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {}
        topic.setName(req.getParameter("name"));
        topic.setLectureHours(Integer.parseInt(req.getParameter("lectureHours")));
        topic.setPracticeHours(Integer.parseInt(req.getParameter("practiceHours")));
        topic.setLabsHours(Integer.parseInt(req.getParameter("labsHours")));
        String ness = req.getParameter("ness");
        if (ness != null) {
            topic.setNess(true);
        } else {
            topic.setNess(false);
        }
        topic.setDisciplineId(Long.parseLong(req.getParameter("disciplineId")));
        if(topic.getName() == null || topic.getName().isEmpty()){
            throw new IllegalArgumentException("Не заполнено поле \"Название\"");
        }
        if(topic.getLectureHours() == null){
            throw new IllegalArgumentException("Не заполнено поле \"Лекционные часы\"");
        }
        if(topic.getPracticeHours() == null){
            throw new IllegalArgumentException("Не заполнено поле \"Практические часы\"");
        }
        if(topic.getLabsHours() == null){
            throw new IllegalArgumentException("Не заполнено поле \"Лабораторные часы\"");
        }
        if(topic.getDisciplineId() == null){
            throw new IllegalArgumentException("Ожидался disciplineId, но не был получен");
        }
        return topic;
    }
}
