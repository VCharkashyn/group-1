package com.company.ibank.services;

import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.Person;

public interface PersonService {
    public Person findOrCreatePerson(String name, String surname) throws DAOException;
}
