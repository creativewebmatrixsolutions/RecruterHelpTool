package Resource;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.sun.media.jfxmedia.effects.EqualizerBand;

import cacheutils.DateUtil;

public class Candidate {
	private CANStatus canStatus=CANStatus.other;
	private String name = "";
	private int age = 0;
	private DEGREE degree = DEGREE.bachelor;
	private SEX sex = SEX.MALE;
	private int workYears = 0;
	private String school = "";
	private String liveIn = "";
	private Date updateTime = new Date(0);
	private String workPosition = "";
	private String briefInfo = "";
	private String resume = "";
	private String resumeName = ""; // 智联只显示简历名字 不显示人得名字

	public CANStatus getCanStatus() {
		return canStatus;
	}

	public void setCanStatus(CANStatus canStatus) {
		this.canStatus = canStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = Integer.parseInt(age);
	}

	public void setAge(int age) {
		this.age = age;
	}

	public DEGREE getDegree() {
		return degree;
	}

	public void setDegree(DEGREE degree) {
		this.degree = degree;
	}

	public SEX getSex() {
		return sex;
	}

	public void setSex(SEX sex) {
		this.sex = sex;
	}

	public int getWorkYears() {
		return workYears;
	}

	public void setWorkYears(int workYears) {
		this.workYears = workYears;
	}

	public void setWorkYears(String workYears) {
		this.workYears = Integer.parseInt(workYears);
	}

	public String getSchool() {
		return school;
	}

	public String getLiveIn() {
		return liveIn;
	}

	public void setLiveIn(String liveIn) {
		this.liveIn = liveIn;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getWorkPosition() {
		return workPosition;
	}

	public void setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
	}

	public String getBriefInfo() {
		return briefInfo;
	}

	public void setBriefInfo(String briefInfo) {
		this.briefInfo = briefInfo;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getResumeName() {
		return resumeName;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[canStatus]").append(canStatus);
		sb.append("[name]").append(name);
		sb.append("[age]").append(age);
		sb.append("[degree]").append(degree);
		sb.append("[sex]").append(sex);
		sb.append("[workYears]").append(workYears);
		sb.append("[liveIn]").append(liveIn);
		sb.append("[school]").append(school);
		sb.append("[updateTime]").append(DateUtil.format(updateTime, "yyyy-MM-dd"));
		sb.append("[workPosition]").append(workPosition);
		sb.append("[briefInfo]").append(briefInfo);
		sb.append("[resumeName]").append(resumeName);
		sb.append("[resume]").append(resume);

		return sb.toString();
	}

	public void setDegree(String d) {
		if (d.equals(DEGREE.bachelor.getName())|| d.equalsIgnoreCase(DEGREE.bachelor.toString())) {
			this.degree = DEGREE.bachelor;
		} else if (d.equals(DEGREE.master.getName()) || d.equalsIgnoreCase(DEGREE.master.toString())) {
			this.degree = DEGREE.master;
		} else if (d.equals(DEGREE.doctor.getName()) || d.equalsIgnoreCase(DEGREE.doctor.toString())) {
			this.degree = DEGREE.doctor;
		} else {
			this.degree = DEGREE.other;
		}

	}

	public void setSex(String s) {
		if (s.equals(SEX.MALE.getName()) || s.equalsIgnoreCase(SEX.MALE.toString())) {
			this.sex = SEX.MALE;
		} else {
			this.sex = SEX.FEMALE;
		}
	}

	public void setUpdateTime(String date) {
		String d = "20" + date;
		try {
			this.updateTime = DateUtil.parse(d);
		} catch (ParseException e) {
			System.out.println("Can not convert " + d + " to " + DateUtil.getDatePattern());
			e.printStackTrace();
		}
	}

	public boolean equals(Object o) {
		if (!(o instanceof Candidate))
			return false;
		Candidate other = (Candidate) o;
		return other.resumeName.equals(resumeName) && other.age == age && other.name.equals(name)
				&& other.school.equals(school) && other.sex == sex && other.degree == degree;
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + resumeName.hashCode();
		result = 37 * result + age;
		result = 37 * result + name.hashCode();
		result = 37 * result + school.hashCode();
		result = 37 * result + sex.hashCode();
		result = 37 * result + degree.hashCode();
		return result;
	}
}
