package com.loanapproval.service;

import com.loanapproval.model.LoanApplication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanApplicationService {
    private final List<LoanApplication> loanList = new ArrayList<>();
    private Long idCounter = 0L;

    public LoanApplicationService() {

    }

    public void processApplication(LoanApplication application) {
        application.setId(++idCounter);
        if (application.getCreditScore() >= 700) {
            application.setStatus("Approved");
        } else if (application.getCreditScore() < 500) {
            application.setStatus("Rejected");
        } else {
            application.setStatus("Pending");
        }
        loanList.add(application);
    }

    public List<LoanApplication> allApplication(){
        return loanList;
    }

    public LoanApplication getById(Long id) {
        return loanList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }
}
