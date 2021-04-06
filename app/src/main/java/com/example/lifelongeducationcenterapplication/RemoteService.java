package com.example.lifelongeducationcenterapplication;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RemoteService {
    public static final String BASE_URL = "http://10.0.2.2:8080/LifelongEducationCenterApplication-back/Android/";


    @GET("lecture.jsp")
    Call<List<MainLecture>> listLecture();//수강목록


    @FormUrlEncoded
    @POST("login.jsp")
    Call<List<Userlogin>> userLogin(
            @Field("name") String name,
            @Field("phoneNumber") String phoneNumber,
            @Field("pw") String pw); //로그인

    @FormUrlEncoded
    @POST("register.jsp")
    Call<Void> userRegister(
                            @Field("phoneNumber") String phoneNumber,
                            @Field("pw") String pw,
                            @Field("course") String course,
                            @Field("address") String address,
                            @Field("birthday") String birthday,
                            @Field("name") String name); //회원가입

}
