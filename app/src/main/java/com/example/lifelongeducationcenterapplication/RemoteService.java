package com.example.lifelongeducationcenterapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RemoteService {
    public static final String BASE_URL = "http://" + Ipaddress.ip  +"/LifelongEducationCenterApplication-back/Android/";


    @GET("lecture.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<List<Lecture>> lecture(
            @Query("division") String division);//수강목록


    @GET("lectureDetail.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<List<LectureDetail>> lectureDetail(
            @Query("number") int number);//수강 상세보기



    @GET("lectureWeek.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<List<LectureWeek>> lectureWeek(
            @Query("number")   int number);//수강목록 주차별 수업



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
