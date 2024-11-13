package br.com.matheusfernandes.web.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "news")
public class New {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "created_user_id", referencedColumnName = "id")
    private User createdUser;

    @Column(columnDefinition = "jsonb")
    private String content;

    private String imageUrl;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @ElementCollection
    private List<String> tags;
}
