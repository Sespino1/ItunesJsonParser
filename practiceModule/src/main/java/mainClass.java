import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class mainClass {
    private static JsonArray itunesResponse = new JsonArray();

    public static void main(String[] args) throws IOException {
        System.out.println("Enter the API you want to use:\n1)itunes\n2)rest countries");
        Scanner scn = new Scanner(System.in);
        String apiSelection = scn.nextLine();

        ApiCaller apiCaller = new ApiCaller();

        switch (apiSelection) {
            case "1":
                System.out.println("Enter the song or artist you'd like to look up:");
                itunesResponse = getJsonItunesArray(apiCaller.makeItunesAPICall(scn.nextLine()));
                System.out.println("Make it pretty? (Y/N)");
                String response = scn.nextLine();

                if ((response.equalsIgnoreCase("Y"))) {
                    makeItunesPretty();
                } else if (response.equalsIgnoreCase("N")) {
                    System.out.println(":(");
                } else {
                    System.out.println("That's not an answer.Good Bye.");
                }
                break;
            case "2":
                System.out.println("Enter the country you'd like to look up:");
                String countriesResponse = apiCaller.makeRESTCountriesAPICall(scn.nextLine());
                System.out.println(countriesResponse);
                formatCountries(countriesResponse);
                break;
            default:
                System.out.println("Not valid api");
                break;
        }


    }

    public static JsonArray getJsonItunesArray(String response) {
        JsonElement jsonElement = new JsonParser().parse(response);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("results");
        jsonObject = jsonArray.get(0).getAsJsonObject();
        String formattedResponse = jsonObject.get("wrapperType").getAsString();
        System.out.println(response);
        System.out.println(formattedResponse);
        return jsonArray;
    }

    public static void makeItunesPretty() {
        System.out.println("Number of Results:" + itunesResponse.size());
        System.out.println(DisplayUtils.DIVIDER);
        JsonObject object = itunesResponse.get(0).getAsJsonObject();

        for(String keyValue :object.keySet()){
            System.out.printf(DisplayUtils.ANSI_BLUE+keyValue);
            System.out.printf(DisplayUtils.ANSI_PURPLE+"%150s",object.get(keyValue).getAsString());
            System.out.println();
        }

    }
    public static void formatCountries(String countriesJSON){
        JsonElement jsonElement = new JsonParser().parse(countriesJSON);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
        System.out.println(jsonArray.get(0));
        System.out.println(jsonObject.get("languages"));
        Set<String> keySet = jsonObject.keySet();

        for(String value: keySet){
            System.out.println("Key:"+value);
            System.out.println("Value: "+jsonObject.get(value));
        }

    }


}
