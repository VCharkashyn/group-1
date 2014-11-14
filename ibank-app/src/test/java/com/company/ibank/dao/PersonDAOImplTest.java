package com.company.ibank.dao;


import com.company.ibank.AbstractTestBase;
import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class PersonDAOImplTest extends AbstractTestBase {

    private PersonDAO personDAO;

    @Before
    public void setup() {
        personDAO = new PersonDAOImpl();
    }


    @Test
    public void testCreateOrUpdatePerson() throws DAOException {
        assertNotNull(personDAO.createOrUpdatePerson("name", "surname"));
        assertEquals(new Person("name", "surname"), personDAO.createOrUpdatePerson("name", "surname"));
    }

    @Test
    public void testCreateOrUpdatePersonWithNull() throws DAOException {
        assertNull(personDAO.createOrUpdatePerson(null, null));
    }
}
