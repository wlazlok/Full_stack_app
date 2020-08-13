package karol.spring.webapp.services;

import karol.spring.webapp.commands.CompanyCommand;
import karol.spring.webapp.converters.CompanyToCompanyCommand;
import karol.spring.webapp.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyToCompanyCommand companyToCompanyCommand;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyToCompanyCommand companyToCompanyCommand) {
        this.companyRepository = companyRepository;
        this.companyToCompanyCommand = companyToCompanyCommand;
    }

    @Override
    public List<CompanyCommand> getAllCompanies() {

        List<CompanyCommand> companies = new ArrayList<>();
        companyRepository.findAll().forEach(comp ->{
            companies.add(companyToCompanyCommand.convert(comp));
        });

        return companies;
    }
}
