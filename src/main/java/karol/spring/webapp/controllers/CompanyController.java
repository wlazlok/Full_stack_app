package karol.spring.webapp.controllers;

import karol.spring.webapp.models.Company;
import karol.spring.webapp.services.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public String getAllCompanies(Model model){
        model.addAttribute("companies", companyService.getAllCompanies());

        return "company/showAllCompanies";
    }

    @GetMapping("/{id}/edit")
    public String editCompany(Model model, @PathVariable Long id){
        model.addAttribute("company", companyService.getCompanyById(id));

        return "company/editCompany";
    }

    @PostMapping("/{id}/edit")
    @Transactional
    public String processEditCompany(@Validated Company company, BindingResult result, @PathVariable Long id, Model model){

        if(result.hasErrors()){
            System.out.println("Problem during updating company");
            model.addAttribute("company", company);
            return "redirect:/company/editCompany";
        }else{
            Company savedCompany = companyService.getCompanyById(id);

            savedCompany.setCompanyName(company.getCompanyName());
            savedCompany.setCompanyDescription(company.getCompanyDescription());
            savedCompany.setProducts(company.getProducts());

            companyService.saveCompany(company);

            return "redirect:/company";
        }
    }

    @GetMapping("/new")
    public String createNewCompany(Model model){
        model.addAttribute("company", new Company());

        return "company/createNewCompanyForm";
    }

    @PostMapping("/new")
    public String processCreateNewCompany(@ModelAttribute Company company, BindingResult result, Model model){
        if(result.hasErrors()){
            System.out.println("Problem during creating new company");
            model.addAttribute("company", company);
            return "redirect:/company/createNewCompanyForm";
        }else{
            Company savedCompany = companyService.saveCompany(company);

            return "redirect:/company";
        }
    }
}
