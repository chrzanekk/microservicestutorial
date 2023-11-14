package com.konradchrzanowski.fraud.repository;

import com.konradchrzanowski.fraud.domain.FraudCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudCheckRepository extends JpaRepository<FraudCheck, Long> {
}
