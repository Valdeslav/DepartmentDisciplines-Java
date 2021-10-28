package dao;

import domain.Topic;

import java.util.List;

public interface TopicDao extends Dao<Topic>{
    List<Topic> readByDiscipline(Long disciplineId) throws DaoException;
}
