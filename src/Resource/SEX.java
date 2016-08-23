package Resource;

public enum SEX {
	MALE("ÄÐ"), FEMALE("Å®");

	String name;

	SEX(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
