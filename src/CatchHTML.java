
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import Resource.HOST;
import Resource.ResourcePage;
import Resource.ResourcePageZhilian;
import cacheutils.CookieTool;
import cacheutils.InputStreamUtils;
import cacheutils.ScanTask;

public class CatchHTML {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// Map<String, String> cookies = null;
		//
		// Connection aConnection =
		// Jsoup.connect("http://rdsearch.zhaopin.com/Home/SearchByCustom?source=rd");
		// Response res =aConnection.execute();
		// cookies = res.cookies();
		// System.out.println(cookies);

		// Document doc =
		// Jsoup.connect("http://rdsearch.zhaopin.com/Home/SearchByCustom?source=rd").get();

		// Element link = doc.select("a").first();
		// String relHref = link.attr("href"); // == "/"
		// String absHref = link.attr("abs:href"); //
		// "http://www.open-open.com/"
		//
		//
		// System.out.println(doc);
		// System.out.println(relHref);
		// System.out.println(absHref);
		// String
		// reseachZhiLianZhaoPin="http://rdsearch.zhaopin.com/Home/ResultForCustom?SF_1_1_1=or-java%20C%20C%2B%2B&SF_1_1_5=7%2C16&SF_1_1_18=801%3B551&SF_1_1_8=24%2C99&SF_1_1_4=2%2C99&SF_1_1_27=0&orderBy=DATE_MODIFIED,1&exclude=1";
		//
		//
		// System.out.println(System.getProperty("user.dir"));// user.dir指定了当前的路
		// CookieTool ct = new CookieTool("cookies.txt");
		// ct.generateCookies();
		// StringBuilder c2 = new StringBuilder();
		//
		// CookieStore aCookieStore = ct.getCookieStore();
		// List<Cookie> cookies = aCookieStore.getCookies();
		// //aCookieStore.
		// if (cookies.isEmpty()) {
		// System.out.println("None");
		// } else {
		// for (int i = 0; i < cookies.size(); i++) {
		// System.out.println("- " + cookies.get(i).toString());
		// String temp = cookies.get(i).getName() + "=" +
		// cookies.get(i).getValue();
		// c2.append(temp);
		// if (i!=cookies.size()-1)
		// {
		// c2.append("; ");
		// }
		// }
		// }
		// //System.out.println(c2);
		// // URI uri = new URIBuilder()
		// // .setScheme("http")
		// // .setHost("rdsearch.zhaopin.com")
		// // .setPath("/search")
		// // .setParameter("q", "httpclient")
		// // .setParameter("btnG", "Google Search")
		// // .setParameter("aq", "f")
		// // .setParameter("oq", "")
		// // .build();
		//
		// RequestConfig globalConfig = RequestConfig.custom()
		// .setCookieSpec(CookieSpecs.NETSCAPE)
		// .build();
		//
		// //HttpGet httpget = new
		// HttpGet("http://rdsearch.zhaopin.com/Home/SearchByCustom?source=rd");
		// HttpGet httpget = new HttpGet(reseachZhiLianZhaoPin);
		// httpget.addHeader("Accept", "*/*");
		// httpget.addHeader("Referer", reseachZhiLianZhaoPin);
		// String c =
		// "pageReferrInSession=http%3A//rd2.zhaopin.com/s/homepage.asp;
		// SearchHead_Erd=rd;
		// Hm_lpvt_38ba284938d5eddca645bb5e02a02006=1468650084;
		// Hm_lvt_38ba284938d5eddca645bb5e02a02006=1468592710,1468627314,1468650084;
		// JsNewlogin=668230413; JsOrglogin=2004691241; RDpUserInfo=;
		// RDsUserInfo=266120664E61526645615C66416157664361506642615766486128663D61596641615466426152661061006609611466486131663D615966630399F66719121C3303BF042BE167EA3EF08CF648612666376159664661506641615D6647615F6631612A664E612A35492FE4045C066D0123F708378B325F66276129664E615366486121663E61596609610C6612610A6614611C661A610A664061576633610C6606610566046101661A614A6610610B661E615F66206130664E615566486125662761596644614966426152665361556647615E664161536648612066376159664661506641615D6647615F6637612A664E612A35492FE4045C066D0123F708378B325F663F6129664E6154664361546642615F66306120664E6153664561516648613166276159664261546640615F66306125664E61276630615466436154664661506641615D66476151664861206632615966306127664361546643615166476156664A61506646615F66376127664E615466486137663A61596642615F663A6134664E615666476156665D61556640615D66486125663F61596643615F664;
		// __utma=269921210.1852169529.1468592713.1468638035.1468650087.4;
		// __utmc=269921210; __utmv=269921210.|2=Member=668230413=1;
		// __utmz=269921210.1468650087.4.3.utmcsr=other|utmccn=121113803|utmcmd=cnt;
		// _jzqa=1.2180899038008166400.1468592710.1468638032.1468650084.4;
		// _jzqc=1; _jzqckmp=1;
		// _jzqy=1.1468592710.1468650084.3.jzqsr=baidu|jzqct=zhilian.jzqsr=baidu|jzqct=%E6%99%BA%E8%81%94%E6%8B%9B%E8%81%98;
		// adfbid=0; adfbid2=0; adfcid=pzzhubiaoti; adfcid2=pzzhubiaoti;
		// cgmark=2;
		// dywea=95841923.3365825371337679000.1468592710.1468638032.1468650084.4;
		// dywec=95841923;
		// dywez=95841923.1468650084.4.3.dywecsr=other|dyweccn=121113803|dywecmd=cnt|dywectr=%E6%99%BA%E8%81%94%E6%8B%9B%E8%81%98;
		// isNewUser=1;
		// lastchannelurl=http%3A//rd2.zhaopin.com/portal/myrd/regnew.asp%3Fza%3D2;
		// pcc=r=289117273&t=0; urlfrom=121113803; urlfrom2=121113803;
		// utype=668230413; xychkcontr=54294%2c1";
		// //httpget.addHeader("Cookie", c2.toString());
		// // HttpClientBuilder hcb =
		// // HttpClients.custom().setDefaultCookieStore(aCookieStore);
		//
		// CloseableHttpClient httpclient =
		// HttpClients.custom().setDefaultRequestConfig(globalConfig).setDefaultCookieStore(aCookieStore).build();
		// HttpContext localContext = new BasicHttpContext();
		// CloseableHttpResponse response =
		// httpclient.execute(httpget,localContext);
		//
		// for (Header s : response.getAllHeaders()) {
		// System.out.println("Headers " + s);
		// }
		//
		// try {
		// HttpEntity entity = response.getEntity();
		// if (entity != null) {
		// InputStream instream = entity.getContent();
		// try {
		// String res = InputStreamUtils.InputStreamTOString(instream);
		// System.out.println(res);
		//
		// System.out.println(response.getStatusLine());
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// instream.close();
		// }
		// }
		// } finally {
		// response.close();
		// }

		// ResourcePage rs =new ResourcePageZhilian(HOST.ZHILIAN);
		// rs.process();
		// System.out.println();
		System.out.println(System.getProperty("user.dir"));
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
		service.scheduleAtFixedRate(new ScanTask(HOST.ZHILIAN), 3, 600, TimeUnit.SECONDS);
	}

}
