package com.example.lifelongeducationcenterapplication;

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



    @GET("login.jsp")
    Call<Userlogin> userLogin(
            @Query("name") String name,
            @Query("phoneNumber") String phoneNumber,
            @Query("pw") String pw); //로그인


    @POST("register.jsp")
    Call<Void> userRegister(
                            @Query("phoneNumber") String phoneNumber,
                            @Query("pw") String pw,
                            @Query("course") String course,
                            @Query("address") String address,
                            @Query("birthday") String birthday,
                            @Query("name") String name); //회원가입


}
