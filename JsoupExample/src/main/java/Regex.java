import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	
	public static List<String> extractUrls(String text)
	{
	    List<String> containedUrls = new ArrayList<String>();
	    String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
	    Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);

	    text = text.replaceAll("<div>", " ");
	    text = text.replaceAll("<br>", " ");
	    Matcher urlMatcher = pattern.matcher(text);
	    while (urlMatcher.find())
	    {
	        containedUrls.add(text.substring(urlMatcher.start(0),
	                urlMatcher.end(0)));
	    }

	    return containedUrls;
	}
	public List<String> list() {
		List<String> linklist=new ArrayList<String>();
		List<String> extractedUrls = extractUrls("https://wordpress.org/plugins/default-featured-image/");

		for (String url : extractedUrls)
		{
		    System.out.println(url);
		    linklist.add(url);
		}
		return linklist;

	}
	
}
