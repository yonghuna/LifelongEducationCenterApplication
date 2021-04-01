package com.example.lifelongeducationcenterapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RemoteService {
    public static final String BASE_URL = "http://10.0.2.2:8080/LifelongEducationCenterApplication-back/Android/";

    @GET("lecture.jsp")
    Call<List<MainLecture>> listLecture();//수강목록

    @POST("login.jsp")
    Call<String> userLogin(@Field("id") String id,
                           @Field("phoneNumber") String phoneNumber,
                           @Field("pw") String pw
                            );//로그인

    @POST("register.jsp")
    Call<String> userRegister(@Field("name") String name,
                             @Field("name") String ,
                             @Field("name") String name,
                             @Field("name") String name,
                             //회원가입
}
