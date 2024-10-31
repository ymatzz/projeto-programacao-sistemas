package br.com.matheusfernandes.todo_list.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "news")
public class New {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    @ManyToOne
    @JoinColumn(name = "created_user_id", referencedColumnName = "id")
    private User createdUserId;

    private String content;

    @ElementCollection
    private List<String> tags;

}
