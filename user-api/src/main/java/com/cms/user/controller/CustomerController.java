package com.cms.user.controller;

import com.cms.user.domain.customer.CustomerDto;
import com.cms.user.domain.model.Customer;
import com.cms.user.exception.CustomException;
import com.cms.user.service.CustomerService;
import com.sohee.domain.common.UserVo;
import com.sohee.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cms.user.exception.ErrorCode.NOT_FOUND_USER;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomerService customerService;

    @GetMapping("/getInfo")
    public ResponseEntity<?> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
        UserVo vo = jwtAuthenticationProvider.getUserVo(token);
        Customer customer = customerService.findByIdAndEmail(vo.getId(), vo.getEmail())
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        return ResponseEntity.ok(CustomerDto.from(customer));
    }
}
