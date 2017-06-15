package com.smilexie.retrofitsoap.webservice.response;

import org.simpleframework.xml.Serializer;

import java.io.IOException;
import java.io.OutputStreamWriter;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

import static android.provider.Telephony.Mms.Part.CHARSET;

/**
 * Created by Administrator on 2017/3/17.
 */


class MySimpleXmlRequestBodyConverter<T,M> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/xml; charset=UTF-8");
    private static final MediaType MEDIA_TYPE_STRING = MediaType.parse("text/plain; charset=UTF-8");

    private final Serializer serializer;

    MySimpleXmlRequestBodyConverter(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        if (value instanceof String) {
            return RequestBody.create(MEDIA_TYPE_STRING, value.toString());
        } else  {
            Buffer buffer = new Buffer();
            try {
                OutputStreamWriter osw = new OutputStreamWriter(buffer.outputStream(), CHARSET);
                serializer.write(value, osw);
                osw.flush();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        }



    }
}
