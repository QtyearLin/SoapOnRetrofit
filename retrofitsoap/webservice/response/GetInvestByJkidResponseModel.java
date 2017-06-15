package com.smilexie.retrofitsoap.webservice.response;



import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * 用户角色返回
 * Created by SmileXie on 16/7/15.
 */

@Root(name = "GetInvestByJkidResponse")
@Namespace(reference = "http://tempuri.org/")
public class GetInvestByJkidResponseModel {

    @ElementList(name = "GetInvestByJkidResult")
    public String result;

}
