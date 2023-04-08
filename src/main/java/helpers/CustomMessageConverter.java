package helpers;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class CustomMessageConverter extends MessageConverter {

    public String convert(ILoggingEvent event) {
        return enhance(super.convert(event));
    }

    private String enhance(String message) {
        if (!message.endsWith("\n")) {
            message = message.concat("\n");
        }
        return message;
    }
}
