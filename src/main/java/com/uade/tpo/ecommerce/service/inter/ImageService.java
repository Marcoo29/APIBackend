package com.uade.tpo.ecommerce.service.inter;

import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Image;

@Service
public interface ImageService {
    public Image create(Image image);

    public Image viewByProductId(long id);
}
