package com.go.testvolley;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Gooo Main";

    TextView tv_result;
    Button btn_get_one, btn_get_all, btn_post, btn_put, btn_delete;

    RequestQueue requestQueue;
    StringRequest stringRequest;
    JsonObjectRequest jsonObjectRequest;
    JsonArrayRequest jsonArrayObjectRequest;

    String url = "";
    // URL for testing method. get, post, put and delete from Volley to https://jsonplaceholder.typicode.com/guide.html
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_result = findViewById(R.id.textView);
        btn_get_one = findViewById(R.id.button_get_one);
        btn_get_all =findViewById(R.id.button_get_all);
        btn_post = findViewById(R.id.button_post);
        btn_put = findViewById(R.id.button_put);
        btn_delete = findViewById(R.id.button_delete);

        // Instantiate the RequestQueue.
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Request a string response from the provided URL.
        /* stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        tv_result.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tv_result.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest); */

        btn_get_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://jsonplaceholder.typicode.com/posts/1";
                jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                tv_result.setText(response.toString());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                if (error.networkResponse != null) {
                                    if (error.networkResponse.statusCode == 400) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(new String(error.networkResponse.data,"UTF-8"));
                                            tv_result.setText("That didn't work! " + jsonObject.toString());
                                        } catch (UnsupportedEncodingException | JSONException e) {
                                            e.printStackTrace();
                                            tv_result.setText("That didn't work! " + error.getMessage());
                                        }
                                    } else {
                                        tv_result.setText("That didn't work! " + error.getMessage());
                                    }
                                } else {
                                    tv_result.setText("That didn't work! " + error.getMessage());
                                }
                            }
                        });
                // Add the request to the RequestQueue.
                requestQueue.add(jsonObjectRequest);
            }
        });

        btn_get_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://jsonplaceholder.typicode.com/posts";
                jsonArrayObjectRequest = new JsonArrayRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int k = 0; k < response.length(); k++) {
                                    try {
                                        tv_result.append(response.getJSONObject(k).toString() + "\n");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                tv_result.setText("That didn't work! " + error.getMessage());if (error.networkResponse != null) {
                                    if (error.networkResponse.statusCode == 400) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(new String(error.networkResponse.data,"UTF-8"));
                                            tv_result.setText("That didn't work! " + jsonObject.toString());
                                        } catch (UnsupportedEncodingException | JSONException e) {
                                            e.printStackTrace();
                                            tv_result.setText("That didn't work! " + error.getMessage());
                                        }
                                    } else {
                                        tv_result.setText("That didn't work! " + error.getMessage());
                                    }
                                } else {
                                    tv_result.setText("That didn't work! " + error.getMessage());
                                }
                            }
                        });
                // Add the request to the RequestQueue.
                requestQueue.add(jsonArrayObjectRequest);
            }
        });

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://jsonplaceholder.typicode.com/posts";
                /* Map<String, String> params = new HashMap<String, String>();
                params.put("title", "foo");
                params.put("body", "bar");
                params.put("userId", "1");
                JSONObject jsonObj = new JSONObject(params); */

                JSONObject json_data = new JSONObject();
                try {
                    json_data.put("title", "foo");
                    json_data.put("body", "bar");
                    json_data.put("userId", 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, url, json_data, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                tv_result.setText(response.toString());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                if (error.networkResponse != null) {
                                    if (error.networkResponse.statusCode == 400) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(new String(error.networkResponse.data,"UTF-8"));
                                            tv_result.setText("That didn't work! " + jsonObject.toString());
                                        } catch (UnsupportedEncodingException | JSONException e) {
                                            e.printStackTrace();
                                            tv_result.setText("That didn't work! " + error.getMessage());
                                        }
                                    } else {
                                        tv_result.setText("That didn't work! " + error.getMessage());
                                    }
                                } else {
                                    tv_result.setText("That didn't work! " + error.getMessage());
                                }
                            }
                        });
                // Add the request to the RequestQueue.
                requestQueue.add(jsonObjectRequest);
            }
        });

        btn_put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://jsonplaceholder.typicode.com/posts/1";
                /* Map<String, String> params = new HashMap<String, String>();
                params.put("title", "foo1");
                params.put("body", "bar1");
                params.put("userId", "1");
                JSONObject jsonObj = new JSONObject(params); */

                JSONObject json_data = new JSONObject();
                try {
                    json_data.put("title", "foo1");
                    json_data.put("body", "bar1");
                    json_data.put("userId", 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.PUT, url, json_data, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                tv_result.setText(response.toString());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                if (error.networkResponse != null) {
                                    if (error.networkResponse.statusCode == 400) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(new String(error.networkResponse.data,"UTF-8"));
                                            tv_result.setText("That didn't work! " + jsonObject.toString());
                                        } catch (UnsupportedEncodingException | JSONException e) {
                                            e.printStackTrace();
                                            tv_result.setText("That didn't work! " + error.getMessage());
                                        }
                                    } else {
                                        tv_result.setText("That didn't work! " + error.getMessage());
                                    }
                                } else {
                                    tv_result.setText("That didn't work! " + error.getMessage());
                                }
                            }
                        });
                // Add the request to the RequestQueue.
                requestQueue.add(jsonObjectRequest);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "https://jsonplaceholder.typicode.com/posts/1";

                jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                tv_result.setText(response.toString());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                if (error.networkResponse != null) {
                                    if (error.networkResponse.statusCode == 400) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(new String(error.networkResponse.data,"UTF-8"));
                                            tv_result.setText("That didn't work! " + jsonObject.toString());
                                        } catch (UnsupportedEncodingException | JSONException e) {
                                            e.printStackTrace();
                                            tv_result.setText("That didn't work! " + error.getMessage());
                                        }
                                    } else {
                                        tv_result.setText("That didn't work! " + error.getMessage());
                                    }
                                } else {
                                    tv_result.setText("That didn't work! " + error.getMessage());
                                }
                            }
                        });
                // Add the request to the RequestQueue.
                requestQueue.add(jsonObjectRequest);
            }
        });

        /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // *******
    // Cycle life
    // *******
    @Override
    protected void onStop () {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
        }
    }

}
