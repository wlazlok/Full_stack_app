package karol.spring.webapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(MultipartFile file);
}
