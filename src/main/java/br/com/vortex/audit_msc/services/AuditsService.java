package br.com.vortex.audit_msc.services;

import br.com.vortex.audit_msc.DTO.AuditsDTO;
import br.com.vortex.audit_msc.exceptions.ResourceNotFoundException;
import br.com.vortex.audit_msc.models.Auditors;
import br.com.vortex.audit_msc.models.Audits;
import br.com.vortex.audit_msc.models.Standards;
import br.com.vortex.audit_msc.repositories.AuditorsRepository;
import br.com.vortex.audit_msc.repositories.AuditsRepository;
import br.com.vortex.audit_msc.repositories.StandardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuditsService {

    @Autowired
    private AuditsRepository auditsRepository;

    @Autowired
    private AuditorsRepository auditorsRepository;

    @Autowired
    private StandardsRepository standardsRepository;

    public List<Audits> findAll() {
        return auditsRepository.findAll();
    }

    public Audits createAudit(AuditsDTO data) {
        Audits newAudit = new Audits();
        Set<Auditors> auditors = new HashSet<>();
        Set<Standards> standards = new HashSet<>();

        // Verifica se o auditor existe e adiciona na lista
        for (Integer auditorId : data.auditor_id()) {
            Auditors auditor = auditorsRepository.findById(auditorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Auditor not found with id " + auditorId));
            auditors.add(auditor);
        }

        // Verifica se a norma existe e adiciona na lista
        for (Integer standardId : data.standard_id()) {
            Standards standard = standardsRepository.findById(standardId)
                    .orElseThrow(() -> new ResourceNotFoundException("Standard not found with id " + standardId));
            standards.add(standard);
        }

        // Verifica se os auditores possuem as normas
        for (Standards standard : standards) {
            for (Auditors auditor : auditors) {
                if (!auditor.getStandards().contains(standard)) {
                    throw new IllegalArgumentException("Auditor with id " + auditor.getId() +
                            " does not have the standard with id " + standard.getId());
                }
            }
        }

        newAudit.setAuditors(auditors);
        newAudit.setStandards(standards);
        newAudit.setName(data.name());
        newAudit.setInitialDate(data.initialDate());
        newAudit.setFinalDate(data.finalDate());
        newAudit.setOnSiteManDays(data.onSiteManDays());
        newAudit.setOffSiteManDays(data.offSiteManDays());

        return auditsRepository.save(newAudit);
    }

    public Optional<Audits> findAuditById(Integer id) {
        return auditsRepository.findById(id);
    }

    public void deleteAudit(Integer id) {
        if (!auditsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Audit not found with id " + id);
        }
        auditsRepository.deleteById(id);
    }

    public Audits editAudit(Integer id, AuditsDTO data) {
        Optional<Audits> audit = auditsRepository.findById(id);
        if (audit.isPresent()) {
            Set<Auditors> auditors = new HashSet<>();
            Set<Standards> standards = new HashSet<>();

            // Verifica se o auditor existe e adiciona na lista
            if (data.auditor_id() != null ) {
                for (Integer auditorId : data.auditor_id()) {
                    Auditors auditor = auditorsRepository.findById(auditorId)
                            .orElseThrow(() -> new ResourceNotFoundException("Auditor not found with id " + auditorId));
                    auditors.add(auditor);
                }
            }
            // Verifica se a norma existe e adiciona na lista
            if (data.standard_id() != null) {
                for (Integer standardId : data.standard_id()) {
                    Standards standard = standardsRepository.findById(standardId)
                            .orElseThrow(() -> new ResourceNotFoundException("Standard not found with id " + standardId));
                    standards.add(standard);
                }
            }

            Audits existingAudit = audit.get();
            if (data.name() != null) {
                existingAudit.setName(data.name());
            }
            if (data.initialDate() != null) {
                existingAudit.setInitialDate(data.initialDate());
            }
            if (data.finalDate() != null) {
                existingAudit.setFinalDate(data.finalDate());
            }
            if (data.onSiteManDays() != null) {
                existingAudit.setOnSiteManDays(data.onSiteManDays());
            }
            if (data.offSiteManDays() != null) {
                existingAudit.setOffSiteManDays(data.offSiteManDays());
            }
            if (!auditors.isEmpty()) {
                existingAudit.setAuditors(auditors);
            }
            if (!standards.isEmpty()) {
                existingAudit.setStandards(standards);
            }
            return auditsRepository.save(existingAudit);
        } else {
            throw new ResourceNotFoundException("Audit not found with id " + id);
        }

    }
}