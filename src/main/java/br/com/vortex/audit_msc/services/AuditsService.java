package br.com.vortex.audit_msc.services;

import br.com.vortex.audit_msc.exceptions.ResourceNotFoundException;
import br.com.vortex.audit_msc.models.Audits;
import br.com.vortex.audit_msc.repositories.AuditsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuditsService {

    @Autowired
    private AuditsRepository auditsRepository;

    public List<Audits> findAll() {
        return auditsRepository.findAll();
    }

    public Audits save(Audits audits) {
       return auditsRepository.save(audits);
    }

    public Optional<Audits> findAuditById(Integer id) {
        return auditsRepository.findById(id);
    }

    public void deleteAudit(Integer id) {
        if (!auditsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Audit not found with id " + id );
        }
        auditsRepository.deleteById(id);
    }
}
