package algorithms.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Crawler {
	
	public static void main(String []args) {
		
		String url = "https://finance.yahoo.com/screener/predefined/aggressive_small_caps?offset=0&count=202";
		
		
		
		 try {
			Document doc = Jsoup.connect(url).get();
			
			String linkHref = doc.select("#scr-res-table > div > table > tbody > tr > td > a").attr("href");
			System.out.println(linkHref);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		
		//*[@id="scr-res-table"]/div[1]/table/tbody/tr[1]/td[1]/a
	
		
		//*[@id="docs"]/div[1]/h4/a
		//JSOUP
		//document.select("#docs > div:eq(1) > h4 > a").attr("href");
		
		
		////*[@id="action-bar-container"]/div/div[2]/a[2]
		//document.select("#action-bar-container > div > div:eq(1) > a:eq(1)").attr("href"); 
	}

}
