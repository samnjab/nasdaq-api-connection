import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NasdaqRegionsApiRequest {

    private static final String API_URL = "https://data.nasdaq.com/api/v3/datatables/ZILLOW/REGIONS.json?api_key=j_bYz4xRRduyMgyH5xMY";

    public static void main(String[] args) {
        try {
            // Create URL object with the target API endpoint
            URL url = new URL(API_URL);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // Specify HTTP GET request

            // Check the response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // If response code is 200
                // Read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                // Close the input stream
                in.close();

                // Print the result (JSON response from API)
                System.out.println("Response from Nasdaq REGIONS API: ");
                System.out.println(content.toString());
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }

            // Disconnect the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
