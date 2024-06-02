package org.shop.events;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class SentToKsefEvent extends ApplicationEvent {
    public SentToKsefEvent(Object source) {
        super(source);
    }

    public SentToKsefEvent(Object source, Clock clock) {
        super(source, clock);
    }
}
