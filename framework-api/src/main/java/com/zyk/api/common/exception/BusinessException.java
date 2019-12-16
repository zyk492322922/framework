package com.zyk.api.common.exception;

import com.zyk.api.common.response.IResultCode;
import com.zyk.api.constant.SYSConstant;
import com.zyk.api.util.StringUtils;
import lombok.Data;

/**
 * 业务异常
 *
 * @author framework
 */
@Data
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected  int status;

    protected  String statusDesc;

    public BusinessException(int status, String statusDesc) {
        super(statusDesc);
        this.status = status;
        this.statusDesc = statusDesc;
    }

    public BusinessException(IResultCode iResultCode){
        super(iResultCode.getStatusDesc());
        this.status = iResultCode.getStatus();
        this.statusDesc = iResultCode.getStatusDesc();
    }

    public BusinessException(IResultCode iResultCode, Object... params){
        super(StringUtils.getMessage(iResultCode.getStatusDesc(),params));
        this.status = iResultCode.getStatus();
        this.statusDesc = StringUtils.getMessage(iResultCode.getStatusDesc(),params);
    }

    public BusinessException(String statusDesc) {
        super(statusDesc);
        this.status = SYSConstant.DEFAULT_FAILURE_CODE;
        this.statusDesc = statusDesc;
    }

    public BusinessException(String statusDesc, Throwable e) {
        super(statusDesc, e);
        this.status = SYSConstant.DEFAULT_FAILURE_CODE;
        this.statusDesc = statusDesc;
    }
}
