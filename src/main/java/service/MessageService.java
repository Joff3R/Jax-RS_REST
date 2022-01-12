package service;

import database.DatabaseClass;
import model.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class MessageService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();


    public MessageService() {
        messages.put(1L, new Message(1, "ABC", "xd"));
        messages.put(2L, new Message(2, "XYZ", "dx"));
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public List<Message> getAllMessagesForYear(int year) {
        var messagesForYear = new ArrayList<Message>();
        var calendar = Calendar.getInstance();
        messages.values().forEach(message -> {
            calendar.setTime(message.getCreated());
            if (calendar.get(Calendar.YEAR) == year) {
                messagesForYear.add(message);
            }
        });
        return messagesForYear;
    }

    public List<Message> getAllMessagesPaginated(int start, int size) {
        var list = new ArrayList<>(messages.values());
        return start + size > list.size() ? new ArrayList<>() : list.subList(start, start + size);
    }

    public Message getMessage(long id) {
        return messages.get(id);
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) {
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(long id) {
        return messages.remove(id);
    }
}
