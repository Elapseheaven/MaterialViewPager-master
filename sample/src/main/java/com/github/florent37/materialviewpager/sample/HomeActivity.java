package com.github.florent37.materialviewpager.sample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.sample.bean.TranslateResult;
import com.github.florent37.materialviewpager.sample.request.TranslateRequest;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends BaseActivity {
    private Toolbar toolbar;
    private EditText mEtContent;

    // 翻译接口地址
    private static final String TRANSLATE_API_URL = "http://fy.iciba.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mEtContent = (EditText) findViewById(R.id.et_content);
        // GET网络请求
        findViewById(R.id.btn_get_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEtContent.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(HomeActivity.this, "请输入需要查询的中文", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 移除空格、换行、制表符
                content = content.replace(" ", "").replace("\n", "").replace("\t", "");

                // 加入loading对话框
                final ProgressDialog dialog = new ProgressDialog(HomeActivity.this);
                dialog.setMessage("加载中，请稍后...");
                dialog.show();

                // 创建Retrofit对象
                Retrofit retrofit = getRetrofit();
                // 创建网络请求接口的实例
                TranslateRequest request = retrofit.create(TranslateRequest.class);
                // 对发送请求进行封装
                Call<TranslateResult> call = request.getCall(content);
                // 异步发送网络请求
                call.enqueue(new Callback<TranslateResult>() {
                    @Override
                    public void onResponse(Call<TranslateResult> call, Response<TranslateResult> response) {
                        // 请求成功
                        TranslateResult result = response.body();
                        if (result != null && result.getContent() != null && result.getContent().getOut() != null) {
                            mEtContent.setText(result.getContent().getOut());
                            // 光标移动到最后
                            mEtContent.setSelection(mEtContent.getText().length());
                        }

                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<TranslateResult> call, Throwable t) {
                        // 请求失败
                        Toast.makeText(HomeActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();
                    }
                });
            }
        });
    }
        /**
         * 获取Retrofit对象
         *
         * @return
         */

        private Retrofit getRetrofit() {
            return new Retrofit.Builder()
                    .baseUrl(TRANSLATE_API_URL) // 接口地址
                    .addConverterFactory(GsonConverterFactory.create()) // 设置使用Gson解析
                    .build();
        }
}





