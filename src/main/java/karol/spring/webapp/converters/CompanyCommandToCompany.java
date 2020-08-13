package karol.spring.webapp.converters;

import karol.spring.webapp.commands.CompanyCommand;
import karol.spring.webapp.models.Company;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class CompanyCommandToCompany implements Converter<CompanyCommand, Company>{

    @Override
    public Company convert(CompanyCommand source) {
        
        if(source == null)
            return null;
        Company company = new Company();
        company.setId(source.getId());
        company.setCompanyName(source.getCompanyName());
        company.setCompanyDescription(source.getCompanyDescription());
        //todo lista

        return company;
    }
}
