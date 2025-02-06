package com.loanapproval.controller;

import com.loanapproval.model.LoanApplication;
import com.loanapproval.service.LoanApplicationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    public LoanApplicationController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    @GetMapping("/apply")
    public String loanApplicationForm() {
        return "apply";
    }

    @PostMapping("/submit")
    public String processLoanApplication(
            @Valid @ModelAttribute LoanApplication loanApplication,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            redirectAttributes.addFlashAttribute("messages", errorMessages);
            return "redirect:/apply";
        }

        try {
            loanApplicationService.processApplication(loanApplication);
            return "redirect:/status";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to process the application. Please try again.");
            return "redirect:/apply";
        }
    }

    @GetMapping("/status")
    public String viewStatus(Model model) {
        List<LoanApplication> list = loanApplicationService.allApplication();
        model.addAttribute("list", list);
        return "status";
    }

    @GetMapping("/status/{id}")
    public String viewStatus(
            Model model,
            @PathVariable("id") Long id
    ) {
        model.addAttribute("appl", loanApplicationService.getById(id));
        List<LoanApplication> list = loanApplicationService.allApplication();
        model.addAttribute("list", list);
        return "status";
    }
}
