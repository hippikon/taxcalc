package digital.places.woot;

import javax.persistence.Transient;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TaxTemplate {
	
	@Transient
	private final ObjectMapper objectMapper = new ObjectMapper();

	private Integer id;
	private String firstName;
	private String lastName;
	private Double wages;
	private Double interest;
	private Double paid;
	private Double refund;
	private Double owed;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Double getWages() {
		return wages;
	}
	public void setWages(Double wages) {
		this.wages = wages;
	}
	public Double getInterest() {
		return interest;
	}
	public void setInterest(Double interest) {
		this.interest = interest;
	}
	public Double getPaid() {
		return paid;
	}
	public void setPaid(Double paid) {
		this.paid = paid;
	}
	public Double getRefund() {
		return refund;
	}
	public void setRefund(Double refund) {
		this.refund = refund;
	}
	public Double getOwed() {
		return owed;
	}
	public void setOwed(Double owed) {
		this.owed = owed;
	}

	@Override
	public String toString()
	{
		String output = "";
		try
		{
			output = objectMapper.writeValueAsString(this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return output;
	}
	
}
