package com.davidcheah.demo.service;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Service;

@Service
public class LocaleService {

	private Locale locale;

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(String lang) {
		switch (lang) {
		case "en":
			this.locale = getEnglishLocale();
			break;
		case "fr":
			this.locale = getFrenchLocale();
			break;
		default:
			break;
		}
	}

	public LocaleService() {
		super();
		this.locale = getEnglishLocale();
	}

	public String getTranslatedString(String field) {
		ResourceBundle resource = ResourceBundle.getBundle("message", locale);
		return resource.getString(field);
	}

	public Locale getEnglishLocale() {
		String lang = "en";
		String country = "US";

		return new Locale(lang, country);
	}

	public Locale getFrenchLocale() {
		String lang = "fr";
		String country = "FR";

		return new Locale(lang, country);
	}
}
