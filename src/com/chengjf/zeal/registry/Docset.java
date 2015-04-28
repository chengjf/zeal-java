package com.chengjf.zeal.registry;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Docset {

	private static Log log = LogFactory.getLog(Docset.class);

	private static final String METADATA_FILE_NAME = "meta.json";
	private static final String JSON_NAME = "name";
	private static final String JSON_TITLE = "title";
	private static final String JSON_VERSION = "version";
	private static final String JSON_REVISION = "revision";
	private static final String JSON_EXTRA = "extra";
	private static final String JSON_INDEXFILEPATH = "indexFilePath";

	private String path;
	private String name;
	private String title;
	private String version;
	private String revision;
	private String indexFilePath;

	public Docset(String path) throws Exception {
		this.path = path;

		File dir = new File(this.path);
		if (!(dir.exists() && dir.isDirectory())) {
			if (log.isErrorEnabled()) {
				log.error("Can't create directory : " + path);
			}
			throw new Exception("Can't create directory : " + path);
		}

		loadMetadata(dir);
	}

	private void loadMetadata(File dir) throws Exception {
		File[] files = dir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.getName().equals(METADATA_FILE_NAME);
			}
		});

		if (files.length == 0) {
			if (log.isErrorEnabled()) {
				log.error("Can't find meta.json file in : "
						+ dir.getAbsolutePath());
			}
			throw new Exception("Can't find meta.json file in : "
					+ dir.getAbsolutePath());
		}

		Reader reader = new FileReader(files[0]);
		JsonElement jsonElement = (new JsonParser()).parse(reader);
		JsonObject jsonObject = jsonElement.getAsJsonObject();

		this.name = getUTF8String(jsonObject.get(JSON_NAME).getAsString());
		this.title = getUTF8String(jsonObject.get(JSON_TITLE).getAsString());
		this.version = getUTF8String(jsonObject.get(JSON_VERSION).getAsString());
		this.revision = getUTF8String(jsonObject.get(JSON_REVISION)
				.getAsString());

		if (jsonObject.has(JSON_EXTRA)) {
			JsonObject extra = jsonObject.getAsJsonObject(JSON_EXTRA);
			this.indexFilePath = extra.get(JSON_INDEXFILEPATH).getAsString();
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("         name: ");
		sb.append(this.name);
		sb.append("\n        title: ");
		sb.append(this.title);
		sb.append("\n      version: ");
		sb.append(this.version);
		sb.append("\n     revision: ");
		sb.append(this.revision);
		if (this.indexFilePath != null) {
			sb.append("\nindexFilePath: ");
			sb.append(this.indexFilePath);
		}
		return sb.toString();
	}

	private String getUTF8String(String str) {
		byte[] b = str.getBytes();
		String s = "";
		try {
			s = new String(b, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			if (log.isErrorEnabled()) {
				log.error("meta.json encoding is not utf-8!", e);
			}
		}
		return s;
	}

}
