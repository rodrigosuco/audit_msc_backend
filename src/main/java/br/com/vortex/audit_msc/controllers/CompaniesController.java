package br.com.vortex.audit_msc.controllers;

import br.com.vortex.audit_msc.exceptions.ResourceNotFoundException;
import br.com.vortex.audit_msc.models.Companies;
import br.com.vortex.audit_msc.services.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/companies")
public class CompaniesController {

    @Autowired
    CompaniesService companiesService;

    @RequestMapping("/find/all")
    public List<Companies> findAll() {
        return companiesService.findAll();
    }

    @PostMapping("/new")
    public ResponseEntity<Companies> createCompany(@RequestBody Companies company) {
        Companies newCompany = companiesService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCompany);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Companies> deleteCompany(@PathVariable Integer id) {
        companiesService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<?> editCompany(@PathVariable Integer id, @RequestBody Companies updatedCompany) {
        try {
            companiesService.editCompany(id, updatedCompany);
            return ResponseEntity.ok(updatedCompany);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
