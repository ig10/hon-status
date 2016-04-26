package devo.honstatus;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public String readAPI(String url) {
      StringBuilder sb = new StringBuilder();
      HttpClient client = new DefaultHttpClient();
      HttpGet get = new HttpGet(url);

      try {
        HttpResponse response = client.execute(get);
        StatusLine sl = response.getStatusLine();
        int statusCode = sl.getStatusCode();

      } catch (Exception e) {
        // Error reading data from source
      }
    }

    private class ReadHoNFeedTask extends AsyncTask <String, Void, String>{

      protected String doInBackground(String type, String player_name) {
        // Hero Statistics
        // Match
        // Match History
        // Player Statistics
        // new HonURL();
        return readAPI(HON_API);
      }

      protected void onPostExecute(String result) {
        try {

        } catch (Exception e) {

        }
      }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnGetData(View view) {
        EditText txtLat = (EditText) findViewById(R.id.txtLat);
        EditText txtLong = (EditText) findViewById(R.id.txtLong);


        new ReadHoNFeedTask().execute();
    }
}
