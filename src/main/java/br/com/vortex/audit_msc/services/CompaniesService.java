package br.com.vortex.audit_msc.services;

import br.com.vortex.audit_msc.exceptions.ResourceNotFoundException;
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

    public void deleteCompany(Integer id) {
        if (!companiesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Standard not found with id " + id );
        }
        companiesRepository.deleteById(id);
    }

    public Companies editCompany(Integer id, Companies updatedCompany) {
        Optional<Companies> company = companiesRepository.findById(id);
        if (company.isPresent()) {
            Companies existingCompany = company.get();

            if (updatedCompany.getName() != null) {
                existingCompany.setName(updatedCompany.getName());
            }
            if (updatedCompany.getAddress() != null) {
                existingCompany.setAddress(updatedCompany.getAddress());
            }
            if (updatedCompany.getCnpj() != null) {
                existingCompany.setCnpj(updatedCompany.getCnpj());
            }
            if (updatedCompany.getContact_name() != null) {
                existingCompany.setContact_name(updatedCompany.getContact_name());
            }
            if (updatedCompany.getContact_number() != null) {
                existingCompany.setContact_number(updatedCompany.getContact_number());
            }
            if (updatedCompany.getScope() != null) {
                existingCompany.setScope(updatedCompany.getScope());
            }
            if (updatedCompany.getIs_intercompany() != null) {
                existingCompany.setIs_intercompany(updatedCompany.getIs_intercompany());
            }
            if (updatedCompany.getIs_active() != null) {
                existingCompany.setIs_active(updatedCompany.getIs_active());
            }
            if (updatedCompany.getStandard_code() != null) {
                existingCompany.setStandard_code(updatedCompany.getStandard_code());
            }

            return companiesRepository.save(existingCompany);
        } else {
            throw new ResourceNotFoundException("Company not found with id " + id);
        }
    }

}
