package com.company.ibank.dao;

import com.company.ibank.exceptions.DAOException;
import com.company.ibank.model.*;
import com.company.ibank.utils.FileUtil;
import com.company.ibank.utils.ValidationUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class AccountDAOImpl implements AccountDAO {
    private static final String FILE_ACCOUNT="account";
    private PersonDAO personDAO;
    private File file;

    public AccountDAOImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
        file = new File(FILE_ACCOUNT);
    }

    @Override
    public Long createOrUpdateAccount(Account account) throws DAOException {
        if (account == null) {
            throw new DAOException("Unsupported account to save/update");
        }
        Long id = null;

        try {
            Person owner = account.getOwner();
            if (account.getId() == null) {
                personDAO.createOrUpdatePerson(owner.getName(), owner.getSurname());
                id = FileUtil.writeLine(file, accountToString(account));
            } else {
                personDAO.createOrUpdatePerson(owner.getName(), owner.getSurname());
                id = FileUtil.updateLine(file, accountToString(account), account.getId());
            }
        } catch (IOException e) {
            new DAOException("I/O exception", e);
        }
        return id;
    }

    @Override
    public Account findById(long id) throws DAOException {
        try {
            String line = FileUtil.findLine(file, "id=" + id);
            if (line == null) {
                return null;
            }
            return stringToAccount(line);
        } catch (IOException ex) {
            throw new DAOException("I/O exception", ex);
        }
    }

    @Override
    public List<Account> getAccounts() throws DAOException {
        List<Account> accounts = new ArrayList<Account>();

        try {
            List<String> lines = FileUtil.readLines(file);
            for (String line : lines) {
                Account account = stringToAccount(line);
                if (account != null) {
                    accounts.add(stringToAccount(line));
                }
            }
        } catch (IOException ex) {
            throw new DAOException("I/O exception", ex);
        }

        return accounts;
    }


    private String accountToString(Account account) {
        return "[id=XidX;status="+account.getStatus().getAccountStatus()+";name="+account.getOwner().getName()+";"
               + "surname="+account.getOwner().getSurname()+";amount="+account.getMoney().getAmount()+";currency="+account.getMoney().getCurrency().getCurrencyName();
    }


    private Account stringToAccount(String line) {
        String[] values = line.split(";");

        if (values.length == 6) {
            List<String> pojoValues = new ArrayList<>(6);
            for(String value:values){
                String[] split = value.split("=");
                if (split.length == 2) {
                    pojoValues.add(split[1]);
                }
            }

            if (pojoValues.size()==6) {
                Account account = new Account();
                account.setId(ValidationUtil.validateAccountID(pojoValues.get(0)));
                account.setStatus(AccountStatus.valueOfAccountStatus(pojoValues.get(1)));
                account.setMoney(new Money(ValidationUtil.validateAmount(pojoValues.get(4)), ValidationUtil.validateCurrency(pojoValues.get(5))));
                account.setOwner(new Person(pojoValues.get(2), pojoValues.get(3)));
                return account;
            }
        }

        return null;
    }
}
