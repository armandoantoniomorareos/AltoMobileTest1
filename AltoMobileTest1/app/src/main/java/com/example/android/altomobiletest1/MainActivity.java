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

        if(isSessionActive()){
            checkEmailPermission();
        }

        LoginManager.getInstance().registerCallback(callBack,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        //Check for email permission
                        checkEmailPermission();
                    }

                    @Override
                    public void onCancel() {
                        checkEmailPermission();
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

    /**
     * @param v Method to open facebook login
     */
    public void facebookLogin(View v){
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email"));
    }

    /**
     * Method to open ListViewMenu Activity once the user login with a Facebook account.
     */
    private void openListViewMenu(){
        Intent i = new Intent(this, ListViewMenu.class);
        startActivity(i);
    }

    /**
     * Method to ensure user provided email permission
     * @return boolean value to indicate if the user grant email permission
     */

    private boolean checkEmailPermission(){
        boolean hasEmailPermission = false;
        Set<String> declinedP = AccessToken.getCurrentAccessToken().getDeclinedPermissions();

        if(declinedP.contains("email")){
            Toast.makeText(MainActivity.this, "Email permission is mandatory", Toast.LENGTH_LONG).show();
            hasEmailPermission = false;
        }else {
            openListViewMenu();
            finish();
           hasEmailPermission = true;
        }
        return hasEmailPermission;
    }

    /**
     * Method to check if there is an active facebook session
     * @return boolean value to indicate if exist an active session
     */
    private boolean isSessionActive(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && !accessToken.isExpired();
    }

}
