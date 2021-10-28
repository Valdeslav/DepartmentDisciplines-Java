package dao.sqlImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.DaoException;
import dao.DisciplineDao;
import domain.Discipline;



public class DisciplineDaoSQLImpl extends BaseDaoMySqlImpl implements DisciplineDao {
    @Override
    public Long create(Discipline discipline) throws DaoException {
        String sql = "INSERT INTO discipline (name, teacher, speciality, course, "
                + "semester, start_date, end_date, lecture_hours, practice_hours, "
                + "labs_hours, lecture_lessons, practice_lessons, labs_lessons) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try{
            c = getConnection();
            s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, discipline.getName());
            s.setString(2, discipline.getTeacher());
            s.setString(3, discipline.getSpeciality());
            s.setInt(4, discipline.getCourse());
            s.setInt(5, discipline.getSemester());
            s.setDate(6, new java.sql.Date(discipline.getStartDate().getTime().getTime()));
            s.setDate(7, new java.sql.Date(discipline.getEndDate().getTime().getTime()));
            s.setInt(8, discipline.getLectureHours());
            s.setInt(9, discipline.getPracticeHours());
            s.setInt(10, discipline.getLabsHours());
            s.setInt(11, discipline.getLectureLessons());
            s.setInt(12, discipline.getPracticeLessons());
            s.setInt(13, discipline.getLabsLessons());
            s.executeUpdate();
            r = s.getGeneratedKeys();
            r.next();
            Long id = r.getLong(1);
            discipline.setId(id);
            return id;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            try {r.close();} catch(NullPointerException | SQLException e) {}
            try {s.close();} catch(NullPointerException | SQLException e) {}
            try {c.close();} catch(NullPointerException | SQLException e) {}
        }
    }

    @Override
    public Discipline read(Long id) throws DaoException{
        String sql = "SELECT id, name, teacher, speciality, course, "
                + "semester, start_date, end_date, lecture_hours, practice_hours, "
                + "labs_hours, lecture_lessons, practice_lessons, labs_lessons FROM discipline WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setLong(1, id);
            r = s.executeQuery();
            Discipline discipline = null;
            if(r.next()){
                discipline = new Discipline();
                discipline.setId(r.getLong("id"));
                discipline.setName(r.getString("name"));
                discipline.setTeacher(r.getString("teacher"));
                discipline.setSpeciality(r.getString("speciality"));
                discipline.setCourse(r.getInt("course"));
                discipline.setSemester(r.getInt("semester"));
                java.sql.Date startDate = r.getDate("start_date");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                discipline.setStartDate(calendar);
                discipline.setStartDateStr();
                java.sql.Date endDate = r.getDate("end_date");
                calendar.setTime(endDate);
                discipline.setEndDate(calendar);
                discipline.setEndDateStr();
                discipline.setLectureHours(r.getInt("lecture_hours"));
                discipline.setPracticeHours(r.getInt("practice_hours"));
                discipline.setLabsHours(r.getInt("labs_hours"));
                discipline.setLectureLessons();
                discipline.setPracticeLessons();
                discipline.setLabsLessons();
            }
            return discipline;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{r.close();} catch(NullPointerException | SQLException e) {}
            try{s.close();} catch(NullPointerException | SQLException e) {}
            try{c.close();} catch(NullPointerException | SQLException e) {}
        }
    }

    @Override
    public void update(Discipline discipline) throws DaoException{
        String sql = "UPDATE discipline SET name = ?, teacher = ?, speciality= ?, course= ?, "
                + "semester= ?, start_date= ?, end_date= ?, lecture_hours= ?, practice_hours= ?, "
                + "labs_hours= ?, lecture_lessons= ?, practice_lessons= ?, labs_lessons= ? WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, discipline.getName());
            s.setString(2, discipline.getTeacher());
            s.setString(3, discipline.getSpeciality());
            s.setInt(4, discipline.getCourse());
            s.setInt(5, discipline.getSemester());
            s.setDate(6, new java.sql.Date(discipline.getStartDate().getTime().getTime()));
            s.setDate(7, new java.sql.Date(discipline.getEndDate().getTime().getTime()));
            s.setInt(8, discipline.getLectureHours());
            s.setInt(9, discipline.getPracticeHours());
            s.setInt(10, discipline.getLabsHours());
            s.setInt(11, discipline.getLectureLessons());
            s.setInt(12, discipline.getPracticeLessons());
            s.setInt(13, discipline.getLabsLessons());
            s.setLong(14, discipline.getId());
            s.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            try {s.close();} catch(NullPointerException | SQLException e) {}
            try {c.close();} catch(NullPointerException | SQLException e) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException{
        String sql = "DELETE FROM discipline WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setLong(1, id);
            s.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e);
        }
        finally {
            try {s.close();} catch(NullPointerException | SQLException e) {}
            try {c.close();} catch(NullPointerException | SQLException e) {}
        }
    }

    @Override
    public List<Discipline> readAll() throws DaoException {
        String sql = "SELECT id, name, teacher, speciality, course, "
                + "semester, start_date, end_date, lecture_hours, practice_hours, "
                + "labs_hours, lecture_lessons, practice_lessons, labs_lessons FROM discipline";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);
            List<Discipline> disciplines = new ArrayList<>();
            while (r.next()){
                Discipline discipline = new Discipline();
                discipline.setId(r.getLong("id"));
                discipline.setName(r.getString("name"));
                discipline.setTeacher(r.getString("teacher"));
                discipline.setSpeciality(r.getString("speciality"));
                discipline.setCourse(r.getInt("course"));
                discipline.setSemester(r.getInt("semester"));
                java.sql.Date startDate = r.getDate("start_date");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                discipline.setStartDate(calendar);
                discipline.setStartDateStr();
                java.sql.Date endDate = r.getDate("end_date");
                calendar.setTime(endDate);
                discipline.setEndDate(calendar);
                discipline.setEndDateStr();
                discipline.setLectureHours(r.getInt("lecture_hours"));
                discipline.setPracticeHours(r.getInt("practice_hours"));
                discipline.setLabsHours(r.getInt("labs_hours"));
                discipline.setLectureLessons();
                discipline.setPracticeLessons();
                discipline.setLabsLessons();
                disciplines.add(discipline);
            }
            return disciplines;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            try {r.close();} catch(NullPointerException | SQLException e) {}
            try {s.close();} catch(NullPointerException | SQLException e) {}
            try {c.close();} catch(NullPointerException | SQLException e) {}
        }
    }
}
