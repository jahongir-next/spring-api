package com.codewithmosh.store.products;


import com.codewithmosh.store.carts.ProductDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final ProductService productService;


    @GetMapping("")
    public  Iterable<ProductDto> findAllProducts(
          @RequestParam(name = "categoryId", required = false) Byte catedoryId
    )
    {
        return productService.getAllProducts(catedoryId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") Long id){

       return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody CreateProductRequest request,
            UriComponentsBuilder uriBuilder
    ){

        var productDto = productService.createProduct(request);

        var uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable(name = "id") Long id,
           @RequestBody CreateProductRequest request
    ){
        var productDto = productService.updateProduct(id, request);

        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long id){

        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFound(){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("message", "Product was not found.")
        );
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("message", "Category was not found.")
        );
    }
}
