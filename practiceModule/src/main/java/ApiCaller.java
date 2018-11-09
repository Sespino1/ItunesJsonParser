import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class ApiCaller {

    private OkHttpClient client = new OkHttpClient();

    public String makeItunesAPICall(String searchQuery) throws IOException {

        try {
            URL link = new URL("https://itunes.apple.com/search?term=" + searchQuery);
            URLConnection linkConnection = link.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(linkConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return "";
    }

    /**
     * Using OkHTTP
     *
     */

    public String makeRESTCountriesAPICall(String country){
        Request request = new Request.Builder()
                .url("https://restcountries.eu/rest/v2/name/"+country)
                .build();
        try {
            Response synchronousCall = client.newCall(request).execute();
            return synchronousCall.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String makeBreweryDBAPICall(){

        return "";
    }
}
