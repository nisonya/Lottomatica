package lotto.matica.scommesse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity3 extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Bundle arguments = getIntent().getExtras();
        String url = arguments.get("url").toString();
        webView = findViewById(R.id.webView);
        //получение ссылки
        webView.loadUrl(url);
        //корректность данных
        webView.getSettings().setJavaScriptEnabled(true);

        //cookies
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        WebSettings webSettings= webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.getLoadWithOverviewMode();
        webSettings.getUseWideViewPort();
        webSettings.getDomStorageEnabled();
        webSettings.getDatabaseEnabled();
        webSettings.setSupportZoom(false);
        webSettings.getAllowFileAccess();
        webSettings.getAllowContentAccess();
        webSettings.getLoadWithOverviewMode();
        webSettings.getUseWideViewPort();

        //loadUrl только в приложение без стороних программ
        WebViewClient webViewClient = new WebViewClient() {
            @SuppressWarnings("deprecation") @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @TargetApi(Build.VERSION_CODES.N) @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        };

        webView.setWebViewClient(webViewClient);

        webView.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    if(keyCode==KeyEvent.KEYCODE_BACK) {
                        if (webView.canGoBack()) {
                            webView.goBack();
                            return true;
                        }else    new MainActivity3().onBackPressed();
                    }
                }
                return false;
            }
        });
    }

    //обработка кнопки назад
    @Override
    public void onBackPressed()
    {
    }

}