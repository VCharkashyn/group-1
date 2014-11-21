package com.company.ibank.controllers;

import com.company.ibank.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ICommand {
    RequestPage execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException;
}
