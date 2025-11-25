package com.uade.tpo.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Image;
import com.uade.tpo.ecommerce.repository.ImageRepository;
import com.uade.tpo.ecommerce.service.inter.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image create(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image viewByProductId(long id) {
        return imageRepository.findByProduct_Id(id);
    }
}