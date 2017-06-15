package com.smilexie.retrofitsoap.webservice.response;

import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;

import com.smilexie.retrofitsoap.webservice.ResponseBean;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Administrator on 2017/3/17.
 */

class StringResponseBodyConverter implements Converter<ResponseBody, ResponseBean<M>> {

    @Override
    public ResponseBean<> convert(ResponseBody value) throws IOException {
        XmlPullParser pullParser = Xml.newPullParser();
        ResponseBean response = null;
        // 设置需要解析的XML数据
        try {
            pullParser.setInput(value.byteStream(), "UTF-8");
            // 取得事件
            int event = pullParser.getEventType();

            // 若为解析到末尾
            while (event != XmlPullParser.END_DOCUMENT) // 文档结束
            {
                String nodeName = pullParser.getName();
                switch (event) {
                    case XmlPullParser.START_DOCUMENT: // 文档开始
                        break;
                    case XmlPullParser.START_TAG: // 标签开始
                        Log.e("START_TAG", nodeName);
                        if (nodeName.contains("Result")) {
                            String context = pullParser.nextText();
                            Log.e("----", context);
                            ByteArrayInputStream inputStream = new ByteArrayInputStream(context.getBytes());
                            XmlPullParser xmlPullParser = Xml.newPullParser();
                            xmlPullParser.setInput(inputStream, "UTF-8");
                            // 取得事件
                            int eventType = xmlPullParser.getEventType();

                            // 若为解析到末尾
                            while (eventType != XmlPullParser.END_DOCUMENT) // 文档结束
                            {
                                String nodeTempName = xmlPullParser.getName();
                                switch (eventType) {
                                    case XmlPullParser.START_DOCUMENT: // 文档开始
                                        response = new ResponseBean();
                                        break;
                                    case XmlPullParser.START_TAG: // 标签开始
                                        Log.e("START_TAG", nodeTempName);
                                        if (nodeTempName.equals("Code")) {
                                            String code = xmlPullParser.nextText();
                                            response.code = Integer.parseInt(code);
                                            Log.e("code", code);
                                        } else
                                        if (nodeTempName.equals("Message")) {
                                            String msg = xmlPullParser.nextText();
                                            response.msg = msg;
                                            try {
                                                if (!TextUtils.isEmpty(msg)) {
//                                                    response.body =
                                                    JSONObject jsonObject = new JSONObject(response.msg );
                                                    Log.e("-==",jsonObject.toString());
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            Log.e("msg", response.msg);
                                        }
                                        break;

                                }
                                eventType = xmlPullParser.next(); // 下一个标签
                            }
//
                        }

                        break;
                    case XmlPullParser.END_TAG: // 标签结束
                        break;
                }
                event = pullParser.next(); // 下一个标签
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return response;
    }

}
