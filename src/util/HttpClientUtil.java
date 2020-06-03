package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	public static final String HTTPS = "https";

	private static class DefaultTrustManager implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}

	// end mo update 9/8/2016
	public static boolean checkProtocolURL(String url) {
		if (url.startsWith(HTTPS)) {
			return true;
		}
		return false;
	}

	public static String getReponseHTTPSByGet(String url, String username, String password, String method,
			Map<String, Object> parameters) {
		HttpsURLConnection conn = null;
		try {
			// configure the SSLContext with a TrustManager
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
			SSLContext.setDefault(ctx);
			StringBuilder postData = new StringBuilder();
			for (Map.Entry<String, Object> param : parameters.entrySet()) {
				if (postData.length() != 0)
					postData.append('&');
				postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
				postData.append('=');
				postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
			}
			conn = (HttpsURLConnection) new URL(url + method + "?" + postData.toString()).openConnection();
			String userInfo = username + ":" + password;
			String basicAuth = "Basic " + new String(new Base64().encode(userInfo.getBytes()));
			conn.setRequestProperty("Authorization", basicAuth);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpsURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				// print result
				System.out.println(response.toString());
			} else {
				System.out.println("POST request not worked");
			}
		} catch (Exception e) {
			return e.getMessage();
		} finally {
			try {
				conn.disconnect();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return "";
	}

	private static String getReponseHTTPSByPost(String url, String username, String password, String method,
			Map<String, Object> parameters) throws UnsupportedEncodingException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url + method);
		String userInfo = username + ":" + password;
		String encoding = new String(new Base64().encode(userInfo.getBytes()));
		httpPost.setHeader("Authorization", "Basic " + encoding);
		httpPost.setEntity(createRequestHttpEntity(parameters));
		String responseString = "";
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				System.out.println(
						"response.getStatusLine().getStatusCode() " + response.getStatusLine().getStatusCode());
				responseString = EntityUtils.toString(entity, "UTF-8");
			} else {
				System.out.println("entity null");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseString;
	}

	private static HttpEntity createRequestHttpEntity(Map<String, Object> parameters)
			throws UnsupportedEncodingException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> param : parameters.entrySet()) {
			params.add(new BasicNameValuePair(URLEncoder.encode(param.getKey(), "UTF-8"),
					URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8")));
		}
		params.add(new BasicNameValuePair("tagProperties", ""));
		return new UrlEncodedFormEntity(params, "UTF-8");
	}

	public static void main(String[] args) throws Exception {
		//POST();
		GET();
	}
	private static void GET() throws UnsupportedEncodingException {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60 * 1000).build();
		HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		HttpGet httpGet = new HttpGet("http://localhost:8082/ui-servicemobile-portlet/api/jsonws/danhmucdungchung/get-tep-tin-dinh-kem-van-ban-den?fileEntryId=310930&type=1");
		String userInfo = "admin" + ":" + "123";
		String encoding = new String(new Base64().encode(userInfo.getBytes()));
		httpGet.setHeader("Authorization", "Basic " + encoding);
		String responseString = "";
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseString = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(responseString);
	}
	private static void POST() throws UnsupportedEncodingException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(
				"http://localhost:8082/ui-servicemobile-portlet/api/secure/jsonws/danhmucdungchung/post-test");
		String userInfo = "admin" + ":" + "123";
		String encoding = new String(new Base64().encode(userInfo.getBytes()));
		httpPost.setHeader("Authorization", "Basic " + encoding);
		httpPost.setHeader("enctype", "multipart/form-data");
		String responseString = "";
		try {
			//multipart
			//MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			//multipartEntityBuilder.addTextBody("denNgay", "123321");
			//HttpEntity multipart = multipartEntityBuilder.build();
			
			//raw
			//StringEntity httpEntity = new StringEntity("", "UTF-8");
			
			//form-data
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("ngayDen", "1231312");
			httpPost.setEntity(createRequestHttpEntity(parameters));
			
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				System.out.println(
						"response.getStatusLine().getStatusCode() " + response.getStatusLine().getStatusCode());
				responseString = EntityUtils.toString(entity, "UTF-8");
			} else {
				System.out.println("entity null");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(responseString);
	}
	

	private static String getByHttpHost() throws UnsupportedEncodingException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("/QNAPI_INTERFACES_MOBILE_T/DVC/getChiTietHoSoPublic?soBienNhan=1801816200292");
		httpGet.setHeader("Authorization", "DYzGmsptA33KGOOs/Bg2IHGOf0R1VPT/sOy9MOC9/JzAaT");
		String responseString = "";
		HttpHost httpHost = new HttpHost("203.162.127.212", 8088, "HTTP");
		BasicHttpContext ctx = new BasicHttpContext();
		try {
			HttpResponse response = httpClient.execute(httpHost, httpGet, ctx);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				System.out.println(
						"response.getStatusLine().getStatusCode() " + response.getStatusLine().getStatusCode());
				responseString = EntityUtils.toString(entity, "UTF-8");
			} else {
				System.out.println("entity null");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(responseString);
		return responseString;
	}
}
