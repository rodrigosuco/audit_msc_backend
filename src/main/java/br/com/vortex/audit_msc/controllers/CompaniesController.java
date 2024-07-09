package br.com.vortex.audit_msc.controllers;

import br.com.vortex.audit_msc.models.Companies;
import br.com.vortex.audit_msc.services.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
