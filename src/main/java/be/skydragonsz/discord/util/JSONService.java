package be.skydragonsz.discord.util;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class JSONService {
    private static Logger logger = LoggerFactory.getLogger(JSONService.class.getSimpleName());

    public static String readAll(Reader rd) {
        try {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();

        } catch (IOException e) {
            logger.error("Failed to get apicalls!", e);
            return null;
        }

    }

    public static int getResponseCode(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            return connection.getResponseCode();
        } catch (ProtocolException e) {
            logger.warn("ProtocolExeption", e);
            return 0;
        } catch (MalformedURLException e) {
            logger.warn("MalformedURLException", e);
            return 0;
        } catch (IOException e) {
            logger.warn("IOException", e);
            return 0;
        }

    }

    public static JSONObject readJsonFromUrl(String url) {
        InputStream is = null;
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.addRequestProperty("User-Agent", "SADBOYS");
            is = urlConnection.getInputStream();
        } catch (IOException e) {
            logger.error("Failed to get apicalls from url!", e);
            return null;
        }
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                logger.error("Failed to get apicalls!", e);
            }
        }
    }


    public static JSONObject sendJsonObjectToUrl(JSONObject obj, String urlString) {
        try {
            URL url = new URL(urlString);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setUseCaches(false);


            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return new JSONObject(response.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    //TODO Rewrite this
    public static void POSTRequest(JSONObject jsonObj, String urlString) {
        try {
            URL obj = new URL(urlString);
            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
            postConnection.setRequestMethod("POST");


            postConnection.setRequestProperty("Content-Type", "application/json");

            for (String keys : jsonObj.keySet()) {
                postConnection.setRequestProperty(keys, jsonObj.get(keys).toString());
            }

            postConnection.setDoOutput(true);
            OutputStream os = postConnection.getOutputStream();
            System.out.println(jsonObj.toString());
            os.write(jsonObj.toString().getBytes());
            os.flush();
            os.close();
            int responseCode = postConnection.getResponseCode();
            System.out.println("POST Response Code :  " + responseCode);
            System.out.println("POST Response Message : " + postConnection.getResponseMessage());
            if (responseCode == HttpURLConnection.HTTP_CREATED || responseCode == 200) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        postConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // print result
                System.out.println(response.toString());
            } else {
                System.out.println("POST NOT WORKED");
            }
        } catch (Exception ex) {

        }
    }
}