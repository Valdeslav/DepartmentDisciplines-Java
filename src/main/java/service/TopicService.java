package service;

import domain.Topic;

import java.util.List;

public interface TopicService {
    Long create(Topic topic) throws ServiceException;
    Topic read(Long id) throws ServiceException;
    void update(Topic topic) throws ServiceException;
    void delete(Long id) throws ServiceException;
    List<Topic> readByDiscipline(Long disciplineId) throws ServiceException;
}
