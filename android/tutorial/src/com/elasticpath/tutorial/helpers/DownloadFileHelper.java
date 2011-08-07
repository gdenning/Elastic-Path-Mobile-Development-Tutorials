package com.elasticpath.tutorial.helpers;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import android.util.Log;

public class DownloadFileHelper {
	/**
	 * Download a file from the Internet.
	 * WARNING: This is a fake implementation - it doesn't actually write anything to the file system.
	 * 
	 * @param urlString the url to download
	 */
	public static void downloadFile(final String urlString) {
		try {
			final URL url = new URL(urlString);

			/* Open a connection to that URL. */
			final URLConnection ucon = url.openConnection();

			/*
			 * Define InputStreams to read from the URLConnection.
			 */
			final InputStream is = ucon.getInputStream();
			final BufferedInputStream bis = new BufferedInputStream(is);

			/*
			 * Read bytes to the Buffer until there is nothing more to read(-1).
			 */
			final ByteArrayBuffer baf = new ByteArrayBuffer(50);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}
		} catch (final IOException ex) {
			Log.d("Android Tutorial", "Error: " + ex);
		}
	}
}
