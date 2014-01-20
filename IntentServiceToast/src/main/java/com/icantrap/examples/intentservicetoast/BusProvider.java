package com.icantrap.examples.intentservicetoast;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;

public final class BusProvider {
  private static final Bus _instance = new MainThreadBus();

  public static Bus getInstance() {
    return _instance;
  }

  private BusProvider() {
  }

  private static class MainThreadBus extends Bus {
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void post(final Object event) {
      // if I'm already on the main thread, I can just post
      if (Looper.myLooper() == Looper.getMainLooper()) {
        super.post(event);
      }
      else // otherwise, wrap the post in mainThreadHandler
      {
        mainThreadHandler.post(new Runnable() {
          @Override
          public void run() {
            post(event);
          }
        });
      }
    }
  }
}