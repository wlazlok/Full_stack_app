package karol.spring.webapp.commands;

import lombok.Data;

import javax.persistence.Lob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ProductCommand {

    private Long id;
    private String productName;
    private Long categoryId;
    private Long companyId;
    private double price;
    private String producedDate;
    private String description;
    private String shortDescription;
    private Byte[] mainImage;
    private Byte[] image1;
    private Byte[] image2;
    //todo zdjeica
}
