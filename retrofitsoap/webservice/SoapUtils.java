package com.smilexie.retrofitsoap.webservice;

import android.text.TextUtils;
import android.util.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.smilexie.retrofitsoap.webservice.SoapUtils.Node.SERVER_NAMESPACE;
import static com.smilexie.retrofitsoap.webservice.SoapUtils.Node.V_BODY_E;
import static com.smilexie.retrofitsoap.webservice.SoapUtils.Node.V_BODY_S;
import static com.smilexie.retrofitsoap.webservice.SoapUtils.Node.V_ENVELOPE_E;
import static com.smilexie.retrofitsoap.webservice.SoapUtils.Node.V_HEAD_E;
import static com.smilexie.retrofitsoap.webservice.SoapUtils.Node.V_HEAD_S;
import static com.smilexie.retrofitsoap.webservice.SoapUtils.Node.sDefaultV;

/**
 * Created by Qtyearlin on 2017/3/17.
 */

public class SoapUtils {

    static class Node {
        public static final String V_11 = "<v:Envelope "
                + "xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" "
                + "xmlns:d=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:c=\"http://schemas.xmlsoap.org/soap/encoding/\" "
                + "xmlns:v=\"http://schemas.xmlsoap.org/soap/envelope/\">";

        public static final String SERVER_NAMESPACE = "http://tempuri.org/";
        public static final String V_HEAD_S = "<v:Header>";
        public static final String V_HEAD_E = "</v:Header>";
        public static final String V_BODY_S = "<v:Body>";
        public static final String V_BODY_E = "</v:Body>";
        public static final String V_ENVELOPE_E = "</v:Envelope>";
        public static String sDefaultV = V_11;

        public static String toStart(String name) {
            return "<" + name + ">";
        }

        public static String toEnd(String name) {
            return "</" + name + ">";
        }


    }

    public static String createSoapMessage(WSParams params, String methodName) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sDefaultV);
        WSParams.Header head = params.getHeader();
        StringBuffer append = stringBuffer.append(V_HEAD_S);
        stringBuffer.append(createSoapHeader(head, SERVER_NAMESPACE));
        stringBuffer.append(V_HEAD_E);
        stringBuffer.append(V_BODY_S);
        stringBuffer.append(createSoapBody(params.getPropertys(), SERVER_NAMESPACE, methodName));
        stringBuffer.append(V_BODY_E);

        stringBuffer.append(V_ENVELOPE_E);
        Log.d("-createSoapMessage-", stringBuffer.toString());
        return stringBuffer.toString();
    }

    private static String createSoapBody(ConcurrentHashMap<String, Object> propertys, String nameSpace, String methodName) {
        String bodyXml = "<" + methodName
                + " xmlns="
                + "\""
                + nameSpace
                + "\""
                + ">";
        StringBuffer sf = new StringBuffer(bodyXml);
        for (Map.Entry<String, Object> entry : propertys.entrySet()) {
            sf.append(Node.toStart(entry.getKey()));
            sf.append(entry.getValue());
            sf.append(Node.toEnd(entry.getKey()));
        }
        sf.append(Node.toEnd(methodName));
        Log.d("-createSoapBody-", sf.toString());
        return sf.toString();
    }

    private static String createSoapHeader(WSParams.Header head, String nameSpace) {
        if (head == null || null == head.getHeadeValues())
            return "";
        String headName = "";
        if (!TextUtils.isEmpty(head.getHeaderName())) {
            headName = "<" + head.getHeaderName() + " xmlns="
                    + "\""
                    + nameSpace
                    + "\""
                    + ">";
        }
        StringBuffer sf = new StringBuffer(headName);
        for (Map.Entry<String, Object> entry : head.getHeadeValues().entrySet()) {
            sf.append(Node.toStart(entry.getKey()));
            sf.append(entry.getValue());
            sf.append(Node.toEnd(entry.getKey()));
        }
        if (!TextUtils.isEmpty(head.getHeaderName())) {
            String endheadName = "</" + head.getHeaderName() + ">";
            sf.append(endheadName);
        }
        Log.d("-createSoapHeader--", sf.toString());
        return sf.toString();
    }
}


