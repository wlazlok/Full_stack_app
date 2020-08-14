package karol.spring.webapp.services;

import karol.spring.webapp.commands.CompanyCommand;
import karol.spring.webapp.models.Company;
import karol.spring.webapp.models.Product;

import java.util.*;

public interface CompanyService {

    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    Company saveCompany(Company company);

    List<Product> getProductsOfCompany(Long id);
}
