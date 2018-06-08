package shopping_demo.com.wrapperhandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new WrapperedHandler.Builder().setCallBack(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        }).build();
        handler.sendEmptyMessageDelayed(1, 10000);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
