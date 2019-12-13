package info.ymjs.api.common.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangqingqing
 * @version BaseRequest, v0.1 2019/3/22 11:44
 */
public class BaseRequest implements Serializable {

    @ApiModelProperty(value = "当前页码")
    private int currPage = 1;

    @ApiModelProperty(value = "每页记录数")
    private int pageSize = 10;


    private Map<String,Object> dataScopeParams;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setDataScopeParams(Map<String, Object> dataScopeParams) {
        this.dataScopeParams = dataScopeParams;
    }

    public Map<String, Object> getDataScopeParams() {
        if (dataScopeParams == null) {
            dataScopeParams = new HashMap<>();
        }
        return dataScopeParams;
    }
}
