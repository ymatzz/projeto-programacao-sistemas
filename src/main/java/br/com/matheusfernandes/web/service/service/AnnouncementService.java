package br.com.matheusfernandes.web.service.service;

import br.com.matheusfernandes.web.service.entity.Announcement;
import br.com.matheusfernandes.web.service.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public List<Announcement> list() {
        try {
            return announcementRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar anúncios", e);
        }
    }

    public Announcement create(Announcement announcement) {
        try {
            announcementRepository.save(announcement);
            return announcement;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar anúncio", e);
        }
    }

    public Announcement update(Announcement announcement) {
        try {
            if (!announcementRepository.existsById(announcement.getId())) {
                throw new NoSuchElementException("Anúncio não encontrado para atualização");
            }
            announcementRepository.save(announcement);
            return announcement;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar anúncio", e);
        }
    }

    public List<Announcement> delete(Long id) {
        try {
            if (!announcementRepository.existsById(id)) {
                throw new NoSuchElementException("Anúncio não encontrado para exclusão");
            }
            announcementRepository.deleteById(id);
            return list();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir anúncio", e);
        }
    }

    public Announcement getAnnouncementById(Long id) {
        try {
            return announcementRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Anúncio não encontrado"));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar anúncio", e);
        }
    }
}
