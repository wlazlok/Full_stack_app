package karol.spring.webapp.services;

import karol.spring.webapp.commands.CompanyCommand;
import karol.spring.webapp.models.Company;

import java.util.*;

public interface CompanyService {

    List<Company> getAllCompanies();
}
