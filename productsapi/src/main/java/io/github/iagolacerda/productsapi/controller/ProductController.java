package io.github.iagolacerda.productsapi.controller;

import io.github.iagolacerda.productsapi.model.Product;
import io.github.iagolacerda.productsapi.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("products")
public class ProductController {

  private ProductRepository productRepository;

  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping
  public List<Product> get (@RequestParam("name") String name) {
    return productRepository.findByName(name);
  }

  @GetMapping("{id}")
  public Product getById (@PathVariable("id") String id) {
    return productRepository.findById(id).orElse(null);
  }

  @PostMapping
  public Product save (@RequestBody Product product) {
    System.out.println("Product receive: " + product);
    var id = UUID.randomUUID().toString();

    product.setId(id);
    productRepository.save(product);
    return product;
  }

  @PutMapping("{id}")
  public void update (@PathVariable("id") String id, @RequestBody Product product) {
    product.setId(id);
    productRepository.save(product);
  }

  @DeleteMapping("{id}")
  public void delete (@PathVariable("id") String id) {
    productRepository.deleteById(id);
  }
}
