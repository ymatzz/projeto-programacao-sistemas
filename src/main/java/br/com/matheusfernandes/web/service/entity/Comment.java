package br.com.matheusfernandes.web.service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "created_user_id", referencedColumnName = "id")
    private User createdUser;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "created_new_id", referencedColumnName = "id")
    private New createdNew;

    private String message;
}
