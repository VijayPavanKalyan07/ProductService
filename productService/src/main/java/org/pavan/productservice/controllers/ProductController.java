package org.pavan.productservice.controllers;


import org.pavan.productservice.dtos.ProductDto;
import org.pavan.productservice.models.Product;
import org.pavan.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;


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
    public String getAllProducts()
    {
        return "Getting All Products";
    }

    @GetMapping("/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId)
    {
        return "Returning Single Product with id: "+ productId;
    }

    @PostMapping()
    public String addNewProduct(@RequestBody ProductDto productdto)
    {
        return "Adding new product "+ productdto;
    }



    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId)
    {
        return "Updating a product with id: " + productId;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId)
    {
        return "Deleting a product with id: " + productId;
    }
}
