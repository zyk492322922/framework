package com.zyk.api.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.zyk.api.common.exception.BusinessException;
import com.zyk.api.common.response.ResultCode;
import com.zyk.api.config.SystemConfig;
import com.zyk.api.util.HttpHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.alibaba.fastjson.JSONObject;
import com.hyjf.framework.common.util.SpringUtils;
import com.hyjf.framework.starter.redis.util.RedisUtils;

import com.zyk.api.common.exception.BusinessException;
import com.zyk.api.common.response.ResultCode;
import com.zyk.api.config.SystemConfig;
import com.zyk.api.constant.AccessToken;
import com.zyk.api.constant.BodyReaderHttpServletRequestWrapper;
import com.zyk.api.constant.ParameterRequestWrapper;
import com.zyk.api.util.HttpHelper;
import com.zyk.api.util.RedisConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * json传参过滤,将来可能进行替换
 *
 * @author zhangyk
 * @date 2019/5/23 10:45
 */
@Component
@Slf4j
@WebFilter(filterName = "DataFilter", urlPatterns = "/*")
public class JsonDataFilter implements Filter {

    private SystemConfig systemConfig = SpringUtils.getBean(SystemConfig.class);


    public static final String PARAM_NAME_EXCLUSIONS = "exclusions";
    private Set<String> excludesPattern;
    protected String contextPath;
    protected PathMatcher matcher = new AntPathMatcher();

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String param = filterConfig.getInitParameter(PARAM_NAME_EXCLUSIONS);
        if (param != null && param.trim().length() != 0) {
            this.excludesPattern = new HashSet(Arrays.asList(param.split("\\s*,\\s*")));
        }

    }


    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain) throws IOException {
        PrintWriter out = null;
        try {
            HttpServletRequest request = (HttpServletRequest) srequest;
            if (!this.isExclusion(request.getRequestURI())) {
                String body = HttpHelper.getBodyString(request);
                JSONObject param = JSONObject.parseObject(body);
                ParameterRequestWrapper wrapRequest = null;
                Map m = param;
//                if (requireToken(request.getRequestURI())) {
//                    String token = param.getString("token");
//                    isTrue(StringUtils.isNotEmpty(token), ResultCode.ERR_USER_TOKEN_EXIST);
//                    AccessToken accessToken = RedisUtils.getObj(RedisConstants.USER_TOKEN_KEY + token, AccessToken.class);
//                    isTrue(accessToken != null, ResultCode.ERR_USER_LOGIN_EXPIRE);
//                }
                //data存进param
                if(param != null){
                    Set<String> keys = param.keySet();
                    for (String key : keys) {
                        String value = param.getString(key);
                        m.put(key, value);
                    }
                }
                if(m == null){
                    m = new HashMap();
                }
                HttpServletRequest req = request;
                wrapRequest = new ParameterRequestWrapper(req, m);
                // 由于流只可以读取一次,防止出现刘关闭问题,重新封装一层request进行传递
                ServletRequestWrapper servletRequestWrapper;
                if (wrapRequest == null) {
                    servletRequestWrapper = new BodyReaderHttpServletRequestWrapper(request, m);
                } else {
                    servletRequestWrapper = new BodyReaderHttpServletRequestWrapper(wrapRequest, m);
                }

                filterChain.doFilter(servletRequestWrapper, sresponse);
            } else {
                filterChain.doFilter(request, sresponse);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            sresponse.setCharacterEncoding("UTF-8");
            sresponse.setContentType("application/json; charset=utf-8");
            out = sresponse.getWriter();
            JSONObject res = new JSONObject();
            res.put("status", e.getStatus());
            res.put("statusDesc", e.getStatusDesc());
            res.put("data", "");
            out.append(res.toString());
            out.flush();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                out.close();
            }
        }
    }


    @Override
    public void destroy() {


    }


    public boolean isExclusion(String requestURI) {
        if (this.excludesPattern == null) {
            return false;
        } else {
            if (this.contextPath != null && requestURI.startsWith(this.contextPath)) {
                requestURI = requestURI.substring(this.contextPath.length());
                if (!requestURI.startsWith("/")) {
                    requestURI = "/" + requestURI;
                }
            }

            Iterator i$ = this.excludesPattern.iterator();

            String pattern;
            do {
                if (!i$.hasNext()) {
                    return false;
                }

                pattern = (String) i$.next();
            } while (!this.matcher.match(pattern, requestURI));

            return true;
        }
    }

    /**
     * 替代Assert.isTrue - 因为前端要求统一返回格式
     */
    private void isTrue(boolean condition, ResultCode resultCode) throws Exception {
        if (!condition) {
            //throw new Exception(statusDesc);
            throw new BusinessException(resultCode);
        }
    }

    /**
     * @param
     * @return
     * @description 判断某一个请求的接口是否需要登录
     * @auth sunpeikai
     */
    private boolean requireToken(String url) {
        String ignoreUrl = systemConfig.getIgnoreUrl();
        List<String> ignoreUrlList = Arrays.asList(ignoreUrl.split(","));
        if (ignoreUrlList.contains(url)) {
            return false;
        }
        return true;
    }
}

