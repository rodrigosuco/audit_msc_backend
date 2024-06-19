package br.com.vortex.audit_msc.controllers;

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

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @PostMapping("/new")
    public ResponseEntity<?> scheduleAudit(@RequestBody Audits audits) {
        Set<Auditors> auditors = new HashSet<>();
        Auditors auditor = auditorsService.findById(Math.toIntExact(audits.getAuditorIds()))
                .orElseThrow(() -> new ResourceNotFoundException("Auditor not found with id " + audits.getAuditorIds()));
        auditors.add(auditor);
        audits.setAuditors(auditors);

        Standards standard = standardsService.findById(Math.toIntExact(audits.getStandardId()))
                .orElseThrow(() -> new ResourceNotFoundException("Standard not found with id " + audits.getStandardId()));
        audits.setStandards(Collections.singleton(standard));

        Audits newAudit = auditsService.save(audits);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAudit);
    }
}
