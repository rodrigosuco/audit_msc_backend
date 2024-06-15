package br.com.vortex.audit_msc.repositories;

import br.com.vortex.audit_msc.models.Auditors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditorsRepository extends JpaRepository<Auditors, Integer> {
}
