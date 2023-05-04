package com.example.volley;

import static com.android.volley.Request.Method;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String edtnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        EditText editText = findViewById(R.id.edt);
//        String edtnumber;
        TextView textView = findViewById(R.id.text);
        TextView textView1 = findViewById(R.id.text1);
        TextView textView2 = findViewById(R.id.text2);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtnumber = editText.getText().toString();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.GET, "https://jsonplaceholder.typicode.com/todos/" + edtnumber, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            textView.setText(response.getString("userId"));
                            textView1.setText(response.getString("id"));
                            textView2.setText(response.getString("title"));

                            Log.d("Myapp", "This response is " + response.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Myapp", "Something went wrong");
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });
    }

}