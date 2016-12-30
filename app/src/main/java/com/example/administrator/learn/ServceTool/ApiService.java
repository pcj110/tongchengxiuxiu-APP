package com.example.administrator.learn.ServceTool;

import android.content.Context;

import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.example.administrator.learn.Model.PersonalInfo;
import com.example.administrator.learn.Model.StartPushInfo;
import com.example.administrator.learn.Model.apiSuccessInfo;
import com.example.administrator.learn.Model.putPictureInfo;
import com.example.administrator.learn.Model.stoppushInfo;
import com.example.administrator.learn.Tool.Sharedparms;
import com.google.gson.reflect.TypeToken;
import com.rxandroidnetworking.RxAndroidNetworking;

import java.io.File;
import java.util.HashMap;


/**
 * Created by Administrator on 2016/12/28.
 */

public class ApiService {
    public interface ParsedRequestListener<T> {
        void onResponseResult(T t);

        void _OnError(String errormessage);
    }

    /**
     * 获取个人信息
     *
     * @param uid
     * @param parsedRequestListener
     */
    public static void getPersonnalInfo(String uid, final ParsedRequestListener<PersonalInfo> parsedRequestListener) {
        HashMap<String, String> parametMap = new HashMap<>();
        parametMap.put("uid", uid);
        RxAndroidNetworking.post(Sharedparms.Http_url.URL_DOMAIN + Sharedparms.Http_url.GETPERSONAL)
                .addBodyParameter(parametMap)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsParsed(new TypeToken<PersonalInfo>() {
                }, new com.androidnetworking.interfaces.ParsedRequestListener<PersonalInfo>() {
                    @Override
                    public void onResponse(PersonalInfo response) {
//
                        parsedRequestListener.onResponseResult(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                    }
                });
    }

    /**
     * 保存封面照片
     *
     * @param path
     * @param parsedRequestListener
     */
    public static void putPicture(String path, final ParsedRequestListener<putPictureInfo> parsedRequestListener) {
        RxAndroidNetworking.upload(Sharedparms.Http_url.URL_DOMAIN + Sharedparms.Http_url.POSTPICTURE)
                .addMultipartFile("file", new File(path))
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsParsed(new TypeToken<putPictureInfo>() {
                }, new com.androidnetworking.interfaces.ParsedRequestListener<putPictureInfo>() {
                    @Override
                    public void onResponse(putPictureInfo response) {
                        parsedRequestListener.onResponseResult(response);
//
                    }

                    @Override
                    public void onError(ANError anError) {
                        parsedRequestListener._OnError(anError.getMessage());

                    }
                });
    }

    /**
     * 开始直播
     *
     * @param title
     * @param uid
     * @param liveimage
     * @param parsedRequestListener
     */
    public static void StartPush(String title, String uid, String liveimage, final ParsedRequestListener parsedRequestListener) {
        HashMap<String, String> bodyMap = new HashMap<>();
        bodyMap.put("uid", uid);
        bodyMap.put("liveTitle", title);
        bodyMap.put("liveImg", liveimage);
        RxAndroidNetworking.post(Sharedparms.Http_url.URL_DOMAIN + Sharedparms.Http_url.STARTPUSH)
                .addBodyParameter(bodyMap)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsParsed(new TypeToken<StartPushInfo>() {
                }, new com.androidnetworking.interfaces.ParsedRequestListener<StartPushInfo>() {
                    @Override
                    public void onResponse(StartPushInfo response) {
                        parsedRequestListener.onResponseResult(response);

                    }

                    @Override
                    public void onError(ANError anError) {
                        parsedRequestListener._OnError(anError.getMessage());
                    }
                });


    }

    /**
     * 分享成功后告诉后台
     *
     * @param liveid
     * @param parsedRequestListener
     */
    public static void ShareLive(String liveid, final ParsedRequestListener<apiSuccessInfo> parsedRequestListener) {
        HashMap<String, String> bodyMap = new HashMap<>();
        bodyMap.put("liveId", liveid);
        RxAndroidNetworking.post(Sharedparms.Http_url.URL_DOMAIN + Sharedparms.Http_url.SHAREURL)
                .addBodyParameter(bodyMap)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsParsed(new TypeToken<apiSuccessInfo>() {
                }, new com.androidnetworking.interfaces.ParsedRequestListener<apiSuccessInfo>() {
                    @Override
                    public void onResponse(apiSuccessInfo response) {
                        parsedRequestListener.onResponseResult(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        parsedRequestListener._OnError(anError.getMessage());
                    }
                });
    }

    /**
     * 结束直播
     *
     * @param uid
     * @param liveId
     * @param parsedRequestListener
     */
    public static void stopPush(String uid, String liveId, final ParsedRequestListener<stoppushInfo> parsedRequestListener) {
        HashMap<String, String> bodyMap = new HashMap<>();
        bodyMap.put("liveId", liveId);
        bodyMap.put("uid", uid);
        RxAndroidNetworking.post(Sharedparms.Http_url.URL_DOMAIN + Sharedparms.Http_url.STOPPUSH)
                .addBodyParameter(bodyMap)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsParsed(new TypeToken<stoppushInfo>() {
                }, new com.androidnetworking.interfaces.ParsedRequestListener<stoppushInfo>() {
                    @Override
                    public void onResponse(stoppushInfo response) {
                        parsedRequestListener.onResponseResult(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        parsedRequestListener._OnError(anError.getMessage());
                    }
                });
    }
}
