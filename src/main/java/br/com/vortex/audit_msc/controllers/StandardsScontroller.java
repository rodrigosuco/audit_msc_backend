package br.com.vortex.audit_msc.controllers;

import br.com.vortex.audit_msc.exceptions.ResourceNotFoundException;
import br.com.vortex.audit_msc.models.Standards;
import br.com.vortex.audit_msc.services.StandardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/audit_standards")
public class StandardsScontroller {

    @Autowired
    private StandardsService standardsService;

    @GetMapping("/find/all")
    public List<Standards> getAllStandards() {
        return standardsService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Standards> getStandardById(@PathVariable Integer id) {
        Optional<Standards> standard = standardsService.findById(id);
        return standard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/new")
    public ResponseEntity createStandard(@RequestBody Standards standards) {
        Standards savedStandard = standardsService.save(standards);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStandard);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            standardsService.deleteStandards(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editStandard(@PathVariable int id, @RequestBody Standards standardDetails) {
        try {
            Standards updatedStandard = standardsService.editStandard(id, standardDetails);
            return ResponseEntity.ok(updatedStandard);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}