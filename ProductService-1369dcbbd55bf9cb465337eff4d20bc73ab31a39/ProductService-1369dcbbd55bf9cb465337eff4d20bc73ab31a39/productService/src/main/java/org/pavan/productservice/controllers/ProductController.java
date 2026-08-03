package org.pavan.productservice.controllers;


import org.pavan.productservice.dtos.ErrorResponseDto;
import org.pavan.productservice.dtos.ProductDto;
import org.pavan.productservice.exceptions.NotFoundException;
import org.pavan.productservice.models.Product;
import org.pavan.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProductController
{
    private final ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getAllProducts()
    {
       return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException
    {

        Optional<Product> productOptional = productService.getSingleProduct(productId);

        if(productOptional.isEmpty())
        {
            throw new NotFoundException("No Product with product id: "+ productId);
        }

        Product product = productService.getSingleProduct(productId).get();
        ResponseEntity<Product> response = new ResponseEntity<>(product,HttpStatus.OK);

        return response;

    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto product)
    {
        Product newProduct = productService.addNewProduct(product);

        ResponseEntity<Product> response = new ResponseEntity<>(newProduct, HttpStatus.OK);

        return response;
    }

    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productdto)
    {
        Product product = new Product();
        product.setId(productdto.getId());
        product.setPrice(productdto.getPrice());
        product.setCategory(product.getCategory());
        product.setTitle(product.getTitle());
        product.setImageUrl(product.getImageUrl());

        return productService.updateProduct(productId,product);
    }

    @PutMapping("/{productId}")
    public Product replaceProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productdto)
    {
        Product product = new Product();
        product.setId(productdto.getId());
        product.setPrice(productdto.getPrice());
        product.setCategory(product.getCategory());
        product.setTitle(product.getTitle());
        product.setImageUrl(product.getImageUrl());

        return productService.replaceProduct(productId,product);
    }

    @DeleteMapping("/{productId}")
    public boolean deleteProduct(@PathVariable("productId") Long productId)
    {
        return productService.deleteProduct(productId);
    }

}
