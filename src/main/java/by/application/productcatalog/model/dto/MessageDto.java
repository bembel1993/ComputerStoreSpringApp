package by.application.productcatalog.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {

    private Integer id;
    private Integer userId;
    private String question;
    private String message;
    private String answer;
    private String status;

    public MessageDto(){}

    public MessageDto(Integer id, Integer userId, String question, String message, String answer, String status) {
        this.id = id;
        this.userId = userId;
        this.question = question;
        this.message = message;
        this.answer = answer;
        this.status = status;
    }
}
