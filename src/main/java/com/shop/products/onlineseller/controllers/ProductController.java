package com.shop.products.onlineseller.controllers;

import com.shop.products.onlineseller.models.Product;
import com.shop.products.onlineseller.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(path="/add", method = RequestMethod.POST)
    public ResponseEntity addProduct(@Valid @RequestBody Product product){
        try {
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/get/id/{productId}", method = RequestMethod.GET)
    public ResponseEntity getProductId(@PathVariable Integer productId){
        Product product = productRepository.findByid(productId);
        if(product == null){
            return new ResponseEntity<>("Product not found ", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/get/category/{category}", method = RequestMethod.GET)
    public ResponseEntity getProductCategory(@PathVariable String category){
        List<Product> products = productRepository.findBycategory(category);
        if(products == null){
            return new ResponseEntity<>("Products not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( products, HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/product/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@RequestBody Product product, @PathVariable Integer id){
        Product existingProduct = productRepository.findByid(id);
        if(existingProduct == null){
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        product.setId(id);
        try {
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/product/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        Product product = productRepository.findByid(id);
        if(product == null){
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        try {
            productRepository.delete(product);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
