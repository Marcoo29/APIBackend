package com.uade.tpo.ecommerce.controllers;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageResponse {
    private Long id;
    private String file;
}
