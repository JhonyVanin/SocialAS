package helper;

import android.graphics.Bitmap;

public class ImageCache {

	private static ImageCache imageCache;

	private int size = 100;
	private int freePos = 0;
	private String[] savedUri;
	private Bitmap[] savedBitmap;

	private ImageCache() {
		savedUri = new String[size];
		savedBitmap = new Bitmap[size];
	}

	public static ImageCache getInstance() {
		if (imageCache == null) {
			imageCache = new ImageCache();
		}
		return imageCache;
	}

	public Bitmap getImage(String uri) {

		for (int i = 0; i < uri.length(); ++i) {
			if (savedUri[i] != null && savedUri[i].equals(uri)) {
				return savedBitmap[i];
			}
		}

		Bitmap bitmap = ImageDownloader.DownloadImage(uri);

		savedUri[freePos] = uri;
		savedBitmap[freePos] = bitmap;

		freePos++;
		if (freePos >= size) {
			freePos = 0;
		}

		return bitmap;
	}
}
