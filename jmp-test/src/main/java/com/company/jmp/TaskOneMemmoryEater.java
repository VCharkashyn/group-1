package com.company.jmp;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class TaskOneMemmoryEater {

    private static final Logger logger = Logger.getLogger(TaskOneMemmoryEater.class);

    public static void main(String[] args) {
        List temporaryRefs = new ArrayList();
        while (true) {
            byte tempBytes[] = new byte[1048576];
            temporaryRefs.add(tempBytes);

            if (logger.isInfoEnabled()) {
                logger.info("free memory: " + Runtime.getRuntime().freeMemory());
            }

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                logger.error("Thread was interrupted", e);
            }

        }
    }
}
