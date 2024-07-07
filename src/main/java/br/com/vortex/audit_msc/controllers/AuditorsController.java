package br.com.vortex.audit_msc.controllers;

import br.com.vortex.audit_msc.exceptions.ResourceNotFoundException;
import br.com.vortex.audit_msc.models.Auditors;
import br.com.vortex.audit_msc.models.Standards;
import br.com.vortex.audit_msc.services.AuditorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auditors")
public class AuditorsController {

    @Autowired
    private AuditorsService auditorsService;

    @GetMapping("/find/all")
    public List<Auditors> getAllAuditors() {
        return auditorsService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Auditors> getAuditorById(@PathVariable Integer id) {
        Optional<Auditors> auditor = auditorsService.findById(id);
        return auditor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/new")
    public ResponseEntity<Auditors> createAuditor(@RequestBody Auditors auditors) {
        Auditors savedAuditors = auditorsService.save(auditors);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuditors);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            auditorsService.deleteAuditors(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editAuditor(@PathVariable Integer id, @RequestBody Auditors auditorsDetails) {
        try {
            Auditors updatedAuditor = auditorsService.editAuditor(id, auditorsDetails);
            return ResponseEntity.ok(updatedAuditor);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add-standard/auditor/{auditorId}/standard/{standardId}")
    public ResponseEntity assignStandard(@PathVariable Integer auditorId, @PathVariable Integer standardId) {
        try {
            auditorsService.addStandardToAuditor(auditorId, standardId);
            return ResponseEntity.ok("Standard added to Auditor successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

}
