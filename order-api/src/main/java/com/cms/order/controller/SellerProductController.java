package com.cms.order.controller;

import com.cms.order.domain.model.Product;
import com.cms.order.domain.product.AddProductForm;
import com.cms.order.domain.product.AddProductItemForm;
import com.cms.order.domain.product.ProductDto;
import com.cms.order.domain.product.ProductItemDto;
import com.cms.order.service.ProductItemService;
import com.cms.order.service.ProductService;
import com.sohee.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller/product")
@RequiredArgsConstructor
public class SellerProductController {

    private final ProductService productService;
    private final ProductItemService productItemService;
    private final JwtAuthenticationProvider provider;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                        @RequestBody AddProductForm form) {

        Product product = productService.addProduct(provider.getUserVo(token).getId(), form);
        return ResponseEntity.ok(ProductDto.from(product));
    }

    @PostMapping("/item")
    public ResponseEntity<?> addProductItem(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                            @RequestBody AddProductItemForm form) {

        Product product = productItemService.addProductItem(provider.getUserVo(token).getId(), form);
        return ResponseEntity.ok(ProductDto.from(product));
    }
}
