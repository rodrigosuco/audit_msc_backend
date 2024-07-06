package br.com.vortex.audit_msc.controllers;

import br.com.vortex.audit_msc.DTO.AuditsDTO;
import br.com.vortex.audit_msc.exceptions.ResourceNotFoundException;
import br.com.vortex.audit_msc.models.Audits;
import br.com.vortex.audit_msc.models.Auditors;
import br.com.vortex.audit_msc.models.Standards;
import br.com.vortex.audit_msc.services.AuditorsService;
import br.com.vortex.audit_msc.services.AuditsService;

import br.com.vortex.audit_msc.services.StandardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("api/audits")
public class AuditsController {

    @Autowired
    AuditsService auditsService;

    @Autowired
    AuditorsService auditorsService;

    @Autowired
    StandardsService standardsService;

    @GetMapping("/find/all")
    public List<Audits> getAll() {
        return auditsService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Audits> getAuditById(@PathVariable Integer id) {
        Optional<Audits> audit = auditsService.findAuditById(id);
        return audit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

/*    @PostMapping("/new")
    public ResponseEntity<Audits> scheduleAudit(@RequestParam("auditor_id") List<Integer> auditor_id,
                                                @RequestParam("standard_id") List<Integer> standard_id,
                                                @RequestParam("name") String name,
                                                @RequestParam("initialDate") LocalDate initialDate,
                                                @RequestParam("finalDate") LocalDate finalDate,
                                                @RequestParam("onSiteManDays") Float onSiteManDays,
                                                @RequestParam("offSiteManDays") Float offSiteManDays) {
        AuditsDTO auditRequestDTO = new AuditsDTO(auditor_id, standard_id, name, initialDate, finalDate, onSiteManDays,
                                        offSiteManDays);
        Audits newAudit = this.auditsService.createAudit(auditRequestDTO);
        return ResponseEntity.ok(newAudit);
    }*/

    @PostMapping("/new")
    public ResponseEntity<Audits> scheduleAudit(@RequestBody AuditsDTO auditRequestDTO) {
        Audits newAudit = this.auditsService.createAudit(auditRequestDTO);
        return ResponseEntity.ok(newAudit);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAudit(@PathVariable Integer id) {
        try {
            auditsService.deleteAudit(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Audits> editAudit(@RequestParam("auditor_id") Integer auditor_id,
                                            @RequestParam("standard_id") Integer standard_id,
                                            @RequestParam("name") String name,
                                            @RequestParam("initialDate") LocalDate initialDate,
                                            @RequestParam("finalDate") LocalDate finalDate,
                                            @RequestParam("onSiteManDays") Float onSiteManDays,
                                            @RequestParam("offSiteManDays") Float offSiteManDays) {
    return null;
    }

}