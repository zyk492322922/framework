package info.ymjs.api.exception;


import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import info.ymjs.api.common.response.R;
import info.ymjs.api.constant.SYSConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理器
 * 
 * @author zqq
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler
{
    /**
     * 请求方式不支持
     */
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public R handleException(HttpRequestMethodNotSupportedException e)
    {
        log.error(e.getMessage(), e);
        return R.fail("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public R notFount(RuntimeException e)
    {
        log.error("运行时异常:", e);
        return R.fail(SYSConstant.GLOBAL_EXCEPTION_MESSAGE);
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return R.fail(SYSConstant.GLOBAL_EXCEPTION_MESSAGE);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(SystemException.class)
    public R systemException(SystemException e)
    {
        log.error(e.getMessage(), e);
        return R.fail(e.getStatus(),e.getStatusDesc());
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleException(MethodArgumentNotValidException e)
    {
        String errorMsg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage()+ "不能为空";
        log.error("参数校验异常:" + errorMsg);
        return R.fail(errorMsg);
    }

    /**
     * JSON 解析错误异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R handleException(HttpMessageNotReadableException e){
        log.error("JSON 解析异常:" + e.getMessage());
        return R.fail("JSON解析错误!");
    }

}
