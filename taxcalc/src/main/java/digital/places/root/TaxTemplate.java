package digital.places.root;

import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.ObjectMapper;

@Document(collection = "form1")
public class TaxTemplate {
	
	@Transient
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Id
	private String id;
	private Integer index;
	private String firstName;
	private String lastName;
	private Double wages;
	private Double interest;
	private Double paid;
	private Double refund;
	private Double owed;
	private String taxFormula;

	public String getTaxFormula() {
		return taxFormula;
	}
	public void setTaxFormula(String taxFormula) {
		this.taxFormula = taxFormula;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
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
