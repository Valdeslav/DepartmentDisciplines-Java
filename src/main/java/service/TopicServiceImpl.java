package service;

import dao.Dao;
import dao.DaoException;
import dao.TopicDao;
import domain.Topic;

import java.util.List;

public class TopicServiceImpl implements TopicService{
    private TopicDao topicDao;

    @Override
    public Long create(Topic topic) throws ServiceException {
        try {
            return topicDao.create(topic);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Topic read(Long id) throws ServiceException {
        try {
            return topicDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Topic topic) throws ServiceException {
        try {
            topicDao.update(topic);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            topicDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Topic> readByDiscipline(Long disciplineId) throws ServiceException {
        try {
            return topicDao.readByDiscipline(disciplineId);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }
}
