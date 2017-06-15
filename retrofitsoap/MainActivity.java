package com.smilexie.retrofitsoap;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.smilexie.retrofitsoap.databinding.ActivityMainBinding;
import com.smilexie.retrofitsoap.webservice.ResponseBean;
import com.smilexie.retrofitsoap.webservice.RetrofitGenerator;
import com.smilexie.retrofitsoap.webservice.SoapUtils;
import com.smilexie.retrofitsoap.webservice.WSParams;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Qtyearlin on 2017/3/17.
 */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.rvElements.setLayoutManager(new LinearLayoutManager(this));

    }

    public void getWSTest() {
        binding.progressbar.setVisibility(View.VISIBLE);
        WSParams params = new WSParams();
        params.addProperty("key", "value");

        String soapEv = SoapUtils.createSoapMessage(params, "method_name");
        Call<ResponseBean<JSONObject>> call = RetrofitGenerator.getWeatherInterfaceApi().GetInvestByJkid(soapEv);
        call.enqueue(new Callback<ResponseBean<JSONObject>>() {
            @Override
            public void onResponse(Response<ResponseBean<JSONObject>> response) {
                binding.progressbar.setVisibility(View.GONE);
                String responseEnvelope = response.message();
            }

            @Override
            public void onFailure(Throwable t) {
                binding.progressbar.setVisibility(View.GONE);
            }
        });
    }


}
