package com.cms.user.application;

import com.cms.user.domain.SignInForm;
import com.cms.user.domain.model.Customer;
import com.cms.user.exception.CustomException;
import com.cms.user.service.CustomerService;
import com.sohee.domain.common.UserType;
import com.sohee.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.cms.user.exception.ErrorCode.LOGIN_CHECK_FAIL;
import static com.sohee.domain.common.UserType.CUSTOMER;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    public String customerLoginToken(SignInForm form) {
        // 1. 로그인 가능 여부
        Customer customer = customerService.findValidCustomer(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));

        // 2. 토큰 발행
        // 3. 토큰을 response함
        return jwtAuthenticationProvider.createToken(customer.getEmail(), customer.getId(), CUSTOMER);
    }
}
