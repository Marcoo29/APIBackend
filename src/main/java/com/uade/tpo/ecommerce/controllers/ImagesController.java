package com.uade.tpo.ecommerce.controllers;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.entity.Image;
import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.AddFileRequest;
import com.uade.tpo.ecommerce.entity.dto.ImageResponse;
import com.uade.tpo.ecommerce.service.inter.ImageService;
import com.uade.tpo.ecommerce.service.inter.ProductService;

@RestController
@RequestMapping("images")
public class ImagesController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;

    @CrossOrigin
    @GetMapping() // Obtener imagen por id
    public ResponseEntity<ImageResponse> displayImage(@RequestParam("id") Long id) throws IOException, SQLException {
        Image image = imageService.viewById(id);
        String encodedString = Base64.getEncoder()
                .encodeToString(image.getImage().getBytes(1, (int) image.getImage().length()));
        ImageResponse response = ImageResponse.builder()
                .id(image.getId())
                .file(encodedString)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping() // Subir nueva imagen asociada a un producto
    public String addImagePost(AddFileRequest request) throws IOException, SerialException, SQLException {
        Optional<Product> optionalProduct = productService.getProductById(request.getProductId());
        if (!optionalProduct.isPresent()) {
            return "Producto no encontrado";
        }
        Product product = optionalProduct.get();
        byte[] bytes = request.getFile().getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        Image image = Image.builder()
                .name(request.getName())
                .image(blob)
                .product(product)
                .build();

        imageService.create(image);
        return "created";
    }
}
