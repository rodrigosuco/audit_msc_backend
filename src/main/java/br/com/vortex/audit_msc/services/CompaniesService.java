package br.com.vortex.audit_msc.services;

import br.com.vortex.audit_msc.models.Companies;
import br.com.vortex.audit_msc.repositories.CompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompaniesService {

    @Autowired
    CompaniesRepository companiesRepository;

    public List<Companies> findAll() {
        return companiesRepository.findAll();
    }

    public Companies createCompany(Companies company) {
        return companiesRepository.save(company);
    }

    public Optional<Companies> findById(Integer id) {
        return companiesRepository.findById(id);
    }

}
