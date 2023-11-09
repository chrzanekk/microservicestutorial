package org.konradchrzanowski.fraud.service.impl;

import javassist.tools.rmi.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.konradchrzanowski.fraud.domain.FraudCheck;
import org.konradchrzanowski.fraud.repository.FraudCheckRepository;
import org.konradchrzanowski.fraud.service.FraudCheckService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckServiceImpl implements FraudCheckService {

    private final FraudCheckRepository fraudCheckRepository;


    @Override
    public boolean isFraudulentCustomer(Long customerId) {
        fraudCheckRepository.save(FraudCheck.builder()
                .isFraudster(false)
                .customerId(customerId)
                .checkedAt(LocalDateTime.now()).build());
        return false;
    }
}
