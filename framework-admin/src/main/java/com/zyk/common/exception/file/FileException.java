package com.zyk.common.exception.file;

import com.zyk.common.exception.BaseException;

/**
 * 文件信息异常类
 * 
 * @author framework
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
