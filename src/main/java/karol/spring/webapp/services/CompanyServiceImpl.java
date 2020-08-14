package karol.spring.webapp.services;

import karol.spring.webapp.models.Company;
import karol.spring.webapp.models.Product;
import karol.spring.webapp.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        companyRepository.findAll().forEach(companies::add);

        return companies;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Product> getProductsOfCompany(Long id) {
        List<Product> products;
        Company company = companyRepository.findById(id).get();
        products = company.getProducts();

        return products;
    }
}
