package com.company.ibank.dao;


import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.Person;
import com.company.ibank.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class PersonDAOImpl implements PersonDAO {
    private static final String FILE_PERSON = "person";
    private ReentrantLock lock = new ReentrantLock();
    private File file;

    public PersonDAOImpl() {
        this.file = new File(FILE_PERSON);
    }

    @Override
    public Person createOrUpdatePerson(String name, String surname) throws DAOException {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(surname)) {
            return null;
        }

        try {
            lock.lock();
            String personString = personToString(name, surname);
            String line = FileUtil.findLine(file, personString);
            if (line == null) {
                FileUtil.writeLine(file, personString);
            }
            return new Person(name, surname);
        } catch (IOException e) {
            throw new DAOException("I/O exception", e);
        } finally {
            lock.unlock();
        }
    }

    private String personToString(String name, String surname) {
        return "name="+name+";surname="+surname;
    }
}
