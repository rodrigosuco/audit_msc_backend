package br.com.vortex.audit_msc.controllers;

import br.com.vortex.audit_msc.DTO.AuditSchedulerDTO;
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
    public ResponseEntity<?> scheduleAudit(@RequestBody AuditSchedulerDTO auditScheduler) {
        System.out.println("Teste = " + auditScheduler.getAuditor_id());
        Set<Auditors> auditors = new HashSet<>();
        if (auditScheduler.getAuditor_id() == null) {
            throw new IllegalArgumentException("Auditor ID cannot be null");
        }

        Auditors auditor = auditorsService.findById(auditScheduler.getAuditor_id())
                .orElseThrow(() -> new ResourceNotFoundException("Auditor not found with id " + auditScheduler.getAuditor_id()));
        auditors.add(auditor);

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
}