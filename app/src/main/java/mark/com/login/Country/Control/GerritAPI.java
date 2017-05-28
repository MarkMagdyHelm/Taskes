package mark.com.login.Country.Control;

import java.util.List;

import mark.com.login.Country.Model.Countries;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mark on 5/2/2017.
 */
public interface GerritAPI {
    @GET("all")
    Call<List<Countries>> getMyJSON(@Query("fields") String s);

}
