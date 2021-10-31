package service;

import dao.Dao;
import dao.DaoException;
import dao.DisciplineDao;
import dao.TopicDao;
import domain.Discipline;
import domain.Topic;

import java.util.List;
import java.util.Objects;

public class TopicServiceImpl implements TopicService{
    private TopicDao topicDao;
    private DisciplineDao disciplineDao;
    @Override
    public void save(Topic topic) throws ServiceException {
        try {
            Discipline discipline = disciplineDao.read(topic.getDisciplineId());
            if(!topic.getNess()){
                List<Topic> topics = topicDao.readByDiscipline(discipline.getId()); // проверяем условие (самостоятельных часов должно быть не больше 15%)
                int lectSelfLearnHours = topic.getLectureHours();
                int practSelfLearnHours = topic.getPracticeHours();
                int labSelfLearnHours = topic.getLabsHours();
                for(Topic top: topics) { // находим сумму самостоятельных часов
                    if (!top.getNess() & !Objects.equals(top.getId(), topic.getId())) {
                        lectSelfLearnHours += top.getLectureHours();
                        practSelfLearnHours += top.getPracticeHours();
                        labSelfLearnHours += top.getLabsHours();
                    }
                }
                if (discipline.getLectureHours() * 0.15 < lectSelfLearnHours) {
                    throw new IllegalArgumentException("Количество лекционных часов, вынесенных на самостоятельную работу, не должно превышать 15%");
                }
                if (discipline.getPracticeHours() * 0.15 < practSelfLearnHours) {
                    throw new IllegalArgumentException("Количество практических часов, вынесенных на самостоятельную работу, не должно превышать 15%");
                }
                if (discipline.getLabsHours() * 0.15 < labSelfLearnHours) {
                    throw new IllegalArgumentException("Количество лабораторных часов, вынесенных на самостоятельную работу, не должно превышать 15%");
                }
            }
            if(topic.getId() != null) {
                topicDao.update(topic);
            } else {
                topicDao.create(topic);
            }
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
    public void setDisciplineDao(DisciplineDao disciplineDao) { this.disciplineDao = disciplineDao; }
}
