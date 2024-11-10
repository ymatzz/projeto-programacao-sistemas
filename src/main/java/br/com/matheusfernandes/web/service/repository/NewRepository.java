package br.com.matheusfernandes.web.service.repository;

import br.com.matheusfernandes.web.service.entity.Comment;
import br.com.matheusfernandes.web.service.entity.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewRepository extends JpaRepository<New, Long> {

    @Query(value ="SELECT * FROM news WHERE created_user_id = :userId",nativeQuery= true)
    List<New> getNewsByUserId(@Param("userId") Long userId);

    @Query(value ="SELECT * FROM news WHERE category_id = :categoryId",nativeQuery= true)
    List<New> getNewsByCategoryId(@Param("categoryId") Long categoryId);
}
