package Resource;

public enum DEGREE {
	other("其他"),bachelor("本科"), master("硕士"), doctor("博士");

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
