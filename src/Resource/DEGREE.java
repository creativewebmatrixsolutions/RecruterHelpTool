package Resource;

public enum DEGREE {
	other("����"),bachelor("����"), master("˶ʿ"), doctor("��ʿ");

	String name;

	DEGREE(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
