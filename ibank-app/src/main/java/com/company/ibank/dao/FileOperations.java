package com.company.ibank.dao;


import java.io.File;

public interface FileOperations {

  File create(final String fileName);

  String read(final String name);

  boolean delete(final String name);
}
