package com.tutors.dev.devhunter.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tutors.dev.devhunter.BuildConfig;


import java.util.Date;
import java.util.Locale;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class ApiManager
{
	public static final String HOST = "http://172.28.34.42:8000/";
	public static final String ENDPOINT = "verification/";

	public static String[][] mAvailableHostAddrList;

	public static String token = "";

	private static Gson gson = new GsonBuilder()
//            .serializeNulls()
			.excludeFieldsWithoutExposeAnnotation()
			.create();

	private static RequestInterceptor requestInterceptor = new RequestInterceptor() {
		public void intercept(RequestFacade request) {
			//token  = InfoManager.getUserSid();
			if (token.length() > 0) {
				request.addHeader("Content-Type", "application/json");
//				request.addHeader("Authorization", "Bearer " + InfoManager.getUserSid());
			}
			request.addHeader("Accept-Language", Locale.getDefault().getLanguage());
		}
	};

    public static RestAdapter restAdapter;

	public static ApiService rebuildAdapter() {
		if (BuildConfig.DEBUG) {
			restAdapter = new RestAdapter.Builder()
					.setEndpoint(ENDPOINT)
					.setLogLevel(RestAdapter.LogLevel.BASIC)
					.setRequestInterceptor(requestInterceptor)
					.setConverter(new GsonConverter(gson))
					.build();
//					.setClient(new OAuthClient())

					//.setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)

		} else {
			restAdapter = new RestAdapter.Builder()
//					.setClient(new OAuthClient())
					.setEndpoint(ENDPOINT)
					.setLogLevel(RestAdapter.LogLevel.NONE)
					.setRequestInterceptor(requestInterceptor)
					.setConverter(new GsonConverter(gson))
					.build();
		}
		return restAdapter.create(ApiService.class);
	}
}