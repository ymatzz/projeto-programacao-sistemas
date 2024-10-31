package br.com.matheusfernandes.todo_list.repository;

import br.com.matheusfernandes.todo_list.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
