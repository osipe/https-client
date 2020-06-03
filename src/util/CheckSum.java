package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class CheckSum {

	public static void main(String[] args) throws Exception {
		checkSum();
	}

	/*public static void checkSum() {
		HTTP_POST(url, authorization, fasle, data);
	}

	public static String HTTP_POST(String url, boolean authorization, String sercet, String data)
			throws UnsupportedEncodingException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		if (authorization) {
			httpPost.setHeader("Authorization", sercet);
		}
		httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
		StringEntity httpEntity = new StringEntity(data, "UTF-8");
		httpPost.setEntity(httpEntity);
		String responseString = "";
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseString = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(responseString);
		return responseString;
	}*/

}
