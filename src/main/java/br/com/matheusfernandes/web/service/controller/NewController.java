package br.com.matheusfernandes.web.service.controller;

import br.com.matheusfernandes.web.service.dto.NewDTO;
import br.com.matheusfernandes.web.service.entity.New;
import br.com.matheusfernandes.web.service.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/news")
public class NewController {

    private final NewService newService;

    @GetMapping
    List<New> list() {
        return newService.list();
    }

    @GetMapping("/{id}")
    New getNew(@PathVariable("id") Long id) {
        return newService.getNewById(id);
    }

    @PostMapping
    New create(@RequestBody NewDTO newDTO) {
        return newService.create(newDTO);
    }

    @PutMapping("/{id}")
    List<New> update(@PathVariable("id") Long id, @RequestBody NewDTO newDTO) {
        newDTO.setId(id);
        return newService.update(newDTO);
    }

    @PatchMapping("/{id}")
    New patch(@PathVariable("id") Long id, @RequestBody NewDTO newDTO) {
        return newService.patch(id, newDTO);
    }

    @DeleteMapping("/{id}")
    List<New> delete(@PathVariable("id") Long id) {
        return newService.delete(id);
    }

    @GetMapping("?createdUserId={id}")
    List<New> getNewsByUserId(@PathVariable("id") Long id) {
        return newService.getNewsByUserId(id);
    }

    @GetMapping("?categoryId={id}")
    List<New> getNewsByCategoryId(@PathVariable("id") Long id) {
        return newService.getNewsByCategoryId(id);
    }
}
