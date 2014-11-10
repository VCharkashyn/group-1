package com.company.ibank.utils;

import com.company.ibank.exceptions.DAOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static Long writeLine(File file, String line) throws DAOException, IOException {
        if (file == null) {
            return null;
        }

        synchronized (file) {
            BufferedWriter bufferedWriter = null;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file, true));
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            } catch (FileNotFoundException e) {
                file.createNewFile();
            } finally {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }
        }

        return null;
    }

    public static Long updateLine(File file, String replaceLine, Long id) throws IOException {
        if (file == null || replaceLine == null || id == null) {
            return null;
        }

        synchronized (file) {
            BufferedReader bufferedReader = null;
            BufferedWriter bufferedWriter = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                bufferedWriter = new BufferedWriter(new FileWriter(file));

                String current = bufferedReader.readLine();
                bufferedWriter.write(current);
               // long count = current.length();
                while (current != null) {
                    if (current.contains("id="+id)) {
                    //    long l = count - current.length();
                        bufferedWriter.write(replaceLine);
                    } else {
                        current = bufferedReader.readLine();
                        bufferedWriter.write(current);
                      //  count+=current.length();
                    }
                }

            } catch (FileNotFoundException e) {
                file.createNewFile();
            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }
        }

        return null;
    }

    public static String findLine(File file, String searchPattern) throws IOException {
        if (file == null || searchPattern == null) {
            return null;
        }

        synchronized (file) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                String current = bufferedReader.readLine();
                while (current != null) {
                    if (current.contains(searchPattern)) {
                        return current;
                    }
                    current = bufferedReader.readLine();
                }
            } catch (FileNotFoundException e) {
                file.createNewFile();
            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
        }

        return null;
    }

    public static List<String> readLines(File file) throws IOException {
        if (file == null) {
            return null;
        }

        List<String> result = new ArrayList<>();

        synchronized (file) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                String current = bufferedReader.readLine();
                while (current != null) {
                    result.add(current);
                    current = bufferedReader.readLine();
                }
            } catch (FileNotFoundException e) {
                file.createNewFile();
            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
        }

        return result;
    }
}
