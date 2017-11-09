package selftest;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Path;

public class URITester {

	private String base = "/home/nrl/Documents/";
	private String sub = "/home/nrl/Documents/my test/";
	private String uri = "/home/nrl/Documents/my test/test.txt";
	
	private URI baseURI;
	private URI subURI;
	private URI uriURI;
	
	private Path basePath;
	private Path subPath;
	private Path uriPath;
	
	
	public void exec() {
		try {
			setURI();
			testRelative();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setURI() throws URISyntaxException, UnsupportedEncodingException {
		this.baseURI = new URI(null, null, base);
		System.out.println(baseURI);
		this.subURI = new URI(null, null, sub);
		System.out.println(subURI);
		this.uriURI = new URI(null, null, uri);
		System.out.println(uriURI);
	}
	
	private void testRelative() throws UnsupportedEncodingException {
		String baseSubRel = getRelativePath(base, sub);
		String subUriRel = getRelativePath(sub, uri);
		String baseUriRel = getRelativePath(base, uri);
		System.out.println(baseSubRel);
		System.out.println(subUriRel);
		System.out.println(baseUriRel);
	}
	
	private void setPath() {
		
	}
    private String getRelativePath(String parent, String child) {
    	if (!child.startsWith(parent)) {
    		return child;
    	}
    	int startIndex = parent.endsWith("/") ? parent.length() : parent.length() + 1;
    	return child.substring(startIndex);
    }
}
