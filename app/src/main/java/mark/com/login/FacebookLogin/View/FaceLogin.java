package mark.com.login.FacebookLogin.View;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import mark.com.login.FacebookLogin.Control.DB.Pref;
import mark.com.login.FacebookLogin.Model.User;
import mark.com.login.FacebookLogin.Model.Welcome;
import mark.com.login.R;


public class FaceLogin extends AppCompatActivity implements  View.OnClickListener, CompoundButton.OnCheckedChangeListener{
    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    Pref pref;
    CheckBox cBoxLoginRemember;
    ProfileTracker mProfileTracker;
    AccessToken accessToken;
    private AccessTokenTracker mAccessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile", "email", "user_friends");
        cBoxLoginRemember = (CheckBox) findViewById(R.id.checkbox_remember);
        cBoxLoginRemember.setOnCheckedChangeListener(this);
        pref = new Pref(FaceLogin.this);
        if (!pref.getAccessToken().isEmpty()) {

        }
        callbackManager = CallbackManager.Factory.create();
        if (!pref.isLoginRemember()) {
            LoginManager.getInstance().logOut();
        }
        if (cBoxLoginRemember.isChecked()) {
            pref.saveLoginRemember(true);
        } else {
            pref.saveLoginRemember(false);
        }
        callbackManager = CallbackManager.Factory.create();
        info = (TextView) findViewById(R.id.info);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                /*
                in case when facebook user logout
                 */
                if (currentAccessToken == null) {
                    pref.setAccessToken("");
                    pref.setUserName("");




                }

            }
        };


        // App code


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                if (Profile.getCurrentProfile() == null) {
                    mProfileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                            mProfileTracker.stopTracking();
                            if (profile2 != null) {
                                pref.setUserName(profile2.getName());

                                pref.setAccessToken(AccessToken.getCurrentAccessToken().getToken());



                            } else {
                                if (isLoggedIn()) {
                                    LoginManager.getInstance().logOut();
                                }
                            }
                        }
                    };
                } else {
                    Profile profile = Profile.getCurrentProfile();
                    pref.setUserName(profile.getName());


                    pref.setAccessToken(AccessToken.getCurrentAccessToken().getToken());
                }


             /*   new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/10213733696842971/taggable_friends",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {

            /* handle the result
                            }
                        }
                ).executeAsync();*/
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            User user;

                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {

                                Log.e("response: ", response + "");
                                try {
                                    user = new User();
                                    user.facebookID = object.getString("id").toString();
                                    user.name = object.getString("name").toString();
                                    user.email = object.getString("email").toString();

                                    user.gender = object.getString("gender").toString();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                //  Toast.makeText(LoginActivity.this,"welcome "+user.name,Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(FaceLogin.this, Welcome.class);

                                startActivity(intent);
                                finish();

                            }

                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });}
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    default:
                        break;

                }
            }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                callbackManager.onActivityResult(requestCode, resultCode, data);
            }


            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    pref.saveLoginRemember(true);
                } else {
                    pref.saveLoginRemember(false);

                }

            }

            public  static boolean isLoggedIn() {
                try {
                    AccessToken accessToken = AccessToken.getCurrentAccessToken();
                    return accessToken != null;
                } catch (Exception e) {
                    return true;
                }
            }
        }
