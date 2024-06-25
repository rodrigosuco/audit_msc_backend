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

    @PostMapping("/new")
    public ResponseEntity<Audits> scheduleAudit(@RequestBody AuditsDTO auditScheduler) {

        Set<Auditors> auditors = new HashSet<>();

        if (auditScheduler.getAuditor_id() == null) {
            throw new IllegalArgumentException("Auditor ID cannot be null");
        }

        for (Integer auditorVariable : auditScheduler.getAuditor_id()) {
            Auditors auditor = auditorsService.findById(auditorVariable)
                    .orElseThrow(() -> new ResourceNotFoundException("Auditor not found with id " + auditScheduler.getAuditor_id()));
            auditors.add(auditor);
            System.out.println(auditorVariable);
        }

        Standards standard = standardsService.findById(auditScheduler.getStandard_id())
                .orElseThrow(() -> new ResourceNotFoundException("Standard not found with id " + auditScheduler.getStandard_id()));

        Audits audits = new Audits();
        audits.setName(auditScheduler.getName());
        audits.setInitialDate(auditScheduler.getInitialDate());
        audits.setFinalDate(auditScheduler.getFinalDate());
        audits.setOnSiteManDays(auditScheduler.getOnSiteManDays());
        audits.setOffSiteManDays(auditScheduler.getOffSiteManDays());
        audits.setAuditors(auditors);
        audits.setStandards(Collections.singleton(standard));

        Audits newAudit = auditsService.save(audits);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAudit);
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
}