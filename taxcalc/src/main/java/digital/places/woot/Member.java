package digital.places.woot;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Member {

	private String name;

	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
