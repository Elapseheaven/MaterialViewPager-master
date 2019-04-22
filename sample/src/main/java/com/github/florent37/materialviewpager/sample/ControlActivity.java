package com.github.florent37.materialviewpager.sample;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.materialviewpager.sample.adapter.ControlViewPagerAdapter;
import com.github.florent37.materialviewpager.sample.adapter.FindingsViewPagerAdapter;

import java.io.UnsupportedEncodingException;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class ControlActivity extends BaseActivity  {
    //备注：接收消息为带换行符的字符串
    private Toolbar toolbar;
    private TabLayout toolbar_tab;
    private ViewPager control_vp;
    final String ON = "1";
    final String OFF = "2";
    BluetoothSPP bluetooth;
    Button connect;
    Button rounie;
    Button qiaoji;
    Button zhiya;
    Button rouqiao;
    Button tuibu;
    Button shoubi;
    ImageButton on, ledon;
    ImageButton off, ledoff;
    private String TAG = "MainActivity Configuration SCREEN";
    private String TAG1 = "Main Bluetooth Send";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controll);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar_tab = (TabLayout) findViewById(R.id.toolbar_tab);
        control_vp = (ViewPager) findViewById(R.id.control_vp);
        ControlViewPagerAdapter vpAdapter = new ControlViewPagerAdapter(getSupportFragmentManager(), this);
        control_vp.setAdapter(vpAdapter);
        toolbar_tab.setupWithViewPager(control_vp);
        control_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                changeTopBgColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        bluetooth = new BluetoothSPP(this);
        connect = findViewById(R.id.connect);
        on =  findViewById(R.id.on);
        off = findViewById(R.id.off);
        rounie=findViewById(R.id.rounie );
        qiaoji=findViewById(R.id.qiaoji  );
        zhiya =  findViewById(R.id.zhiya);
        rouqiao =  findViewById(R.id.rouqiao );
        tuibu = findViewById(R.id.tuibu );
        shoubi = findViewById(R.id.shoubi);
        if (!bluetooth.isBluetoothAvailable()) {   //检查蓝牙是否可用
            Toast.makeText(getApplicationContext(), "蓝牙不可用！", Toast.LENGTH_SHORT).show();
            finish();
        }
        bluetooth.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {  //链接监听器
            public void onDeviceConnected(String name, String address) {
                connect.setText("Connected to " + name);
            }
            public void onDeviceDisconnected() {
                connect.setText("Connection lost");
            }
            public void onDeviceConnectionFailed() {
                connect.setText("Unable to connect");
            }
        });
//

        rounie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetooth.send("1", true);
            }
        });
        qiaoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetooth.send("2", true);
            }
        });
        zhiya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetooth.send("1", true);
            }
        });
        rouqiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetooth.send("2", true);
            }
        });
        tuibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetooth.send("1", true);
            }
        });
        shoubi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetooth.send("2", true);
            }
        });
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetooth.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bluetooth.disconnect();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);   //•意图选择设备活动
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String msg = "Application send a '1' to target via Bluetooth";
                //  bluetoothMsg(msg);
                bluetooth.send(ON, true);
                Log.e(TAG1, "SEND_ON");
                on.setVisibility(View.INVISIBLE); //ON and OFF button
                off.setVisibility(View.VISIBLE); //ON and OFF button
//                ledon.setVisibility(View.VISIBLE);
//                ledoff.setVisibility(View.INVISIBLE);
            }
        });
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String msg = "Application send a '0' to target via Bluetooth";
                // if (bluetoothMsg(msg))
                //     return;
                bluetooth.send(OFF, true);
                Log.e(TAG1, "SEND_OFF");
                off.setVisibility(View.INVISIBLE);
                on.setVisibility(View.VISIBLE);
//                ledoff.setVisibility(View.VISIBLE);
//                ledon.setVisibility(View.INVISIBLE);
            }
        });
//        bluetooth.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {   //数据接收监听器
//            @Override
//            public void onDataReceived(byte[] data, String message) {
//                Log.e(TAG1, String.valueOf(data));
//                //Toast.makeText(getApplicationContext(), String.valueOf(data) , Toast.LENGTH_SHORT).show();
//                // Toast.makeText(getApplicationContext(),message , Toast.LENGTH_SHORT).show();
//                if (message.length()== 10) {
//                    time.setText(message.substring(0, 2)+":"+message.substring(2, 4));
//                    jia_val.setText(message.substring(4, 7));
//                    yi_val.setText(message.substring(7, 10));
//                }
//            }
//        });
    }
    public boolean bluetoothMsg(String msg) {
        if (bluetooth.getServiceState() == BluetoothState.STATE_CONNECTED) {
            Toast.makeText(ControlActivity.this, msg, Toast.LENGTH_LONG).show();
            return false;
        } else {
            Toast.makeText(ControlActivity.this, "还没有蓝牙连接，请先连接设备...", Toast.LENGTH_LONG).show();
            return true;
        }
    }
    public void onStart() {
        super.onStart();
        if (!bluetooth.isBluetoothEnabled()) {
            bluetooth.enable();
        } else {
            if (!bluetooth.isServiceAvailable()) {
                bluetooth.setupService();
                bluetooth.startService(BluetoothState.DEVICE_OTHER);  //用于与任何与蓝牙串口配置文件模块通信的微控制器连接
            }
        }
    }
    public void onDestroy() {
        super.onDestroy();   //调用父类同名方法
        bluetooth.stopService();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bluetooth.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bluetooth.setupService();
            } else {
                Toast.makeText(getApplicationContext()
                        , "蓝牙不可用，请打开蓝牙！"
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    public void onConfigurationChanged(Configuration newConfig) {       //链接状态
        super.onConfigurationChanged(newConfig);
        Toast.makeText(ControlActivity.this, "Configuration state changes", Toast.LENGTH_LONG).show();
        LinearLayout ly = (LinearLayout) findViewById(R.id.activity_controll);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.e(TAG, "ORIENTATION_LANDSCAPE");
            //setContentView(R.layout.main);
//            setContentView(R.drawable.bg3);
            ly.setBackgroundResource(R.drawable.bg3);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.e(TAG, "ORIENTATION_PORTRAIT");
            ly.setBackgroundResource(R.drawable.bg);
        }
    }


//    public static String toUtf8(String str) {
//        String result = null;
//        try {
//            result = new String(str.getBytes("UTF-8"), "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return result;
//    }
}
