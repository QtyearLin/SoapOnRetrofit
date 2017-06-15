package com.smilexie.retrofitsoap.webservice.response;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/3/17.
 */

public class MyConvertBodyFactor extends Converter.Factory {
    private static Strategy strategy = new AnnotationStrategy();
    private static Serializer serializer = new Persister(strategy);

    public MyConvertBodyFactor() {
        super();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new StringResponseBodyConverter();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
//        return super.requestBodyConverter(type, annotations, retrofit);
        if (!(type instanceof Class)) {
            return null;
        }

        return new MySimpleXmlRequestBodyConverter(serializer);
    }

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations) {
        return super.stringConverter(type, annotations);
    }
}
