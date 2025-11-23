package com.uade.tpo.ecommerce.controllers;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uade.tpo.ecommerce.entity.Image;
import com.uade.tpo.ecommerce.entity.Product;
import com.uade.tpo.ecommerce.entity.dto.ImageResponse;
import com.uade.tpo.ecommerce.service.inter.ImageService;
import com.uade.tpo.ecommerce.service.inter.ProductService;

@RestController
@RequestMapping("/images")
@CrossOrigin
public class ImagesController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;

    // GET: devolver imagen asociada a un producto
    @GetMapping
    public ResponseEntity<ImageResponse> displayImage(@RequestParam("id") Long id)
            throws IOException, SQLException {

        Image image = imageService.viewByProductId(id);

        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        String encodedString = Base64.getEncoder()
                .encodeToString(image.getImage().getBytes(1, (int) image.getImage().length()));

        ImageResponse response = ImageResponse.builder()
                .id(image.getId())
                .file(encodedString)
                .build();

        return ResponseEntity.ok(response);
    }

    // POST: subir imagen como multipart form-data
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addImagePost(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("productId") Long productId)
            throws IOException, SQLException {

        Product product = productService.getProductById(productId)
                .orElse(null);

        if (product == null) {
            return ResponseEntity.badRequest().body("Producto no encontrado");
        }

        byte[] bytes = file.getBytes();
        Blob blob = new SerialBlob(bytes);

        Image image = Image.builder()
                .name(name)
                .image(blob)
                .product(product)
                .build();

        imageService.create(image);

        return ResponseEntity.ok("created");
    }
}
