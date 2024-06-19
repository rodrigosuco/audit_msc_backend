package br.com.vortex.audit_msc.services;

import br.com.vortex.audit_msc.models.Audits;
import br.com.vortex.audit_msc.repositories.AuditsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
