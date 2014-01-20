package com.icantrap.examples.intentservicetoast;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.squareup.otto.Subscribe;

public class MainActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
        .add(R.id.container, new PlaceholderFragment())
        .commit();
    }

    BusProvider.getInstance().register(this);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Subscribe
  public void onToastRequest(ToastRequest request) {
    Toast.makeText(this, request.getMessage(), request.getDuration()).show();
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public class PlaceholderFragment extends Fragment {
    private Activity activity;

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      activity = getActivity();

      View rootView = inflater.inflate(R.layout.fragment_main, container, false);

      Button button = (Button) rootView.findViewById(R.id.dumb_service_button);
      button.setOnClickListener(new DumbServiceButtonListener(DumbService.class));

      button = (Button) rootView.findViewById(R.id.less_dumb_service_button);
      button.setOnClickListener(new DumbServiceButtonListener(LessDumbService.class));

      return rootView;
    }

    private class DumbServiceButtonListener implements View.OnClickListener {

      private final Intent intent;

      public DumbServiceButtonListener(Class<? extends IntentService> dumbServiceClass) {
        intent = new Intent(activity, dumbServiceClass);
      }

      @Override
      public void onClick(View v) {
        startService(intent);
      }
    }
  }

}
