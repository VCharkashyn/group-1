package com.company.ibank.services;

import com.company.ibank.dao.PersonDAO;
import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.Person;
import org.apache.commons.lang3.StringUtils;


public class PersonServiceImpl implements PersonService {
    private PersonDAO personDao;

    public PersonServiceImpl(PersonDAO personDao) {
        this.personDao = personDao;
    }

    @Override
    public Person findOrCreatePerson(String name, String surname) throws DAOException {
        return personDao.createOrUpdatePerson(name, surname);
    }
}
