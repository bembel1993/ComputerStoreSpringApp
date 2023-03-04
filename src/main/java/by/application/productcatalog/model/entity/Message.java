package by.application.productcatalog.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "question")
    private String question;

    @Column(name = "message")
    private String message;

    @Column(name = "answer")
    private String answer;

    @Column(name = "status")
    private String status;

    public Message() { }

    public Message(Integer id, Integer userId, String question, String message, String answer, String status) {
        this.id = id;
        this.userId = userId;
        this.question = question;
        this.message = message;
        this.answer = answer;
        this.status = status;
    }
}
