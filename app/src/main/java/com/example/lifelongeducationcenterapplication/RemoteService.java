package com.example.lifelongeducationcenterapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RemoteService {
    public static final String BASE_URL = "http://10.0.2.2:8080/LifelongEducationCenterApplication-back/Android/";


    @GET("lecture.jsp")
    Call<List<MainLecture>> lecture();//수강목록


    @FormUrlEncoded
    @POST("login.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<List<CommunicationResult>> userLogin(
            @Field("name") String name,
            @Field("phoneNumber") String phoneNumber,
            @Field("password") String password); //로그인

    @FormUrlEncoded
    @POST("register.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<List<RegisterResult>> userRegister(
                            @Field("phoneNumber") String phoneNumber,
                            @Field("pw") String pw,
                            @Field("course") String course,
                            @Field("addressNumber") String addressNumber,
                            @Field("address") String address,
                            @Field("detailedAddress") String detailedAddress,
                            @Field("birthday") String birthday,
                            @Field("name") String name); //회원가입

}
