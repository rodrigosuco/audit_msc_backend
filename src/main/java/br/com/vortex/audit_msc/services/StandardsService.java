package br.com.vortex.audit_msc.services;

import br.com.vortex.audit_msc.exceptions.ResourceNotFoundException;
import br.com.vortex.audit_msc.models.Standards;
import br.com.vortex.audit_msc.repositories.StandardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StandardsService {

    @Autowired
    private StandardsRepository standardsRepository;

    public List<Standards> findAll() {
        return standardsRepository.findAll();
    }

    public Standards save(Standards standards) {
        return standardsRepository.save(standards);
    }

    public void deleteStandards(Integer id) {
        if (!standardsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Standard not found with id " + id );
        }
        standardsRepository.deleteById(id);
    }

    public Optional<Standards> findById(Integer id) {
        return standardsRepository.findById(id);
    }

    public Standards editStandard(Integer id, Standards updatedStandard) {
        Optional<Standards> standard = standardsRepository.findById(id);
        if (standard.isPresent()) {
            Standards existingStandard = standard.get();
            if (updatedStandard != null) {
                existingStandard.setName(updatedStandard.getName());
            }
            return standardsRepository.save(existingStandard);
        }
        else {
            throw new ResourceNotFoundException("Standard not found with id " + id);
        }
    }

}
