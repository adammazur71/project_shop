package org.shop.events;

import org.shop.entities.SysLog;
import org.shop.log4j2.LogRepository;
import org.springframework.stereotype.Component;

@Component
public class LogEventListener extends org.apache.logging.log4j.core.LogEventListener {
    LogRepository logRepository;

    public LogEventListener(LogRepository logRepository) {
        this.logRepository = logRepository;
    }
//    public void onApplicationEvent(org.apache.logging.log4j.core.LogEvent event) {
//        logRepository.save(new SysLog(null, null, event.getLoggerName(), event.getLevel().toString(), event.getMessage().getFormattedMessage(), event.getThreadName()));
//    }

    public void log(final org.apache.logging.log4j.core.LogEvent event) {
        super.log(event);
        logRepository.save(new SysLog(null, null, event.getLoggerName(), event.getLevel().toString(), event.getMessage().getFormattedMessage(), event.getThreadName()));
    }

}
