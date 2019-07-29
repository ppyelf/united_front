package com.iyundao.base.exception;

/**
 * @ClassName: AuthenticationException
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/21 5:27
 * @Description: 认证异常
 * @Version: V2.0
 */
public class AuthenticationException extends PermissionException {

    private static final long serialVersionUID = 1L;

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

}
