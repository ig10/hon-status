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
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public String readAPI(String url) {
      String result;
      HttpClient client = new DefaultHttpClient();
      HttpGet get = new HttpGet(url);

      try {
        HttpResponse response = client.execute(get);
        StatusLine sl = response.getStatusLine();
        int statusCode = sl.getStatusCode();

        if (statusCode == 200) {
          result = processResponse(HttpResponse response);
        } else {
          Log.d("JSON", "Error - Status Code: [" + statusCode.toString() + "]");
        }

      } catch (Exception e) {
        Log.d("APP", "Error: " + e.getLocalizedMessage());
      }

      return result;
    }

    public String processResponse(HttpResponse response) {
      StringBuilder stringBuilder = new StringBuilder();
      HttpEntity entity = response.getEntity();
      InputStream inputStream = entity.getContent();
      BufferedReader reader = new BufferedReader(
              new InputStreamReader(inputStream));
      String line;

      while ((line = reader.readLine()) != null) {
          stringBuilder.append(line);
      }
      inputStream.close();

      return stringBuilder.toString();
    }

    private class ReadHoNFeedTask extends AsyncTask <String, Void, String>{

      protected String doInBackground(String statsType, String gameType ,String player) {
        HonURL builder = new HonURL(statsType, gameType, player);
        return readAPI(builder.requestUrl());
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
