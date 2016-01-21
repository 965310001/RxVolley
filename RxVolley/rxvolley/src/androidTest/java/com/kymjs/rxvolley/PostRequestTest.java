package com.kymjs.rxvolley;

import android.os.Looper;
import android.test.AndroidTestCase;

import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.toolbox.FileUtils;
import com.kymjs.rxvolley.toolbox.Loger;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

/**
 * Created by kymjs on 1/21/16.
 */
public class PostRequestTest extends AndroidTestCase {

    HttpCallback callback;

    @Before
    public void setUp() throws Exception {
        callback = new HttpCallback() {
            @Override
            public void onPreStart() {
                Loger.debug("=====onPreStart");
                //为了简单,onPreStart遵从在哪个线程调用就在哪个线程回调
                Loger.debug("=====当前线程是主线程" + (Thread.currentThread() == Looper.getMainLooper
                        ().getThread()));
            }

            @Override
            public void onPreHttp() {
                super.onPreHttp();
                Loger.debug("=====onPreHttp");
                Loger.debug("=====当前线程是主线程" + (Thread.currentThread() == Looper.getMainLooper
                        ().getThread()));
            }

            @Override
            public void onSuccessInAsync(byte[] t) {
                super.onSuccessInAsync(t);
                Loger.debug("=====onSuccessInAsync" + new String(t));
                Loger.debug("=====当前线程是主线程" + (Thread.currentThread() == Looper.getMainLooper
                        ().getThread()));
            }

            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                Loger.debug("=====onSuccess" + t);
                Loger.debug("=====当前线程是主线程" + (Thread.currentThread() == Looper.getMainLooper
                        ().getThread()));
            }

            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                Loger.debug("=====当前线程是主线程" + (Thread.currentThread() == Looper.getMainLooper
                        ().getThread()));
                Loger.debug("=====onSuccessWithHeader" + headers.size() + new String(t));
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                Loger.debug("=====当前线程是主线程" + (Thread.currentThread() == Looper.getMainLooper
                        ().getThread()));
                Loger.debug("=====onFailure" + strMsg);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                Loger.debug("=====当前线程是主线程" + (Thread.currentThread() == Looper.getMainLooper
                        ().getThread()));
                Loger.debug("=====onFinish");
            }
        };
    }
//
//    /**
//     * 不知道为什么,在测试类中写,log会打印不完整,但是在activity中调用,日志就是完整的
//     */
//    @Test
//    public void testUploadProgress() throws Exception {
//        Loger.debug("main=====当前线程是主线程" + (Thread.currentThread() == Looper.getMainLooper
//                ().getThread()));
//        HttpParams params = new HttpParams();
//
//        params.putHeaders("cookie", "aliyungf_tc=AQAAAOEM/UExEAsAUAYscy4Da0FfTWqX;" +
//                "oscid=vv%2BiiKldi6wRaKbbRig0DDvMcIURmo56ZCZD2bfC83AsmxdhUxEVnr3ORNGz7BjiFlkpGQHUKJoRTzVAwy3oVtcO7JsM4nRIjEl6ZgM%2BmZgplCH0foAIiQ%3D%3D;");
//
//        params.put("uid", 863548);
//        params.put("msg", "睡觉");
//        params.put("img", new File(FileUtils.getSDCardPath() + "/request.png"));
//
//        RxVolley.post("http://192.168.1.11/action/api/tweet_pub", params,
//                new ProgressListener() {
//                    @Override
//                    public void onProgress(long transferredBytes, long totalSize) {
//                        //防止日志太多刷屏
//                        if (transferredBytes % 10000 == 0) {
//                            Loger.debug(transferredBytes + "=====" + totalSize);
//                            Loger.debug("=====当前线程是主线程" + (Thread.currentThread() == Looper
//                                    .getMainLooper().getThread()));
//                        }
//                    }
//                }, callback);
//    }

    @Test
    public void testPost() throws Exception {
        HttpParams params = new HttpParams();

        params.putHeaders("cookie", "aliyungf_tc=AQAAAOEM/UExEAsAUAYscy4Da0FfTWqX;" +
                "oscid=vv%2BiiKldi6wRaKbbRig0DDvMcIURmo56ZCZD2bfC83AsmxdhUxEVnr3ORNGz7BjiFlkpGQHUKJoRTzVAwy3oVtcO7JsM4nRIjEl6ZgM%2BmZgplCH0foAIiQ%3D%3D;");

        params.put("uid", 863548);
        params.put("msg", "睡觉");
        params.put("img", new File(FileUtils.getSDCardPath() + "/request.png"));

        RxVolley.post("http://192.168.1.11/action/api/tweet_pub", params, callback);
    }
}