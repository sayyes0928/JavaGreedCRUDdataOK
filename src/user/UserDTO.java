package user;

public class UserDTO {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String number;
	private String age;
	private String phone;

	public UserDTO(String name, String number, String age, String phone) {
		this.name = name;
		this.number = number;
		this.age = age;
		this.phone = phone;

	}

	public UserDTO() {
	}

}
