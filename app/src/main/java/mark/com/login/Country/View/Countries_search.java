package mark.com.login.Country.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mark.com.login.Country.Model.Countries;
import mark.com.login.Country.Control.callRetrofit;
import mark.com.login.Country.Control.NetworkResponse;
import mark.com.login.R;

public class Countries_search extends AppCompatActivity implements TextWatcher {
    private TextView selection;
    private AutoCompleteTextView edit;
    private static String[] items = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countries_activity_main);
        selection = (TextView) findViewById(R.id.selection);
        edit = (AutoCompleteTextView) findViewById(R.id.edit);
        edit.addTextChangedListener(this);
        callRetrofit retrofitMovies = callRetrofit.getInstance(getApplicationContext());
        retrofitMovies.retrofitCall(new NetworkResponse<List<Countries>>() {
            @Override
            public void onSucess(List<Countries> result) {
                items = new String[result.size()];
                for (int i = 0; i < result.size(); i++)
                    items[i] = result.get(i).getName();

                edit.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_dropdown_item_1line,
                        items));
            }


            @Override
            public void onFailure() {
                Toast.makeText(getApplication().getApplicationContext(), "Not_success", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        selection.setText(edit.getText());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
