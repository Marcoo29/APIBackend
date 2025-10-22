package com.uade.tpo.ecommerce.controllers;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class AddFileRequest {
    private String name;
    private MultipartFile file;
    private Long productId;
}
