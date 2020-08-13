package karol.spring.webapp.services;

import karol.spring.webapp.commands.CompanyCommand;

import java.util.*;

public interface CompanyService {

    List<CompanyCommand> getAllCompanies();
}
