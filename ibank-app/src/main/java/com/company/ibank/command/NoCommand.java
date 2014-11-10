package com.company.ibank.command;

import org.apache.commons.lang3.StringUtils;

public class NoCommand implements Command {

    @Override
    public String call()  {
        System.out.println("Please check and reenter last command");
        return StringUtils.EMPTY;
    }
}
