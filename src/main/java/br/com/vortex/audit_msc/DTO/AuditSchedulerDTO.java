package br.com.vortex.audit_msc.DTO;

import java.time.LocalDate;


public class AuditSchedulerDTO {
    private Integer auditor_id;

    private Integer standard_id;

    private String name;

    private LocalDate initialDate;

    private LocalDate finalDate;

    private Float onSiteManDays;

    private Float offSiteManDays;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public Float getOnSiteManDays() {
        return onSiteManDays;
    }

    public void setOnSiteManDays(Float onSiteManDays) {
        this.onSiteManDays = onSiteManDays;
    }

    public Float getOffSiteManDays() {
        return offSiteManDays;
    }

    public void setOffSiteManDays(Float offSiteManDays) {
        this.offSiteManDays = offSiteManDays;
    }

    public Integer getAuditor_id() {
        return auditor_id;
    }

    public void setAuditor_id(Integer auditor_id) {
        this.auditor_id = auditor_id;
    }

    public Integer getStandard_id() {
        return standard_id;
    }

    public void setStandard_id(Integer standard_id) {
        this.standard_id = standard_id;
    }
}
