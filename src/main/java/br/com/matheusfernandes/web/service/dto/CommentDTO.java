package br.com.matheusfernandes.web.service.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long createdUserId;
    private Long createdNewId;
    private String message;
}
