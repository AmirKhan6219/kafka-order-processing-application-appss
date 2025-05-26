package com.shoponline.product.service;

import com.shoponline.product.dto.ProductDto;
import com.shoponline.product.dto.ProductPurchaseRequestDto;
import com.shoponline.product.dto.ProductPurchaseResponse;
import com.shoponline.product.entity.Category;
import com.shoponline.product.entity.Product;
import com.shoponline.product.exception.ProductPurchaseException;
import com.shoponline.product.exception.ResourceNotFoundException;
import com.shoponline.product.mapper.ProductMapper;
import com.shoponline.product.repository.CategoryRepository;
import com.shoponline.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, CategoryRepository categoryRepository, ProductMapper mapper) {
        this.productRepository = repository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }


    public ProductDto createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productDto.getCategoryId()));

        Product product = mapper.toProduct(productDto);
        product.setCategory(category);
        product = productRepository.save(product);

//        var product = repository.save(mapper.toProduct(productDto));
        return mapper.toProductDto(product);
    }

    @Transactional(rollbackFor = ProductPurchaseException.class)
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequestDto> productPurchaseRequest) {
        // Extract Product IDs
        var productIds = productPurchaseRequest
                .stream()
                .map(ProductPurchaseRequestDto::getProductId)
                .toList();
        // Retrieve Stored Products from the Database
        var productInStock = productRepository.findAllByIdInOrderById(productIds);
        // Check for Missing Products
        if(productIds.size() != productInStock.size())
            throw new ProductPurchaseException("Product doesn't exist");

        // Sort the Requested Products based on ProductId
        var sortedRequest = productPurchaseRequest
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequestDto::getProductId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        // Process Each Product and Check Stock
        for(int i=0; i < productInStock.size(); i++){
            var product = productInStock.get(i);
            var productRequest = sortedRequest.get(i);
            // Check for insufficient stock before performing any other operations
            if(product.getAvailableQuantity() < productRequest.getQuantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with id:: " + productRequest.getProductId());
            }
            // Update the available quantity
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.getQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            // Save the updated product and add to the purchased list
            productRepository.save(product);
            // Create a ProductPurchaseDto response for each processed product and adding it to a list of purchasedProducts
            purchasedProducts.add(mapper.toproductPurchaseResponse(product, productRequest.getQuantity()));
        }

        return purchasedProducts;
    }

    public ProductDto findById(long productId) {
        return productRepository.findById(productId)
                .map(mapper::toProductDto)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }

    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductDto)
                .toList();
    }

    public List<ProductDto> findByName(String productName) {
        return productRepository.findByName(productName)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "name", productName));
    }
}
