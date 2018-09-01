package com.bridgeit.fundoonote.noteservice.jsoup;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.fundoonote.noteservice.model.Note;
import com.bridgeit.fundoonote.noteservice.model.WebScrap;

@Service
@Transactional(propagation=Propagation.NESTED)
public class JsoupWithRegex {

	
	public static List<String> extractUrls(String text) {
		List<String> containedUrls = new ArrayList<String>();
		String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
		Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
		
		text = text.replaceAll("<div>", " ");
	    text = text.replaceAll("<br>", " ");
		Matcher urlMatcher = pattern.matcher(text);

		while (urlMatcher.find()) {
			containedUrls.add(text.substring(urlMatcher.start(0), urlMatcher.end(0)));
		}

		return containedUrls;
	}

	public List<WebScrap> listOfLink(Note note) {
		List<WebScrap> webScraps = new ArrayList<>();

		String noteDescription = note.getNoteDescribtion();
		List<String> extractedUrls = extractUrls(noteDescription);
		try {
			for (String string : extractedUrls) {
				WebScrap webScrap = new WebScrap();

				String urlLink = string;

				Document doc = Jsoup.connect(urlLink).get();
				String title = doc.title();
				System.out.println("title is: " + title);
				webScrap.setLinkTitle(title);

				Element a = doc.select("a").first();
				String url = a.attr("abs:href");
				System.out.println("absulute path is : " + url);
				webScrap.setFullLink(url);

				URL aURL = new URL(urlLink);
				String hostname = aURL.getHost();
				System.out.println("host name :" + hostname);
				webScrap.setLinkHost(hostname);
				
				String urlimage = doc.select("meta[property=og:image]").first().attr("content").trim();
				System.out.println("image url :"+urlimage);
				webScrap.setLinkImage(urlimage);
				webScraps.add(webScrap);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return webScraps;
	}

}
