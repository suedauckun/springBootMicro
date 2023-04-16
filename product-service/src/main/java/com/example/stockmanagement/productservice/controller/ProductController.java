package com.example.stockmanagement.productservice.controller;

import com.example.stockmanagement.productservice.enums.Language;
import com.example.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.example.stockmanagement.productservice.exception.utils.FriendlyMessageUtils;
import com.example.stockmanagement.productservice.repository.entity.Product;
import com.example.stockmanagement.productservice.request.ProductCreateRequest;
import com.example.stockmanagement.productservice.response.FriendlyMessage;
import com.example.stockmanagement.productservice.response.InternalApiResponse;
import com.example.stockmanagement.productservice.response.ProductResponse;
import com.example.stockmanagement.productservice.services.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value="/api/1.0/product")///bir url istek yapildiginda o istek nasil islencek belirtmek icin kullanilir
@RequiredArgsConstructor
public class ProductController {
    private final IProductRepositoryService productRepositoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/{language}/create")
    public InternalApiResponse<ProductResponse> createProduct(@PathVariable("language")Language language,
                                                              @RequestBody ProductCreateRequest productCreateRequest){
        log.debug("[{}][createProduct] -> request: {}",this.getClass().getSimpleName(),productCreateRequest);
        Product product =productRepositoryService.createProduct(language,productCreateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][createProduct] -> response: {}",this.getClass().getSimpleName(),productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                .description(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_CREATED))
                .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    private ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productCreatedDate(product.getProductCreatedDate().getTime())
                .productUpdatedDate(product.getProductUpdatedDate().getTime())
                .build();
    }
}
