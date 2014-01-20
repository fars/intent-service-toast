package com.icantrap.examples.intentservicetoast;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class LessDumbService extends IntentService {
  private static final String TAG = LessDumbService.class.toString();

  public LessDumbService() {
    super(TAG);
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    BusProvider.getInstance().post(new ToastRequest("LessDumbService!", Toast.LENGTH_SHORT));
  }
}
