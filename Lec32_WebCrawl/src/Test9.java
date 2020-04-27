import org.jsoup.Jsoup;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test9 {

	public static void main(String[] args) throws IOException {
		String url; 
		Response response; 
		Document doc;
		Element element;
		Elements elements;
		
		
		url = "https://movie.naver.com/movie/sdb/rank/rmovie.nhn";
		response = Jsoup.connect(url).execute(); 
		doc = response.parse();
		
		elements = doc.select("#assistant > div:nth-child(1) > ul > li > a ");
	
		for (Element e : elements) {
			// 1 위,2위 ... 없이 뽑아내기
			e.selectFirst("span.blind").remove(); // 1위, 2위... 텍스트 <span> 없애려면 해당 element 삭제 (remove())
			// <a> 안에 <span>과 <a>의 텍스트가 있음 , <span>을  자식에서 빼겠다라는 의미 
			
			System.out.println(e.text().trim());
		}
		 

	}

}

