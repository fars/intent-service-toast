package com.icantrap.examples.intentservicetoast;

import android.widget.Toast;

public class ToastRequest {
  private final String message;
  private final int duration;

  public ToastRequest(String message) {
    this(message, Toast.LENGTH_SHORT);
  }

  public ToastRequest(String message, int duration) {
    this.message = message;
    this.duration = duration;
  }

  public String getMessage() {
    return message;
  }

  public int getDuration() {
    return duration;
  }
}
