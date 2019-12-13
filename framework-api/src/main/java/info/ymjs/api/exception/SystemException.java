package info.ymjs.api.exception;

import info.ymjs.api.common.response.IResultCode;
import info.ymjs.api.constant.SYSConstant;
import info.ymjs.api.util.StringUtils;
import lombok.Data;

/**
 * 权限模块异常
 *
 * @author framework
 */
@Data
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected  int status;

    protected  String statusDesc;

    public SystemException(int status, String statusDesc) {
        super(statusDesc);
        this.status = status;
        this.statusDesc = statusDesc;
    }

    public SystemException(IResultCode iResultCode){
        super(iResultCode.getStatusDesc());
        this.status = iResultCode.getStatus();
        this.statusDesc = iResultCode.getStatusDesc();
    }

    public SystemException(IResultCode iResultCode, Object... params){
        super(StringUtils.getMessage(iResultCode.getStatusDesc(),params));
        this.status = iResultCode.getStatus();
        this.statusDesc = StringUtils.getMessage(iResultCode.getStatusDesc(),params);
    }

    public SystemException(String statusDesc) {
        super(statusDesc);
        this.status = SYSConstant.DEFAULT_FAILURE_CODE;
        this.statusDesc = statusDesc;
    }

    public SystemException(String statusDesc, Throwable e) {
        super(statusDesc, e);
        this.status = SYSConstant.DEFAULT_FAILURE_CODE;
        this.statusDesc = statusDesc;
    }
}
