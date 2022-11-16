package practice;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public final class App {

    private static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        BufferedReader br = null;
        PrintWriter out = null;
        try {
            String url = "https://pokeapi.co/api/v2/pokemon/ditto";
            URL uri = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("User-Agent", USER_AGENT);

            is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String backStr = sb.toString();
            JSONObject jsonObject = JSONObject.parseObject(backStr);

            JSONArray forms = jsonObject.getJSONArray("forms");
            JSONObject name = forms.getJSONObject(0);
            String nameStr = name.getString("name");
            System.out.print("Name: ");
            System.out.println(nameStr);

            int height = jsonObject.getIntValue("height");
            System.out.print("Height: ");
            System.out.println(height);

            int weight = jsonObject.getIntValue("weight");
            System.out.print("Weight: ");
            System.out.println(weight);

            JSONArray abilities = jsonObject.getJSONArray("abilities");
            ArrayList<String> abilitiesList = new ArrayList<>();
            for (int i = 0; i < abilities.size(); i++){
              JSONObject ability = abilities.getJSONObject(i);
              JSONObject abilityObj = ability.getJSONObject("ability");
              String abilityName = abilityObj.getString("name");
              abilitiesList.add(abilityName);           
            }
            System.out.print("Abilities: ");
            System.out.println(abilitiesList);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (br != null) {
                    br.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception ignored) {
                System.out.println(ignored);
            }
        }
    }
}
