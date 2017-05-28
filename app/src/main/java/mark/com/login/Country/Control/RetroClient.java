package mark.com.login.Country.Control;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mark on 4/24/2017.
 */

public class RetroClient {
    static boolean created = false;
    static RetroClient client;
    private static String ROOT_URL;
    //Context context;

    private RetroClient() {
        //this.context = context;
        ROOT_URL = "https://restcountries.eu/rest/v2/";
    }

    public static RetroClient getInstanceRetrofit(Context con) {

        if (!created)
            client = new RetroClient();
        created = true;
        return client;
    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public GerritAPI getApiService() {
        return getRetrofitInstance().create(GerritAPI.class);
    }


}
