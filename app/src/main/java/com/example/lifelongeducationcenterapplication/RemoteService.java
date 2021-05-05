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
    Call<List<Notice>> notice();

    // 공지사항 내용
    @GET("noticeEnter.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<Notice> noticeEnter(
            @Query("number") int number);

    // 강의
    @GET("lecture.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<List<Lecture>> lecture(
            @Query("division") String division);//수강목록

    // 비로그인 추천 강의
    @GET("mainLecture.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<List<Lecture>> mainLecture();//비 로그인 일때 수강목록




    // 강의 세부 내용 
    @GET("lectureDetail.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<List<LectureDetail>> lectureDetail(
            @Query("number") int number);//수강 상세보기


    // 마이페이지 내 정보 읽기
    @GET("myPageInfo.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<UserInfo> myPage(
            @Query("id") String id);//수강 상세보기

    // 수강내역 읽기
    @GET("enrollment.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<List<Enrollment>> enrollment(
            @Query("id") String id);//  수강된 강좌면 수강내역으로



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

    //일반 과정 회원정보 수정



    @FormUrlEncoded
    @POST("myPageInfoPost1.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<RegisterResult> myPageInfoPost1(
            @Field("id") String id,
            @Field("pwOK") String pwOK,
            @Field("phonenumber") String phonenumber,
            @Field("addressnumber") String addressnumber,
            @Field("address") String address,
            @Field("detailedaddress") String detailedaddress,
            @Field("password") String password); //로그인

    //학습은행제 과정 회원정보 수정
    @FormUrlEncoded
    @POST("myPageInfoPost2.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<RegisterResult> myPageInfoPost2(
            @Field("id") String id,
            @Field("pwOK") String pwOK,
            @Field("phonenumber") String phonenumber,
            @Field("addressnumber") String addressnumber,
            @Field("address") String address,
            @Field("detailedaddress") String detailedaddress,
            @Field("password") String password,
            @Field("education") String education,
            @Field("school") String school,
            @Field("major") String major,
            @Field("admissionmajor") String admissionmajor); //로그인

    //수강신청
    @FormUrlEncoded
    @POST("subjectRegister.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<RegisterResult> userSubjectRegister(
            @Field("id") String id,
            @Field("subjectnumber") int subjectnumber,
            @Field("subjectyear") int subjectyear,
            @Field("subjectsemester") int subjectsemester,
            @Field("subjectdivision") String subjectdivision
            ); //수강신청

    //수강 취소
    @FormUrlEncoded
    @POST("subjectCancel.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<RegisterResult> userSubjectCancel(
            @Field("id") String id,
            @Field("subjectnumber") int subjectnumber
    ); //수강 취소


    //글 쓰기
    @FormUrlEncoded
    @POST("writing.jsp")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Call<Void> userWriting(
            @Field("id") String id,
            @Field("title") String title,
            @Field("reportingdate") String reportingdate,
            @Field("secret") String secret,
            @Field("contents") String contents
    ); //글 쓰기




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
