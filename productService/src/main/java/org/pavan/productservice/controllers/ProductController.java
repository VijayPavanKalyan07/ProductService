package org.pavan.productservice.controllers;


import org.pavan.productservice.dtos.ProductDto;
import org.pavan.productservice.models.Product;
import org.pavan.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId)
    {
        Product product = productService.getSingleProduct(productId);
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

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product)
    {
        return productService.updateProduct(productId,product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId)
    {
        productService.deleteProduct(productId);
    }
}
