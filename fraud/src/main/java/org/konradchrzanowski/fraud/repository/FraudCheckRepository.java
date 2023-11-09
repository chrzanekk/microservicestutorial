package org.konradchrzanowski.fraud.repository;

import org.konradchrzanowski.fraud.domain.FraudCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FraudCheckRepository extends JpaRepository<FraudCheck, Long> {
}
