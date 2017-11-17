package com.semosu.semosu_app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.setInitialScale(1);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultTextEncodingName("EUC-KR");

        webSettings.setGeolocationEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setSupportMultipleWindows(false);
        webSettings.setDomStorageEnabled(true);

        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCacheMaxSize(1024*1024*8);
        webSettings.setAppCachePath("/data/data/" + getApplicationContext().getPackageName());

        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadsImagesAutomatically(true);

        mWebView.getSettings().setBuiltInZoomControls(true); // <!-- 확대축소 버튼 없애기 -->
        mWebView.getSettings().setSupportZoom(true);        // <!-- 확대축소 버튼 없애기 -->
        mWebView.getSettings().setDisplayZoomControls(false); // <!-- 확대축소 버튼 없애기 -->


        mWebView.loadUrl("http://semosu.com");
    }


    @Override
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event){
        if((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack() ){
            mWebView.goBack();
            return true;
        }

        //백할 페이가 없다면
        if ((keyCode == KeyEvent.KEYCODE_BACK) && (mWebView.canGoBack() == false)){
           // Toast.makeText(this, "버튼 클릭 이벤트", Toast.LENGTH_SHORT).show();

            //다이아로그박스 출력
            new AlertDialog.Builder(this)
                    .setTitle("프로그램 종료")
                    .setMessage("프로그램을 종료하시겠습니까?")
                    .setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    })
                    .setNegativeButton("아니오",  null).show();
        }

        return super.onKeyDown(keyCode, event);    }



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
