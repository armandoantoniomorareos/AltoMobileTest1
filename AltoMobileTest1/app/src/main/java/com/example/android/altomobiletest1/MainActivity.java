package com.example.android.altomobiletest1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button facebookButton;
    private CallbackManager callBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        facebookButton = findViewById(R.id.facebookButton);
        callBack = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callBack,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // logout
                        if(isSessionActive() && facebookButton.getText().toString().compareToIgnoreCase("logout") == 0) {
                            facebookButton.setText(R.string.fb_login_button_text); //change text of the facebook button
                            LoginManager.getInstance().logOut();

                        }else {
                            //Check for email permission
                            Set<String> declinedP = AccessToken.getCurrentAccessToken().getDeclinedPermissions();
                            if(declinedP.contains("email")){
                                Toast.makeText(MainActivity.this, "Email permission is mandatory", Toast.LENGTH_LONG).show();

                            }else {
                                //if everything is ok, then go to ListViewMenu
                                facebookButton.setText(R.string.fb_logout_button_text);
                                showListViewMenu();
                            }
                        }
                    }

                    @Override
                    public void onCancel() {
                        Set<String> declinedP = AccessToken.getCurrentAccessToken().getDeclinedPermissions();
                        if(declinedP.contains("email")){
                            Toast.makeText(MainActivity.this, "Email permission is mandatory", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callBack.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void facebookLogin(View v){
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email"));
    }

    private void showListViewMenu(){
        Intent i = new Intent(this, ListViewMenu.class);
        startActivity(i);
    }

    private boolean isSessionActive(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && !accessToken.isExpired();
    }

}
