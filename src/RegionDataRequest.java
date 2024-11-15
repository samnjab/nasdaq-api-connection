import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RegionDataRequest {

    private static final String BASE_URL = "https://data.nasdaq.com/api/v3/datatables/ZILLOW/DATA";
    private static final String API_KEY = "j_bYz4xRRduyMgyH5xMY";

    private String indicatorId;
    private String regionId;

    public RegionDataRequest(String indicatorId, String regionId) {
        this.indicatorId = indicatorId;
        this.regionId = regionId;
    }

    public void fetchData() {
        try {
            // Construct the full URL with query parameters
            String urlWithParams = String.format("%s?indicator_id=%s&region_id=%s&api_key=%s",
                    BASE_URL,
                    URLEncoder.encode(indicatorId, StandardCharsets.UTF_8),
                    URLEncoder.encode(regionId, StandardCharsets.UTF_8),
                    API_KEY);

            URL url = new URL(urlWithParams);

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
                System.out.println("Response from Nasdaq DATA API with parameters: ");
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

    public static void main(String[] args) {
        RegionDataRequest dataRequest = new RegionDataRequest("ZSFH", "99999"); // Initialize with desired indicator and region IDs
        dataRequest.fetchData(); // Fetch and display the data
    }
}
