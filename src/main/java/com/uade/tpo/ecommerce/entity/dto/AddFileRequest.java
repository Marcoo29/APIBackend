package com.uade.tpo.ecommerce.entity.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AddFileRequest {
    private Long productId;
    private String name;
    private MultipartFile file;
}
