package org.shop.events;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.impl.ThrowableProxy;
import org.apache.logging.log4j.core.time.Instant;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.ReadOnlyStringMap;

import java.util.Map;

public class LogEvent implements org.apache.logging.log4j.core.LogEvent {
    @Override
    public org.apache.logging.log4j.core.LogEvent toImmutable() {
        return null;
    }

    @Override
    public Map<String, String> getContextMap() {
        return Map.of();
    }

    @Override
    public ReadOnlyStringMap getContextData() {
        return null;
    }

    @Override
    public ThreadContext.ContextStack getContextStack() {
        return null;
    }

    @Override
    public String getLoggerFqcn() {
        return "";
    }

    @Override
    public Level getLevel() {
        return null;
    }

    @Override
    public String getLoggerName() {
        return "";
    }

    @Override
    public Marker getMarker() {
        return null;
    }

    @Override
    public Message getMessage() {
        return null;
    }

    @Override
    public long getTimeMillis() {
        return 0;
    }

    @Override
    public Instant getInstant() {
        return null;
    }

    @Override
    public StackTraceElement getSource() {
        return null;
    }

    @Override
    public String getThreadName() {
        return "";
    }

    @Override
    public long getThreadId() {
        return 0;
    }

    @Override
    public int getThreadPriority() {
        return 0;
    }

    @Override
    public Throwable getThrown() {
        return null;
    }

    @Override
    public ThrowableProxy getThrownProxy() {
        return null;
    }

    @Override
    public boolean isEndOfBatch() {
        return false;
    }

    @Override
    public boolean isIncludeLocation() {
        return false;
    }

    @Override
    public void setEndOfBatch(boolean endOfBatch) {

    }

    @Override
    public void setIncludeLocation(boolean locationRequired) {

    }

    @Override
    public long getNanoTime() {
        return 0;
    }
}
