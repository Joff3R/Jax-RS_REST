package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class Message {

    private long id;
    private String message;
    private Date created;
    private String author;

    public Message(long id, String message, String author) {
        this.id = id;
        this.message = message;
        this.created = new Date();
        this.author = author;
    }
}
