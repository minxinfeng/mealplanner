package com.threeone.mealplanner.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author bohan
 *
 */
public class Message {
    private List<Message> messages;

    private MessageLevel level;
    private String message;

    public Message() {
        this.messages = new ArrayList<Message>();
    }

    private Message(MessageLevel level, String message) {
        this.level = level;
        this.message = message;
    }

    public void add(MessageLevel level, String message) {
        this.messages.add(new Message(level, message));
    }

    public void success(String message) {
        this.add(MessageLevel.success, message);
    }

    public void danger(String message) {
        this.add(MessageLevel.danger, message);
    }

    public void info(String message) {
        this.add(MessageLevel.info, message);
    }

    public void warning(String message) {
        this.add(MessageLevel.warning, message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public MessageLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }
}
