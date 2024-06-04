package vn.edu.hcmuaf.FocusAppProject.exception;

import org.springframework.security.core.AuthenticationException;

public class AccountNotVerifiedException extends AuthenticationException {
    public AccountNotVerifiedException(String msg) {
        super(msg);
    }
}
