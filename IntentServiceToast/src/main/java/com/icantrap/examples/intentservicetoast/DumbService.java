package com.icantrap.examples.intentservicetoast;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class DumbService extends IntentService {
  private static final String TAG = DumbService.class.toString();

  public DumbService() {
    super(TAG);
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    toast("DumbService!", Toast.LENGTH_SHORT);
  }

  private void toast(final String text, final int duration) {
    Handler handler = new Handler(Looper.getMainLooper());
    handler.post(new Runnable() {
      @Override
      public void run() {
        Toast.makeText(getApplicationContext(), text, duration).show();
      }
    });
  }
}
