package br.com.vortex.audit_msc.controllers;

import br.com.vortex.audit_msc.models.Audits;
import br.com.vortex.audit_msc.services.AuditsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("api/audits")
public class AuditsController {

    @Autowired
    AuditsService auditsService;

    @GetMapping("/find/all")
    public List<Audits> getAll() {
        return auditsService.findAll();
    }

    @GetMapping("/new")
    public ResponseEntity scheduleAudit(@RequestBody Audits audits) {
        Audits newAudit = auditsService.save(audits);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAudit);
    }
}
