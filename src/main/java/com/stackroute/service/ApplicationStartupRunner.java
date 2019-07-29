package com.stackroute.service;

import jdk.internal.jline.internal.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;

public class ApplicationStartupRunner implements CommandLineRunner {
    protected final Log logger= LogFactory.getLog(getClass());

    @Override
    public void run(String... args) throws Exception
    {
       logger.info("Application startup Runner run method started");
    }
}
