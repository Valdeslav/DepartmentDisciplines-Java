package dao;

import domain.Discipline;

import java.sql.SQLException;
import java.util.List;

public interface DisciplineDao extends Dao<Discipline>{
    List<Discipline> readAll() throws DaoException;
}
