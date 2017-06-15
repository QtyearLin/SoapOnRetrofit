package com.smilexie.retrofitsoap.webservice;

import java.util.concurrent.ConcurrentHashMap;

public class WSParams {
    private ConcurrentHashMap<String, Object> propertys;
    private ConcurrentHashMap<String, Object> headeValues;
    private Header header;

    private void init(int i) {
        propertys = new ConcurrentHashMap<String, Object>(i);
        headeValues = new ConcurrentHashMap<String, Object>(i);
        header = new Header();
    }

    private void init() {
        init(20);
    }

    /**
     * 构造器 为提高效率默认使用20个键值作为参数集的大小
     */
    public WSParams() {
        init();
    }

    public WSParams(int i) {
        init(i);
    }

    public void addProperty(String key, Object value) {
        if (key != null && value != null) {
            propertys.put(key, value);
        } else if (value == null) {
            propertys.put(key, "");
        } else {
            throw new RuntimeException("[Put property exception] key is NULL");
        }
    }

    public void removeProperty(String key) {
        if (!propertys.isEmpty() && propertys.containsKey(key)) {
            propertys.remove(key);
        }
    }

    public void removeHeader(String key) {
        if (!headeValues.isEmpty() && headeValues.containsKey(key)) {
            headeValues.remove(key);
        }
    }

    public void addHeadeValues(String key, Object value) {
        if (key != null && value != null) {
            headeValues.put(key, value);
        } else if (value == null) {
            headeValues.put(key, "");
        } else {
            throw new RuntimeException("[Put header exception] key is NULL");
        }
    }

    public void packageHeader(String headerName) {
        if (headerName != null && headeValues != null) {
            header.setHeaderName(headerName);
            header.setHeadeValues(headeValues);
        } else {
            throw new RuntimeException("[PackageHeader exception] headerName or values is NULL");
        }
    }

    public ConcurrentHashMap<String, Object> getPropertys() {
        return propertys;
    }

    public Header getHeader() {
        return header;
    }

    class Header {
        private String headerName;
        private ConcurrentHashMap<String, Object> headeValues;

        public String getHeaderName() {
            return headerName;
        }

        public void setHeaderName(String headerName) {
            this.headerName = headerName;
        }

        public ConcurrentHashMap<String, Object> getHeadeValues() {
            return headeValues;
        }

        public void setHeadeValues(ConcurrentHashMap<String, Object> headeValues) {
            this.headeValues = headeValues;
        }
    }
}
