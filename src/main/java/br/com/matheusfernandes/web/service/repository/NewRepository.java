package br.com.matheusfernandes.web.service.repository;

import br.com.matheusfernandes.web.service.entity.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewRepository extends JpaRepository<New, Long> {
}
