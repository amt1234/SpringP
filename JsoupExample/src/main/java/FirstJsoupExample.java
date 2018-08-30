
import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;  
public class FirstJsoupExample{  
    public static void main( String[] args ) throws IOException{  
                Document doc = Jsoup.connect("http://www.javatpoint.com").get();  
                String title = doc.title();  
                System.out.println("title is: " + title);
                Elements elements=doc.getElementsByTag("img");
                for(Element element1:elements) {
                	System.out.println(element1);
                	System.out.println("src :"+element1.attr("src"));
                }
                
    }  
}  