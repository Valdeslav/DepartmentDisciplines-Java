package dao.sqlImpl;

import dao.Dao;
import dao.DaoException;
import dao.TopicDao;
import domain.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import java.util.List;

public class TopicDaoSQLImpl extends BaseDaoMySqlImpl implements TopicDao {
    @Override
    public Long create(Topic topic) throws DaoException {
        String sql = "INSERT INTO topics (discipline_id, name, lecture_hours, practice_hours, labs_hours, is_necessary) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement s = null;
        ResultSet r = null;
        try{
            s = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setLong(1, topic.getDisciplineId());
            s.setString(2, topic.getName());
            s.setInt(3, topic.getLectureHours());
            s.setInt(4, topic.getPracticeHours());
            s.setInt(5, topic.getLabsHours());
            s.setBoolean(6, topic.getNess());
            s.executeUpdate();
            r = s.getGeneratedKeys();
            r.next();
            Long id = r.getLong(1);
            topic.setId(id);
            return id;
        } catch(SQLException e) {
            throw new DaoException(e);
        }
        finally {
            try {r.close();} catch(NullPointerException | SQLException e) {}
            try {s.close();} catch(NullPointerException | SQLException e) {}
        }
    }

    @Override
    public Topic read(Long id) throws DaoException {
        String sql = "SELECT id, discipline_id, name, lecture_hours, practice_hours, labs_hours, is_necessary "
                + "FROM topics WHERE id = ?";
        PreparedStatement s = null;
        ResultSet r = null;
        try{
            s = getConnection().prepareStatement(sql);
            s.setLong(1, id);
            r = s.executeQuery();
            Topic topic = null;
            if(r.next()){
                topic = new Topic();
                topic.setId(r.getLong("id"));
                topic.setDisciplineId(r.getInt("discipline_id"));
                topic.setName(r.getString("name"));
                topic.setLectureHours(r.getInt("lecture_hours"));
                topic.setPracticeHours(r.getInt("practice_hours"));
                topic.setLabsHours(r.getInt("labs_hours"));
                topic.setNess(r.getBoolean("is_necessary"));
            }
            return topic;
        } catch(SQLException e) {
            throw new DaoException(e);
        }
        finally {
            try{r.close();} catch(NullPointerException | SQLException e) {}
            try{s.close();} catch(NullPointerException | SQLException e) {}
        }
    }

    @Override
    public void update(Topic topic) throws DaoException {
        String sql = "UPDATE topics SET discipline_id = ?, name = ?, "
                + "lecture_hours = ?, practice_hours = ?, labs_hours = ?, "
                + "is_necessary = ? WHERE id = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            s = getConnection().prepareStatement(sql);
            s.setInt(1, topic.getDisciplineId());
            s.setString(2, topic.getName());
            s.setInt(3, topic.getLectureHours());
            s.setInt(4, topic.getPracticeHours());
            s.setInt(5, topic.getLabsHours());
            s.setBoolean(6, topic.getNess());
            s.setLong(7, topic.getId());
            s.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            try {s.close();} catch(NullPointerException | SQLException e) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM topics WHERE id = ?";
        PreparedStatement s = null;
        try {
            s = getConnection().prepareStatement(sql);
            s.setLong(1, id);
            s.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            try { s.close(); } catch(NullPointerException | SQLException e) {}
        }
    }

    @Override
    public List<Topic> readByDiscipline(Long disciplineId) throws DaoException {
        String sql = "SELECT id, discipline_id, name, lecture_hours, practice_hours, labs_hours, is_necessary "
                + "FROM topics WHERE discipline_id = ?";
        PreparedStatement s = null;
        ResultSet r = null;
        try {
            s = getConnection().prepareStatement(sql);
            s.setLong(1, disciplineId);
            r = s.executeQuery();
            List<Topic> topics = new ArrayList<>();
            while(r.next()){
                Topic topic = new Topic();
                topic.setId(r.getLong("id"));
                topic.setDisciplineId(r.getInt("discipline_id"));
                topic.setName(r.getString("name"));
                topic.setLectureHours(r.getInt("lecture_hours"));
                topic.setPracticeHours(r.getInt("practice_hours"));
                topic.setLabsHours(r.getInt("labs_hours"));
                topic.setNess(r.getBoolean("is_necessary"));
                topics.add(topic);
            }
            return topics;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        finally {
            try{r.close();} catch(NullPointerException | SQLException e) {}
            try{s.close();} catch(NullPointerException | SQLException e) {}
        }
    }
}
