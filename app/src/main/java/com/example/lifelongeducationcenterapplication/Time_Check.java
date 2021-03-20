package com.example.lifelongeducationcenterapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Time_Check {

    String TAG = Time_Check.class.getSimpleName() + " 테스트";
    Context mcontext;
    String serial_key;


    class check_date extends AsyncTask<String, Void, String> {
        StringBuffer Buffer = new StringBuffer();

        @Override
        protected String doInBackground(String... serialkey) {
            String get_serialkey = serialkey[0];
            String get_json = "";
            try {
                String urlAddr = "http://localhost:8080/LifelongEducationCenterApplication-back/Android/server.jsp" + get_serialkey;
                URL url = new URL(urlAddr);
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                if (conn != null) {
                    conn.setConnectTimeout(20000);
                    conn.setUseCaches(false);
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        // 서버에서 읽어오기 위한 스트림 객체
                        InputStreamReader isr = new InputStreamReader(
                                conn.getInputStream());
                        // 줄단위로 읽어오기 위해 BufferReader로 감싼다.
                        BufferedReader br = new BufferedReader(isr);
                        // 반복문 돌면서읽어오기
                        while (true) {
                            String line = br.readLine();
                            if (line == null) {
                                break;
                            }
                            Buffer.append(line);
                        }
                        br.close();
                        conn.disconnect();
                    }
                }
                get_json = Buffer.toString();
                Log.d(TAG, "get_json: " + get_json);
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("에러 ", e.getMessage());
            }
            return get_json;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, " <<<<<onPostExecute>>>> ");
            try {
                JSONArray jarray = new JSONObject(result).getJSONArray(serial_key);
                if (jarray != null) {
                    JSONObject jsonObject = jarray.getJSONObject(0);
                    String Start = jsonObject.getString("START_TIME");
                    String Stop = jsonObject.getString("STOP_TIME");
                    String REG = jsonObject.getString("REG_TIME");

                    // null을 가끔 못 읽어오는 때가 있다고 하기에 써봄
                    //String Start = jsonObject.optString("START_TIME", "text on no value");
                    //String Stop = jsonObject.optString("STOP_TIME", "text on no value");
                    //String REG = jsonObject.optString("REG_TIME", "text on no value");
                    Log.d(TAG, Start + "/" + Stop + "/" + REG);
                } else {
                    Toast.makeText(mcontext, "123", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

}


