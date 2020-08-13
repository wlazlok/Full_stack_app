package karol.spring.webapp.commands;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductCommand {

    private Long id;
    private String productName;
    private Long categoryId;
    private double price;
    private String producedDate;
    private String description;
    //todo zdjeica
}
