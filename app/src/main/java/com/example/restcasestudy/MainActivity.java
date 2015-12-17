package com.example.restcasestudy;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.restcasestudy.api.StuffApi;
import com.example.restcasestudy.model.StuffModel;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        OkHttpClient client = new OkHttpClient();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(interceptor);

        String API = "http://192.168.1.120:8080/SqlToRest/webresources/com.example.topsy.mytable";
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StuffApi service = retrofit.create(StuffApi.class);
        Call<StuffModel> call = service.getById(1);
        call.enqueue(new Callback<StuffModel>() {
            @Override
            public void onResponse(Response<StuffModel> response, Retrofit retrofit) {
                int statusCode = response.code();
                Log.e("RETRO", response.toString());
                StuffModel stuffModel = response.body();
                Log.e("RETRO", stuffModel.getName());

                Context context = getApplicationContext();
//                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, stuffModel.getId() + ", " + stuffModel.getName() + " @ " + stuffModel.getLocation(), duration);
                toast.show();

            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed
                Log.e("RETRO", "ERROR!!!" + t.getMessage());
            }
        });


//        Call<List<StuffModel>> call2 = service.getAll();
//        call2.enqueue(new Callback<List<StuffModel>>() {
//            @Override
//            public void onResponse(Response<List<StuffModel>> response, Retrofit retrofit) {
//                int statusCode = response.code();
//                Log.e("RETRO", response.toString());
//                List<StuffModel> stuffModelList = response.body();
//                String toastText=null;
//                for (StuffModel stuffModel : stuffModelList){
//                    Log.e("RETRO", stuffModel.getName());
//                    toastText += stuffModel.getId().toString() + stuffModel.getName() + " @ " + stuffModel.getLocation();
//                }
//
//                Context context = getApplicationContext();
////                CharSequence text = "Hello toast!";
//                int duration = Toast.LENGTH_LONG;
//                Toast toast = Toast.makeText(context, toastText, duration);
//                toast.show();
//
//            }
//
//
//            @Override
//            public void onFailure(Throwable t) {
//                // Log error here since request failed
//                Log.e("RETRO", "ERROR!!!" + t.getMessage());
//            }
//        });

        StuffModel newStuffModel = new StuffModel();
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Log.e("TIMESTAMP", timestamp.toString());
        newStuffModel.setChanged(timestamp.toString());
        newStuffModel.setName("new Name");
        newStuffModel.setLocation("new Location");
        Log.e("timestamp", timestamp.toString());
        Call<StuffModel> call3 = service.post(newStuffModel);
        call3.enqueue(new Callback<StuffModel>() {
            @Override
            public void onResponse(Response<StuffModel> response, Retrofit retrofit) {
                Log.e("RETRORETURN", "SUCCESS");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("RETRORETURN", "FAILURE! " + t.getMessage());


            }
        });


//        call3.enqueue(new Callback<StuffModel>() {});
//            @Override
//            public void onResponse(Response<StuffModel> response, Retrofit retrofit) {
//                int statusCode = response.code();
//                Log.e("RETRO", response.toString());
//                StuffModel stuffModel = response.body();
////                Log.e("RETRO", stuffModel.getName());
//
//                Context context = getApplicationContext();
////                CharSequence text = "Hello toast!";
//                int duration = Toast.LENGTH_LONG;
//                Toast toast = Toast.makeText(context, stuffModel.getId() + ", " + stuffModel.getName() + " @ " + stuffModel.getLocation(), duration);
//                toast.show();
//
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                // Log error here since request failed
//                Log.e("RETRO", "ERROR!!!" + t.getMessage());
//            }
//        });
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
}
