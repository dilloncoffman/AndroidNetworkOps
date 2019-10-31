package edu.temple.networkops_10_31_19;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView htmlDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        htmlDisplay = findViewById(R.id.htmlDisplay);

        Handler displayHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                // message will be string, so it won't be inside what variable, instead inside obj variable
                htmlDisplay.setText((String) msg.obj);
                return true;
            }
        });

        findViewById(R.id.goButton).setOnClickListener(v -> { // load content into TextView using Lambda function
            new Thread() {
                @Override
                public void run() {
                    URL url = null;
                    try {
                        url = new URL("https://www.temple.edu");
                        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                        StringBuilder builder = new StringBuilder(); // StringBuilder, keep adding on bits of a string
                        String response;
                        while ((response = reader.readLine()) != null) {
                            builder.append(response);
                        }
                        // Need to use Handler
                        Message msg = Message.obtain();
                        msg.obj = builder.toString(); // gives you string created from StringBuilder object
                        displayHandler.sendMessage(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        });
    }
}
