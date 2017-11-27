package selftest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpTester {
	
	private static String YAHOO = "maya.internal.worksap.com:17000/CPNYCJK/td";
	
	private class Proxy {
		
		public static final String host = "172.16.206.84";
		public static final int port = 60088;
		
	}
	
	public static void showSystemProperties() {
		StringBuilder sb = new StringBuilder("System Properties: \n");
		Properties p = System.getProperties();
		for (Object key : p.keySet()) {
			sb.append(key).append(" : ").append(p.get(key)).append("\n");
		}
		System.out.println(sb.toString());
	}
	private URI makeUri() throws URISyntaxException {
		URI uri = new URIBuilder()
				.setScheme("http")
				.setHost(YAHOO)
				.build();
		
		return uri;
	}
	
	public String exec() {
		
		String responseMessage = null;
		
		try {
			System.out.println("exec starts");
			HttpPost hp = new HttpPost(makeUri());
			
			HttpClient hc = getClient(getProxy());
			HttpResponse hr = hc.execute(hp);
			responseMessage = hr.toString();
			System.out.println(responseMessage);
			
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		return responseMessage;
	}

	public String exec(boolean useProxy) {
		
		String responseMessage = null;
		
		try {
			HttpPost hp = new HttpPost(makeUri());
			
			HttpClient hc = getClient(useProxy);
			HttpResponse hr = hc.execute(hp);
			responseMessage = hr.toString();
			System.out.println(responseMessage);
			System.out.println(hr.getStatusLine());
			System.out.println(hr.getEntity().getContentType().getValue());
			
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		return responseMessage;
	}
	
	private HttpClient getClient(boolean useProxy) {
		HttpClient hc = null;
		if (useProxy) {
			hc = HttpClientBuilder.create().useSystemProperties().build();
		}
		else {
			hc = HttpClientBuilder.create().build();
		}
		
		return hc;
	}

	
	private HttpClient getClient(HttpHost proxy) {
		HttpClient hc = null;
		hc = HttpClientBuilder.create().setProxy(proxy).build();
		return hc;
	}
	
	
	private HttpHost getProxy() {
		return new HttpHost(Proxy.host, Proxy.port);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("http proxy: ")
		  .append(System.getProperty("http.proxyHost"))
		  .append("\n");
		return sb.toString();
	}

}
