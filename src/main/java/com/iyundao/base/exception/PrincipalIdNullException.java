package com.iyundao.base.exception;

/**
 * @ClassName: PrincipalIdNullException
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/7/12 9:41
 * @Description:
 * @Version: V2.0
 */
public class PrincipalIdNullException extends RuntimeException {

    private static final String MESSAGE = "Principal Id shouldn't be null!";

    public PrincipalIdNullException(Class clazz, String idMethodName) {
        super(clazz + " id field: " +  idMethodName + ", value is null\n" + MESSAGE);
    }
}
