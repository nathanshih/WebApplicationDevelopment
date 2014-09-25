/**
 * 
 */
package hw4.service;

import hw4.constants.COSTS;
import hw4.model.CostInfo;
import hw4.model.RegistrationInfo;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *
 * @author Nathan Shih
 * @date Sep 24, 2014
 */
public class RegistrationServiceImpl implements RegistrationService {

	private RegistrationInfo registrationInfo;
	
	public RegistrationServiceImpl() {
		registrationInfo = new RegistrationInfo();
	}
	
	@Override
	public RegistrationInfo getRegistrationInfo() {
		return registrationInfo;
	}

	@Override
	public void setRegistrationInfo(String name, 
									String email,
									String[] courses, 
									String employmentStatus, 
									String hotel,
									String parking) {
		
		registrationInfo.setName(name);
		registrationInfo.setEmail(email);
		registrationInfo.setCourses(courses);
		registrationInfo.setEmploymentStatus(employmentStatus);
		registrationInfo.setHotel(hotel);
		registrationInfo.setParking(parking);		   	
		registrationInfo.setCostInfo(calculateCostInfo());
	}
	
	/**
	 * Calculates the total cost based upon employment status, number of courses chosen, and selected extras (i.e., hotel, parking).
	 *
	 * @return CostInfo
	 */
	private CostInfo calculateCostInfo() {
		CostInfo costInfo = new CostInfo();
		
		String employmentStatus = registrationInfo.getEmploymentStatus();
		if (employmentStatus.equals("JHU Employee")) {
    		costInfo.setEmployeeStatusCost(COSTS.EMPLOYEE);
    	} else if (employmentStatus.equals("JHU Student")) {
    		costInfo.setEmployeeStatusCost(COSTS.STUDENT);
    	} else if (employmentStatus.equals("Speaker")) {
    		costInfo.setEmployeeStatusCost(COSTS.SPEAKER);
    	} else if (employmentStatus.equals("Other")) {
    		costInfo.setEmployeeStatusCost(COSTS.OTHER);
    	}
    	
    	// calculate total cost
    	double total = 0.0;
    	
    	String[] courses = registrationInfo.getCourses();
    	// total up cost of courses given employment status
    	total = costInfo.getEmployeeStatusCost() * courses.length;
    	
    	// if selected hotel, add to total
    	if (!StringUtils.isEmpty(registrationInfo.getHotel())) {
    		total = total + COSTS.HOTEL;
    	}
    	
    	// if selected parking, add to total
    	if (!StringUtils.isEmpty(registrationInfo.getParking())) {
    		total = total + COSTS.PARKING;
    	}
		
    	costInfo.setTotal(total);
    	
		return costInfo;
	}
}
