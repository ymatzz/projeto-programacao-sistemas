package br.com.matheusfernandes.web.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewDTO {
    private Long id;
    private String name;
    private Long categoryId;
    private Long createdUserId;
    private String content;
    private List<String> tags;
}
