package helper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageDownloader {

	public static Bitmap DownloadImage(String uri) {
		try {
			InputStream in = OpenHttpConnection(uri);
			Bitmap bitmap = BitmapFactory.decodeStream(in);

			in.close();
			return bitmap;
		} catch (Exception ex) {
			// TODO: write error message to log file
		}

		// TODO: return base image
		return null;
	}

	private static InputStream OpenHttpConnection(String uriString) {
		try {
			URL url = new URL(uriString);
			URLConnection conn = url.openConnection();

			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();

			return httpConn.getInputStream();
		} catch (Exception ex) {
			// TODO: write error message to log file
		}

		return null;
	}

}
