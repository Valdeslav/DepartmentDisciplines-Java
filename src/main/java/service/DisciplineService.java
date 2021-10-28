package service;

import java.util.List;

import domain.Discipline;


public interface DisciplineService {
    void save(Discipline discipline) throws ServiceException;
    Discipline read(Long id) throws ServiceException;
    void delete(Long id) throws ServiceException;
    List<Discipline> readAll() throws ServiceException;
}
