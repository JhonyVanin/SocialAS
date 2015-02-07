package helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class Requester {
	public static String sendPost(String uri, String params) {

		BufferedReader reader;
		OutputStreamWriter writer;

		try {
			URL url = new URL(uri);
			URLConnection conn = url.openConnection();

			conn.setDoOutput(true);

			writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(params);
			writer.flush();

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
			reader.close();
			writer.close();

			return response.toString();
		} catch (Exception ex) {
			// TODO: write error message to log file
		}

		return "";
	}
}
