package com.example.prayertime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class AccessData extends AppCompatActivity {

    private RequestQueue mQueue;
    TextView fj,dh,as,mg,Isha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_data);
        fj=findViewById(R.id.Fajrdata);
        dh=findViewById(R.id.Dhurdata);
        as=findViewById(R.id.Asrata);
        mg=findViewById(R.id.Magribdata);
        Isha=findViewById(R.id.Ishadata);
       /* mTextViewResult.findViewById(R.id.userinput);*/
        mQueue = Volley.newRequestQueue(this);
        jsonParse();

    }
    private void jsonParse() {

        String url = "http://api.aladhan.com/v1/calendarByCity?city=London&country=Karachi&method=2&month=02&year=2022";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < 1; i++) {
                                JSONObject time = jsonArray.getJSONObject(i);
                                JSONObject firstName = time.getJSONObject("timings");
                                String fajr = firstName.get("Fajr").toString();
                                String dhur = firstName.get("Dhuhr").toString();
                                String asr = firstName.get("Asr").toString();
                                String magrib = firstName.get("Maghrib").toString();
                                String isha = firstName.get("Isha").toString();
                                fj.setText(fajr);
                                dh.setText(dhur);
                                as.setText(asr);
                                mg.setText(magrib);
                                Isha.setText(isha);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Network Error",  Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}