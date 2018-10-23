package com.example.mihai.getmydrivercardapp.async;

import android.os.AsyncTask;
import android.os.Handler;

import com.example.mihai.getmydrivercardapp.async.base.AsyncRunner;

    public class AsyncRunnerImpl implements AsyncRunner {

        @Override
        public void runInBackground(Runnable action) {
            run(action);
        }

        private static void run (final Runnable action) {
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                new AsyncTask<Boolean, Void, Void>() {
                    @Override
                    protected Void doInBackground(Boolean... booleans) {
                        action.run();
                        return null;
                    }
                }.execute(true);
            }, 1000);

        }
    }
}
