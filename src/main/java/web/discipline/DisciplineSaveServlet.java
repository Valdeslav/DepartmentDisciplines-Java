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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DisciplineSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            Discipline discipline = getDiscipline(req);
            try(IoCContainer ioc = new IoCContainer()) {
                DisciplineService service = ioc.get(DisciplineService.class);
                service.save(discipline);
                resp.sendRedirect(getServletContext().getContextPath() + "/discipline/list.html");
            } catch (ServiceException | IoCException e) {
                throw new ServletException(e);
            }
        } catch (IllegalArgumentException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }

    private Discipline getDiscipline(HttpServletRequest req) {
        Discipline discipline = new Discipline();
        try {
            discipline.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e){}
        discipline.setName(req.getParameter("name"));
        discipline.setTeacher(req.getParameter("teacher"));
        discipline.setSpeciality(req.getParameter("speciality"));
        discipline.setCourse(Integer.parseInt(req.getParameter("course")));
        discipline.setSemester(Integer.parseInt(req.getParameter("semester")));
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            cal.setTime(sdf.parse(req.getParameter("startDate")));
        } catch (java.text.ParseException e){}
        discipline.setStartDate(cal);
        discipline.setStartDateStr();
        try {
            cal.setTime(sdf.parse(req.getParameter("endDate")));
        } catch (java.text.ParseException e){}
        discipline.setEndDate(cal);
        discipline.setEndDateStr();
        discipline.setLectureHours(Integer.parseInt(req.getParameter("lectureHours")));
        discipline.setPracticeHours(Integer.parseInt(req.getParameter("practiceHours")));
        discipline.setLabsHours(Integer.parseInt(req.getParameter("labsHours")));
        discipline.setLectureLessons();
        discipline.setPracticeLessons();
        discipline.setLabsLessons();
        if(discipline.getName() == null || discipline.getName().isEmpty()) {
            throw new IllegalArgumentException("Не заполнено поле \"Название\"");
        }
        if(discipline.getTeacher() == null || discipline.getTeacher().isEmpty()) {
            throw new IllegalArgumentException("Не заполнено поле \"Преподаватель\"");
        }
        if(discipline.getSpeciality() == null || discipline.getSpeciality().isEmpty()) {
            throw new IllegalArgumentException("Не заполнено поле \"Специальность\"");
        }
        if(discipline.getCourse() == null) {
            throw new IllegalArgumentException("Не заполнено поле \"Курс\"");
        }
        if(discipline.getSemester() == null) {
            throw new IllegalArgumentException("Не заполнено поле \"Семестр\"");
        }
        if(discipline.getStartDateStr() == null || discipline.getStartDateStr().isEmpty()) {
            throw new IllegalArgumentException("Не заполнено поле \"Дата начала\"");
        }
        if(discipline.getEndDateStr() == null || discipline.getEndDateStr().isEmpty()) {
            throw new IllegalArgumentException("Не заполнено поле \"Дата окончания\"");
        }
        if(discipline.getLectureHours() == null) {
            throw new IllegalArgumentException("Не заполнено поле \"Лекционные часы\"");
        }
        if(discipline.getPracticeHours() == null) {
            throw new IllegalArgumentException("Не заполнено поле \"Практические часы\"");
        }
        if(discipline.getLabsHours() == null) {
            throw new IllegalArgumentException("Не заполнено поле \"Лабораторные часы\"");
        }
        return discipline;
    }
}
