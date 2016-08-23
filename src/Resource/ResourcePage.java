package Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;

import cacheutils.CookieTool;
import cacheutils.InputStreamUtils;
import cacheutils.ReadFromFile;

public abstract class ResourcePage {
	protected String URL = "";
	protected HOST Name = HOST.NULL;
	protected String cookiePath = "";
	protected String searchResult = "";
	protected CookieStore cookieStore = null;
	protected int currentPageIndex = 1;
	protected int maxPageIndex = 1;

	protected List<Candidate> candidateList = new LinkedList<Candidate>();

	public List<Candidate> getCandidateList() {
		return candidateList;
	}

	public void setCandidateList(List<Candidate> candidateList) {
		this.candidateList = candidateList;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public HOST getName() {
		return Name;
	}

	public void setName(HOST name) {
		Name = name;
	}

	public String getCookiePath() {
		return cookiePath;
	}

	public void setCookiePath(String cookiePath) {
		this.cookiePath = cookiePath;
	}

	public String getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(String searchResult) {
		this.searchResult = searchResult;
	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}

	public ResourcePage(HOST Name) {
		this.Name = Name;
		generateURL(Name);
		generateCookieStore();
	}

	private void generateURL(HOST Name) {
		switch (Name) {
		case ZHILIAN:
			//URL = "http://rdsearch.zhaopin.com/Home/ResultForCustom?SF_1_1_1=or-java%20C%20C%2B%2B&SF_1_1_5=7%2C16&SF_1_1_18=801%3B551&SF_1_1_8=24%2C99&SF_1_1_4=2%2C99&SF_1_1_27=0&orderBy=DATE_MODIFIED,1&exclude=1";
			URL="http://rdsearch.zhaopin.com/Home/ResultForCustom?SF_1_1_1=or-java%20C%20C%2B%2B&SF_1_1_5=7%2C16&SF_1_1_18=801%3B551&SF_1_1_8=24%2C99&SF_1_1_4=2%2C99&SF_1_1_7=1%2C9&SF_1_1_27=0&orderBy=DATE_MODIFIED,1&exclude";
			break;
		case LIEPIN:
			URL = "http://liepin";
			break;
		case RENCAIREXIAN:
			URL = "http://reixian";
			break;
		default:
			URL = "";
			break;
		}
	}

	private void generateCookieStore() {
		CookieTool ct = new CookieTool("cookies.txt");
		cookieStore = ct.generateCookies();
	}

	protected String readData(CloseableHttpResponse response) throws UnsupportedOperationException, IOException {
		String res = "";
		// for (Header s : response.getAllHeaders()) {
		// System.out.println("Headers " + s);
		// }

		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream;
			instream = entity.getContent();
			res = InputStreamUtils.InputStreamTOString(instream);
		}
		return res;
	}

	protected void loadData() {
		candidateList.clear();
		String path = System.getProperty("user.dir") + "\\" + getName();
		List<String> aDataList = ReadFromFile.readFileByLines(path);
		for (String aDataLine : aDataList) {
			// System.out.println(aDataLine);
			if (StringUtils.isBlank(aDataLine) || aDataLine.startsWith("LOG")) {
				continue;
			}

			String[] d = aDataLine.split("\\[.+?\\]");
			Candidate tempCandidate = new Candidate();
//			for (int i = 0; i < d.length; i++) {
//				System.out.println(i + " " + d[i]);
//			}
			if (d.length >= 13) {
				tempCandidate.setCanStatus(CANStatus.exist);
				tempCandidate.setName(d[2]);
				tempCandidate.setAge(d[3]);
				tempCandidate.setDegree(d[4]);
				tempCandidate.setSex(d[5]);
				tempCandidate.setWorkYears(d[6]);
				tempCandidate.setLiveIn(d[7]);
				tempCandidate.setSchool(d[8]);
				tempCandidate.setUpdateTime(d[9]);
				tempCandidate.setWorkPosition(d[10]);
				tempCandidate.setBriefInfo(d[11]);
				tempCandidate.setResumeName(d[12]);
			}
			candidateList.add(tempCandidate);
		}
		//System.out.println("loadData over");
	}

	public abstract void getPageDetails();

	public abstract void process();

	public abstract void analysz(String htmlPage);
}