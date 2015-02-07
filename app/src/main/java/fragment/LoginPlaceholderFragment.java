package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.social.R;

import activity.MainActivity;
import helper.Config;
import model.LoginModel;
import service.LoginService;

public class LoginPlaceholderFragment extends Fragment {
    EditText email;
    EditText password;
    Button login;
    Button registration;

    public LoginPlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        View rootView = inflater.inflate(R.layout.fragment_login, container,
                false);

        email = (EditText) rootView.findViewById(R.id.email);
        password = (EditText) rootView.findViewById(R.id.password);
        login = (Button) rootView.findViewById(R.id.login);
        registration = (Button) rootView.findViewById(R.id.registration);

        login.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                LoginService loginService = new LoginService();
                LoginModel loginModel = loginService.getLoginModel(email
                        .getText().toString(), password.getText().toString());

                // loginModel.setStatus("true");

                if (loginModel.getStatus().equals("true")) {
                    Config.getInstance().setUserId(loginModel.getUserId());
                    Config.getInstance().setUserPassword(
                            password.getText().toString());

                    Intent i = new Intent(v.getContext(), MainActivity.class);
                    startActivity(i);
                } else {
                    Toast toast = Toast.makeText(v.getContext(),
                            "Wrong email or password", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        registration.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO
            }
        });

        return rootView;
    }

}