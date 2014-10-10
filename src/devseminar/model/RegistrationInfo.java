package devseminar.model;

/**
 * This model class holds information relevant to the registration information.
 *
 * @author Nathan Shih
 * @date Sep 24, 2014
 */
public class RegistrationInfo {
	
	private String name;
	private String email;
	private String[] courses;
	private String employmentStatus;
	private String hotel;
	private String parking;
	private CostInfo costInfo;
	
	public String getName() {
		return name;
	}
	public void setName(String fullName) {
		this.name = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getCourses() {
		return courses;
	}
	public void setCourses(String[] courses) {
		this.courses = courses;
	}
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
	public String getHotel() {
		return hotel;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public CostInfo getCostInfo() {
		return costInfo;
	}
	public void setCostInfo(CostInfo costInfo) {
		this.costInfo = costInfo;
	}
}
