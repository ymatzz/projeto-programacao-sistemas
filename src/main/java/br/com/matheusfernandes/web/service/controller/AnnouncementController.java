package br.com.matheusfernandes.web.service.controller;

import br.com.matheusfernandes.web.service.entity.Announcement;
import br.com.matheusfernandes.web.service.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping
    List<Announcement> list() {
        return announcementService.list();
    }

    @GetMapping("/{id}")
    Announcement getAnnouncement(@PathVariable("id") Long id) {
        return announcementService.getAnnouncementById(id);
    }

    @PostMapping
    Announcement create(@RequestBody Announcement announcement) {
        return announcementService.create(announcement);
    }

    @PutMapping("/{id}")
    Announcement update(@PathVariable("id") Long id, @RequestBody Announcement announcement) {
        announcement.setId(id);
        return announcementService.update(announcement);
    }

    @DeleteMapping("/{id}")
    List<Announcement> delete(@PathVariable("id") Long id) {
        return announcementService.delete(id);
    }
}
