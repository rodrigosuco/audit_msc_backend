package br.com.vortex.audit_msc.DTO;

import java.time.LocalDate;
import java.util.List;


public record AuditsDTO(List<Integer> auditor_id, List<Integer> standard_id, String name,
                        LocalDate initialDate, LocalDate finalDate, Float onSiteManDays, Float offSiteManDays
        ){
}