package com.konradchrzanowski.fraud.service;

import javassist.tools.rmi.ObjectNotFoundException;

public interface FraudCheckService {

    boolean isFraudulentCustomer(Long customerId);
}
