package com.loanapproval.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

@Component
public class LoanApplication {
    private Long id;

    @NotNull(message = "Applicant name can't be empty")
    @Size(min = 3, max = 50, message = "Name should be 3 to 50 characters")
    private String applicantName;

    @NotNull(message = "Email can't be empty")
    @Email(message = "Enter an valid email")
    private String email;

    @NotNull(message = "Loan amount can't be empty")
    @Min(value = 1, message = "Amount should be at least 1")
    private Double loanAmount;

    @NotNull(message = "Credit score can't be empty")
    @Min(value = 300, message = "Credit score should between 300 and 850")
    @Max(value = 850, message = "Credit score should between 300 and 850")
    private Integer creditScore;

    private String status;

    public LoanApplication() {
    }

    public LoanApplication(Long id, String applicantName, String email, Double loanAmount, Integer creditScore, String status) {
        super();
        this.id = id;
        this.applicantName = applicantName;
        this.email = email;
        this.loanAmount = loanAmount;
        this.creditScore = creditScore;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
