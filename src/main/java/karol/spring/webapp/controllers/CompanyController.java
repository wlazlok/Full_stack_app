package karol.spring.webapp.controllers;

import karol.spring.webapp.services.CompanyService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    
}
