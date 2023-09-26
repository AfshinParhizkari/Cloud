package com.afshin.finance.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

@Component
public class MyLogger {
    public final Logger errorLogger = LoggerFactory. getLogger("ErrorLogger");
    public final Logger eventLogger = LoggerFactory. getLogger("EventLogger");

    public void logStackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        errorLogger.error(exceptionAsString);
    }
}
