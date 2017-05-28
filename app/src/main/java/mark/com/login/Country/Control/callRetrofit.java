package mark.com.login.Country.Control;

import android.content.Context;

import java.util.List;

import mark.com.login.Country.Model.Countries;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nayir on 5/4/2017.
 */

public class callRetrofit {
    static String s = "name";
    static boolean callCreatedReviews = false;
    static callRetrofit RetrofitReviews;
    Context con;
    List<Countries> count;

    private callRetrofit(Context context) {
        con = context;
    }

    public static callRetrofit getInstance(Context conn) {
        if (!callCreatedReviews)
            RetrofitReviews = new callRetrofit(conn);
        callCreatedReviews = true;
        return RetrofitReviews;
    }

    public void retrofitCall(final NetworkResponse<List<Countries>> reviewsResponse) {
        RetroClient retro = RetroClient.getInstanceRetrofit(con);
        GerritAPI api = retro.getApiService();
        Call<List<Countries>> call = api.getMyJSON(s);
        call.enqueue(new Callback<List<Countries>>() {
            @Override
            public void onResponse(Call<List<Countries>> call, Response<List<Countries>> response) {
                if (response.isSuccessful()) {
                    count = response.body();
                    reviewsResponse.onSucess(count);

                }
            }

            @Override
            public void onFailure(Call<List<Countries>> call, Throwable t) {

            }


        });
    }
}
