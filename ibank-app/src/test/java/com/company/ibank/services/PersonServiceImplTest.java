package com.company.ibank.services;

import com.company.ibank.AbstractTestBase;
import com.company.ibank.dao.PersonDAO;
import com.company.ibank.dao.PersonDAOImpl;
import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.Person;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PersonServiceImplTest extends AbstractTestBase {

    @Mock
    private PersonDAO personDAOMock;

    private PersonServiceImpl personService;

    @Before
    public void setup() {
        personService = new PersonServiceImpl(personDAOMock);
    }

    @Test
    public void testFindOrCreatePerson() throws DAOException {
        getContext().checking(new Expectations() {
            {
                oneOf(personDAOMock).createOrUpdatePerson(with(any(String.class)), with(any(String.class)));
            }
        });

        Assert.assertNotNull(personService.findOrCreatePerson("", "fghj"));
    }

    @Test
    public void testFindOrCreatePersonWithNullName() throws DAOException {
        getContext().checking(new Expectations() {
            {
                oneOf(personDAOMock).createOrUpdatePerson(null, null);
            }
        });

        Assert.assertNotNull(personService.findOrCreatePerson(null, null));
    }
}
