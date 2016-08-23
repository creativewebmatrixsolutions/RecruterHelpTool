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

			// ÿ������һ������
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
	 * �������� ְλ���� ѧ�� �Ա� ���� �־�ס�� ����ʱ�� ��ҵԺУ C#/C++/���/����������/ϵͳ����/���� C#�з�����ʦ ���� �� 24
	 * ���� 16-07-17 ��������ѧ ��ס�أ����� ������н��6001-8000Ԫ/�� ��ǰ״̬����Ŀǰ������ְ״̬���������ϸ� �������
	 * 2015-07 �� 2016-06������ͬҽ����Ϣ�������޹�˾��11���£� -??|?? C#�з�����ʦ??|??6001-8000Ԫ/��
	 * ������/��������??|??-??|??-
	 * ��������������ͻ���(C#WinForm)�����д�������ܹ�ģ�����������ϣ�ϵͳ����CSLA.NET��ܣ����ʽ�ṹ�����ݿ�ΪOracle��
	 * ͨ�ſ��ΪWCF����ʵ�ֿ�������ҩ��һ�����̣�����ҽ�����㣬�������͵ȡ� ���ѧ�� 2011-09 ��
	 * 2015-06????��������ѧ????��������????����
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
