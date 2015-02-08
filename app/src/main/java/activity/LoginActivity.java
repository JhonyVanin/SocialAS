package activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.social.R;

import java.net.URISyntaxException;

import fragment.LoginPlaceholderFragment;
import helper.SocketIO;

public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginPlaceholderFragment())
                    .commit();
        }
    }
}