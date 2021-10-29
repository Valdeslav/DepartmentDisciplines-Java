package service;

import domain.Discipline;
import domain.Topic;

import java.util.List;

public interface TopicService {
    void save(Topic topic) throws ServiceException;
    Topic read(Long id) throws ServiceException;
    void delete(Long id) throws ServiceException;
    List<Topic> readByDiscipline(Long disciplineId) throws ServiceException;
}
