package br.com.vortex.audit_msc.services;

import br.com.vortex.audit_msc.exceptions.ResourceNotFoundException;
import br.com.vortex.audit_msc.models.Auditors;
import br.com.vortex.audit_msc.repositories.AuditorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditorsService {

    @Autowired
    private AuditorsRepository auditorsRepository;

    public List<Auditors> findAll() {
        return auditorsRepository.findAll();
    }

    public Auditors save(Auditors auditors) {
        return auditorsRepository.save(auditors);
    }

    public void deleteAuditors(Integer id) {
        if (!auditorsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Auditor not found with id " + id );
        }
        auditorsRepository.deleteById(id);
    }

    public Optional<Auditors> findById(Integer id) {
        return auditorsRepository.findById(id);
    }

    public Auditors editAuditor(Integer id, Auditors updatedAuditor) {
        Optional<Auditors> auditor = auditorsRepository.findById(id);
        if (auditor.isPresent()) {
            Auditors existingAuditor = auditor.get();
            existingAuditor.setName(updatedAuditor.getName());
            return auditorsRepository.save(existingAuditor);
        }
        else {
            throw new ResourceNotFoundException("Standard not found with id " + id);
        }
    }


}
