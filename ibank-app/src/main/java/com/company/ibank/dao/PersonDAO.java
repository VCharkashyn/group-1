package com.company.ibank.dao;

import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.Person;

public interface PersonDAO {
    Person createOrUpdatePerson(String name, String surname) throws DAOException;
}
