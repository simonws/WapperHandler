package shopping_demo.com.wrapperhandler;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

public class WrapperedHandler {


    public static class Builder {
        private Looper mLooper;
        private WeakReference<Handler.Callback> mCallBackReference;
        private Handler.Callback mCallback;

        public Builder setLooper(Looper looper) {
            mLooper = looper;
            return this;
        }

        public Builder setCallBack(Handler.Callback back) {
            mCallBackReference = new WeakReference(back);
//            this.mCallback = back;
            return this;
        }

        @SuppressLint("HandlerLeak")
        public Handler build() {
            if (mCallback == null && mCallBackReference == null) {
                throw new IllegalArgumentException("call back can nnnot be null");
            }
            if (mLooper != null) {
                return new Handler(mLooper) {
                    @Override
                    public void handleMessage(Message msg) {
                        if (mCallBackReference.get() != null) {
                            mCallBackReference.get().handleMessage(msg);
                        }
                        if (mCallback != null) {
                            mCallback.handleMessage(msg);
                        }


                    }
                };
            } else {
                return new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (mCallBackReference.get() != null) {
                            mCallBackReference.get().handleMessage(msg);
                        }

                        if (mCallback != null) {
                            mCallback.handleMessage(msg);
                        }
                    }
                };
            }

        }
    }


}
