package com.open.spring.mvc.timecapsule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/data")
public class TimeCapsuleApiController {

    @Autowired
    private TimeCapsuleJpaRepository repository;

    // GET all
    @GetMapping
    public ResponseEntity<List<TimeCapsule>> getAll() {
        List<TimeCapsule> capsules = repository.findAll();
        return new ResponseEntity<>(capsules, HttpStatus.OK); // 200
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<TimeCapsule> getById(@PathVariable Long id) {
        Optional<TimeCapsule> capsule = repository.findById(id);
        return capsule.map(c -> new ResponseEntity<>(c, HttpStatus.OK))       // 200
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404
    }

    // POST create
    @PostMapping
    public ResponseEntity<TimeCapsule> create(@RequestBody TimeCapsule capsule) {
        try {
            TimeCapsule saved = repository.save(capsule);
            return new ResponseEntity<>(saved, HttpStatus.CREATED); // 201
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    // PUT update
    @PutMapping("/{id}")
    public ResponseEntity<TimeCapsule> update(@PathVariable Long id, @RequestBody TimeCapsule updated) {
        Optional<TimeCapsule> existing = repository.findById(id);
        if (existing.isPresent()) {
            TimeCapsule capsule = existing.get();
            capsule.setTitle(updated.getTitle());
            capsule.setMessage(updated.getMessage());
            capsule.setAuthor(updated.getAuthor());
            capsule.setUnlockDate(updated.getUnlockDate());
            capsule.setOpened(updated.isOpened());
            TimeCapsule saved = repository.save(capsule);
            return new ResponseEntity<>(saved, HttpStatus.OK); // 200
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try {
            if (!repository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
            }
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK); // 200
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }
}