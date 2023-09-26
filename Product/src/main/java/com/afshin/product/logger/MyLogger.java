package com.afshin.product.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

@Component
public class MyLogger {
    public final Logger errorLogger = LogManager. getLogger("ErrorLogger");
    public final Logger eventLogger = LogManager. getLogger("EventLogger");

    public void logStackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        errorLogger.error(exceptionAsString);
    }
}
