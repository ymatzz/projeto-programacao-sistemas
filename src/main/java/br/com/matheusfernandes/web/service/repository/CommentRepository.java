package br.com.matheusfernandes.web.service.repository;

import br.com.matheusfernandes.web.service.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value ="SELECT * FROM comments WHERE created_user_id = :userId",nativeQuery= true)
    List<Comment> getCommentByUserId(@Param("userId") Long userId);

    @Query(value ="SELECT * FROM comments WHERE created_new_id = :newId",nativeQuery= true)
    List<Comment> getCommentByNewId(@Param("newId") Long newId);
    
}
