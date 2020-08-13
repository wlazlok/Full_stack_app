package karol.spring.webapp.converters;

import karol.spring.webapp.commands.CompanyCommand;
import karol.spring.webapp.models.Company;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class CompanyToCompanyCommand implements Converter<Company, CompanyCommand>{

    @Override
    public CompanyCommand convert(Company source) {
        if(source == null)
            return null;
        CompanyCommand companyCommand = new CompanyCommand();
        companyCommand.setId(source.getId());
        companyCommand.setCompanyName(source.getCompanyName());
        companyCommand.setCompanyDescription(source.getCompanyDescription());

        return companyCommand;
    }
}
