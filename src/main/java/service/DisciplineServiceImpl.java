package service;

import dao.DaoException;
import dao.DisciplineDao;
import domain.Discipline;

import java.util.List;

public class DisciplineServiceImpl implements DisciplineService{
    private DisciplineDao disciplineDao;
    @Override
    public void save(Discipline discipline) throws ServiceException {
        try {
            if(discipline.getId() != null) {
                disciplineDao.update(discipline);
            } else {
                disciplineDao.create(discipline);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Discipline read(Long id) throws ServiceException {
        try {
            return disciplineDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            disciplineDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Discipline> readAll() throws ServiceException {
        try {
            return disciplineDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void setDisciplineDao(DisciplineDao disciplineDao) {
        this.disciplineDao = disciplineDao;
    }
}
