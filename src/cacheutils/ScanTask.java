package cacheutils;

import java.util.Date;

import Resource.Candidate;
import Resource.HOST;
import Resource.ResourcePage;
import Resource.ResourcePageZhilian;

public class ScanTask implements Runnable {
	private HOST host = HOST.NULL;

	public ScanTask(HOST h) {
		host = h;
	}

	public void run() {		
		StringBuilder filevalue = new StringBuilder();
		ResourcePage rs = new ResourcePageZhilian(HOST.ZHILIAN);
		rs.process();
		for (Candidate c : rs.getCandidateList()) {
			filevalue.append(c.toString()).append("\n");
		}
		WriteToFile.writeStrToFile("", host.toString());
		WriteToFile.appendFile(filevalue.toString()+"\n", host.toString());
		WriteToFile.appendFile("LOG END--- " + (new Date()).toString(), host.toString());
	}

}
