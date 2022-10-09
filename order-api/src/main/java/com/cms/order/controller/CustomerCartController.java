package com.cms.order.controller;

import com.cms.domain.config.JwtAuthenticationProvider;
import com.cms.order.domain.product.AddProductCartForm;
import com.cms.order.domain.redis.Cart;
import com.cms.order.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/cart")
@RequiredArgsConstructor
public class CustomerCartController {

    // 임시 코드
    private final CartService cartService;
    private final JwtAuthenticationProvider provider;

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                        @RequestBody AddProductCartForm form) {
        return ResponseEntity.ok(cartService.addCart(provider.getUserVo(token).getId(), form));
    }
}
