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

    // 공지사항
    @GET("notice.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<List<Notice>> notice();//수강목록

    // 강의
    @GET("lecture.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<List<Lecture>> lecture(
            @Query("division") String division);//수강목록

    // 강의 세부 내용 
    @GET("lectureDetail.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<List<LectureDetail>> lectureDetail(
            @Query("number") int number);//수강 상세보기


    // 강의 주 내용
    @GET("lectureWeek.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<List<LectureWeek>> lectureWeek(
            @Query("number")   int number);//수강목록 주차별 수업



    //로그인
    @FormUrlEncoded
    @POST("login.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<CommunicationResult> userLogin(
            @Field("name") String name,
            @Field("phoneNumber") String phoneNumber,
            @Field("password") String password); //로그인

    
    //로그인 체크 
    @FormUrlEncoded
    @POST("loginCheck.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<CommunicationResult> loginCheck(
            @Field("name") String name,
            @Field("birth") String birth,
            @Field("sex") String sex); //로그인


    // 회원가입
    @FormUrlEncoded
    @POST("register.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<RegisterResult> userRegister(
                            @Field("phoneNumber") String phoneNumber,
                            @Field("pw") String pw,
                            @Field("course") String course,
                            @Field("addressNumber") String addressNumber,
                            @Field("address") String address,
                            @Field("detailedAddress") String detailedAddress,
                            @Field("birthday") String birthday,
                            @Field("name") String name,
                            @Field("sex") String sex); //회원가입

}
