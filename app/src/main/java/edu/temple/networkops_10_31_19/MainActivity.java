package edu.temple.networkops_10_31_19;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);

        Handler displayHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                // message will be string, so it won't be inside what variable, instead inside obj variable
                webView.loadData((String) msg.obj, "text/html", "UTF-8"); // 1st arg is content it should load, 2nd is mimetype, 3rd is encoding
                return true;
            }
        });

        findViewById(R.id.goButton).setOnClickListener(v -> { // load content into TextView using Lambda function
            webView.loadUrl("https://www.temple.edu");
            webView.setWebViewClient(new WebViewClient());
//            new Thread() {
//                @Override
//                public void run() {
//                    URL url = null;
//                    try {
//                        url = new URL("https://www.temple.edu");
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
//                        StringBuilder builder = new StringBuilder(); // StringBuilder, keep adding on bits of a string
//                        String response;
//                        while ((response = reader.readLine()) != null) {
//                            builder.append(response);
//                        }
//                        // Need to use Handler
//                        Message msg = Message.obtain();
//                        msg.obj = builder.toString(); // gives you string created from StringBuilder object
//                        displayHandler.sendMessage(msg);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }.start();
        });
    }
}
