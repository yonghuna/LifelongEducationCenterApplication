package com.example.lifelongeducationcenterapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RemoteService {
    public static final String BASE_URL = "http://10.0.2.2:8080/LifelongEducationCenterApplication-back/Android/";

    @GET("lecture.jsp")
    Call<List<MainLecture>> listLecture();//수강목록
}
