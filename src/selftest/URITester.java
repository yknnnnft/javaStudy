package selftest;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class URITester {

	private String base = "/home/nrl/Documents/";
	private String sub = "def.txt";
	private String uri = "/home/nrl/Documents/my test/test.txt";
	
	private URI baseURI;
	private URI subURI;
	private URI uriURI;
	
	private Path basePath;
	private Path subPath;
	private Path uriPath;
	
	
	public void exec() {
		Path pBase = Paths.get(base);
		Path pSub = Paths.get(sub);
		System.out.println(pBase.resolve(pSub));
		System.out.println(pBase.resolve(pSub.toString()));
		System.out.println(pBase.resolve(pSub).toUri());
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

    private Path getRelative(Path par, Path child) {
    	Path rel = par.relativize(child);
    	return rel;
    }
}
