package br.com.vortex.audit_msc.repositories;

import br.com.vortex.audit_msc.models.Audits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditsRepository extends JpaRepository<Audits, Integer> {
}
