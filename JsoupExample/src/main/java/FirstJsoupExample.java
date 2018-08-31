
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element; 
public class FirstJsoupExample{  
    public static void main( String[] args ) throws Exception{ 
    	
    	Regex regex=new Regex();
    	List<String> listOfLink=regex.list();
    	
    	System.out.println("==================================");
    	for(String string:listOfLink)
    	{
    		urlink(string);
    	}         
    }
    public static void urlink(String urlString) throws Exception {
		String urlLink=urlString;
        Document doc = Jsoup.connect(urlLink).get();  
        String title = doc.title();  
        System.out.println("title is: " + title);
        
        Element a=doc.select("a").first();
        String url = a.attr("abs:href"); 
        System.out.println("protocol is : "+url );
        
        URL aURL = new URL(urlLink);
        System.out.println("host name :"+aURL.getHost());
        Element image = doc.select("img").first();
		String urlimage = image.absUrl("src");
		System.out.println("image url :"+urlimage);
		
		String temp = doc.select("meta[property=og:image]").first().attr("content").trim();
		System.out.println("dzfffffffffffffffffff     "+temp);
	}
}  