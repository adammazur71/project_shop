package org.example.events;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;
// TODO: JAVA DOC
public class SentToKsefEvent extends ApplicationEvent {
    public SentToKsefEvent(Object source) {
        super(source);
    }

    public SentToKsefEvent(Object source, Clock clock) {
        super(source, clock);
    }
}
