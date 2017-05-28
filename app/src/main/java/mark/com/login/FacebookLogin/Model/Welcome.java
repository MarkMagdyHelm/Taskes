package mark.com.login.FacebookLogin.Model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import mark.com.login.R;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.je);
     /*   String jsonMyObject = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("U");
        }
        User myObject = new Gson().fromJson(jsonMyObject, User.class);
       TextView info = (TextView)findViewById(R.id.info);
        info.setText(
                "User ID: "
                        + myObject.name
                        + "\n" +
                        "Auth Token: "
                        + myObject.facebookID
        );
    }*/
    }
}
