package com.example.stockmanagement.productservice.services.impl;

import com.example.stockmanagement.productservice.enums.Language;
import com.example.stockmanagement.productservice.exception.ProductNotCreatedException;
import com.example.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.example.stockmanagement.productservice.repository.entity.Product;
import com.example.stockmanagement.productservice.repository.entity.ProductRepository;
import com.example.stockmanagement.productservice.request.ProductCreateRequest;
import com.example.stockmanagement.productservice.request.ProductUpdateRequest;
import com.example.stockmanagement.productservice.services.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductRepositotyServiceImpl implements IProductRepositoryService {
    private final ProductRepository productRepository;
    @Override
    public Product createProduct(Language language, ProductCreateRequest productCreateRequest) {
        log.debug("[{}][createProduct] -> request:{}",this.getClass().getSimpleName(),productCreateRequest);
        try{
            Product product=Product.builder()
                    .productName(productCreateRequest.getProductName())
                    .quantity(productCreateRequest.getQuantity())
                    .price(productCreateRequest.getPrice())
                    .deleted(false)
                    .build();
            Product productResponse =productRepository.save(product);
            log.debug("[{}][createProduct]-> response: {}",this.getClass().getSimpleName(),productResponse);
            return productResponse;
        }catch (Exception exception){
            throw new ProductNotCreatedException(language, FriendlyMessageCodes.PRODUCT_NOT_CREATED_EXCEPTION,"product request:" +productCreateRequest.toString());
        }
    }

    @Override
    public Product getProduct(Language language, Long productId) {
        return null;
    }

    @Override
    public List<Product> getProducts(Language language) {
        return null;
    }

    @Override
    public Product updateProduct(Language language, Long productId, ProductUpdateRequest productUpdateRequest) {
        return null;
    }

    @Override
    public Product deleteProduct(Language language, Long productId) {
        return null;
    }
}
