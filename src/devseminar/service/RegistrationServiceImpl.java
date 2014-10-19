package devseminar.service;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import devseminar.model.CostInfo;
import devseminar.model.RegistrationInfo;

/**
 * The implementation of the business logic.
 *
 * @author Nathan Shih
 * @date Sep 24, 2014
 */
public class RegistrationServiceImpl implements RegistrationService, Serializable {

	private static final long serialVersionUID = 1L;
	
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
									List<String> courses, 
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
	
	@Override
	public CostInfo getCostInfo() {
		return registrationInfo.getCostInfo();
	}
	

	@Override
	public void removeCourse(String course) {
		
		// remove the course
		List<String> courses = new LinkedList<String>();
		courses.addAll(registrationInfo.getCourses());	
		courses.remove(course);
		registrationInfo.setCourses(courses);
		
		// recalculate cost information
		registrationInfo.setCostInfo(calculateCostInfo());
	}
	
	@Override
	public String[] getCourses() {
		String[] courses = new String[registrationInfo.getCourses().size()];
		courses = registrationInfo.getCourses().toArray(courses);
		
		return courses;
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
    		costInfo.setEmployeeStatusCost(CostInfo.EMPLOYEE);
    	} else if (employmentStatus.equals("JHU Student")) {
    		costInfo.setEmployeeStatusCost(CostInfo.STUDENT);
    	} else if (employmentStatus.equals("Speaker")) {
    		costInfo.setEmployeeStatusCost(CostInfo.SPEAKER);
    	} else if (employmentStatus.equals("Other")) {
    		costInfo.setEmployeeStatusCost(CostInfo.OTHER);
    	}
    	
    	// calculate total cost
    	double total = 0.0;
    	
    	List<String> courses = registrationInfo.getCourses();
    	// total up cost of courses given employment status
    	total = costInfo.getEmployeeStatusCost() * courses.size();
    	
    	// if selected hotel, add to total
    	if (!StringUtils.isEmpty(registrationInfo.getHotel())) {
    		total = total + CostInfo.HOTEL;
    	}
    	
    	// if selected parking, add to total
    	if (!StringUtils.isEmpty(registrationInfo.getParking())) {
    		total = total + CostInfo.PARKING;
    	}
		
    	costInfo.setTotal(total);
    	
		return costInfo;
	}
}
