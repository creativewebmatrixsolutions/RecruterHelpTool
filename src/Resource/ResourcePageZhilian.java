package Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ResourcePageZhilian extends ResourcePage {

	public ResourcePageZhilian(HOST Name) {
		super(Name);
	}

	public String searchPage(int index) {
		//System.out.println("Go into searchPage");
		String TempURL = URL;
		if (index > 1) {
			TempURL = URL + "&pageIndex=" + index;
		}
		String res = "";
		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.NETSCAPE).build();
		HttpGet httpget = new HttpGet(TempURL);
		httpget.addHeader("Accept", "*/*");
		httpget.addHeader("Referer", TempURL);

		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(globalConfig)
				.setDefaultCookieStore(cookieStore).build();
		HttpContext localContext = new BasicHttpContext();
		CloseableHttpResponse response;
		try {
			response = httpclient.execute(httpget, localContext);
			res = super.readData(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Go out searchPage");
		return res;
	}

	@Override
	public void getPageDetails() {
	}

	public void analysz(String htmlPage) {
		//System.out.println("Go into analysz");
		// WriteToFile.writeStrToFile(htmlPage, "res.txt");

		Document doc = Jsoup.parse(htmlPage);
		Element body = doc.body();
		Elements content = doc.getElementsByTag("tbody");
		Elements trs = doc.select("table").select("tr");

		List<String> infotext = new ArrayList<String>();
		for (int i = 1; i < trs.size(); i++) {

			Elements tds = trs.get(i).select("td");
			// for(Element e : tds)
			// {
			//
			// System.out.println("td----------h1>"+e.select("h1").text());
			// System.out.println("td----------h2>"+e.select("h2").select("span").text());
			// System.out.println("td----------<p>"+e.select("p").text());
			// }
			//

			for (int j = 0; j < tds.size(); j++) {
				String text = tds.get(j).text();
				infotext.add(text);
				//System.out.print(text + "\t");
			}

			// 每两行是一个简历
			if (i % 2 != 0) {
				continue;
			} else {
				Candidate tempcd = new Candidate();
				fillCandidate(tempcd, infotext);
				infotext.clear();
				addList(candidateList, tempcd);
				//System.out.println("Go into analysz trs" + i +"A");
				//System.out.print("\n");
				//System.out.println(tempcd);
			}
		}
		//System.out.println("Go into analysz 10");
		Element e1 = doc.getElementById("rd-resumelist-pageNum");
		String indexString = e1.text();
		if (!StringUtils.isBlank(indexString)) {
			currentPageIndex = Integer.parseInt(indexString.split("/")[0]);
			maxPageIndex = Integer.parseInt(indexString.split("/")[1]);
		}

		//System.out.println("Go out analysz");
	}

	private void addList(List<Candidate> candidateList, Candidate tempcd) {
		//boolean b =candidateList.get(0).equals(tempcd);
		
		if (!candidateList.contains(tempcd)) {
			tempcd.setCanStatus(CANStatus.add);
			candidateList.add(0, tempcd);
			System.out.println(tempcd);
		}

	}

	/*
	 * 简历名称 职位名称 学历 性别 年龄 现居住地 更新时间 毕业院校 C#/C++/软件/互联网开发/系统集成/销售 C#研发工程师 本科 男 24
	 * 重庆 16-07-17 重庆理工大学 居住地：重庆 期望月薪：6001-8000元/月 当前状态：我目前处于离职状态，可立即上岗 最近工作
	 * 2015-07 ～ 2016-06重庆信同医疗信息服务有限公司（11个月） -??|?? C#研发工程师??|??6001-8000元/月
	 * 互联网/电子商务??|??-??|??-
	 * 工作描述：参与客户端(C#WinForm)代码编写，代码总规模量百万行以上，系统采用CSLA.NET框架，插件式结构，数据库为Oracle，
	 * 通信框架为WCF。能实现看病到拿药的一切流程，包括医保结算，物流配送等。 最高学历 2011-09 ～
	 * 2015-06????重庆理工大学????计算机软件????本科
	 */
	private void fillCandidate(Candidate tempcd, List<String> infotext) {
		// System.out.println(00 + " " + infotext.get(0));
		// for (int i = 0; i < infotext.size(); i++) {
		// System.out.println(i + " " + infotext.get(i));
		// }

		if (infotext.size() >= 11) {
			tempcd.setResumeName(infotext.get(1));
			tempcd.setWorkPosition(infotext.get(3));
			tempcd.setDegree(infotext.get(4));
			tempcd.setSex(infotext.get(5));
			tempcd.setAge(infotext.get(6));
			tempcd.setLiveIn(infotext.get(7));
			tempcd.setUpdateTime(infotext.get(8));
			tempcd.setSchool(infotext.get(9));
			tempcd.setBriefInfo(infotext.get(10));
		}

	}

	public void process() {
		loadData();
		System.out.println("---------page" + currentPageIndex + "  ---------max " + maxPageIndex);
		analysz(searchPage(1));
		while (currentPageIndex < maxPageIndex) {
//			if (currentPageIndex >= 1) {
//				break;
//			}
			currentPageIndex++;
			System.out.println("---------page" + currentPageIndex + "  ---------max " + maxPageIndex);
			analysz(searchPage(currentPageIndex));
		}
	}

	public static void main(String[] args) {
		ResourcePageZhilian re = new ResourcePageZhilian(HOST.ZHILIAN);
		re.loadData();
	}

}
