package br.com.matheusfernandes.web.service.repository;

import br.com.matheusfernandes.web.service.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
