package devseminar.model;

/**
 * This model class holds information relevant to registration cost information.
 *
 * @author Nathan Shih
 * @date Sep 24, 2014
 */
public class CostInfo {
	
	// constants
	public static final double EMPLOYEE = 850.00;
	public static final double STUDENT = 1000.00;
	public static final double SPEAKER = 0.00;
	public static final double OTHER = 1350.00;
	public static final double HOTEL = 185.00;
	public static final double PARKING = 10.00;
	
	private double employeeStatusCost;
	private double total;
	@SuppressWarnings("unused")
	private double hotelCost;
	@SuppressWarnings("unused")
	private double parkingCost;
	
	public double getEmployeeStatusCost() {
		return employeeStatusCost;
	}
	public void setEmployeeStatusCost(double employeeStatusCost) {
		this.employeeStatusCost = employeeStatusCost;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getHotelCost() {
		return HOTEL;
	}
	public double getParkingCost() {
		return PARKING;
	}
}
