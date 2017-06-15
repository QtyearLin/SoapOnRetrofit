package com.smilexie.retrofitsoap.webservice.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 用户角色返回body
 * Created by SmileXie on 16/7/15.
 */
@Root(name = "Body")
public class GetInvestByJkidResponseBody {

    @Element(name = "GetInvestByJkidResponse", required = false)
    public GetInvestByJkidResponseModel GetInvestByJkidResponse;

}
