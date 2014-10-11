package devseminar.service;

import java.util.List;

import devseminar.model.CostInfo;
import devseminar.model.RegistrationInfo;

/**
 * The RegistrationService interface provides entry points to the registration service business logic.
 *
 * @author Nathan Shih
 * @date Sep 24, 2014
 */
public interface RegistrationService {

	/**
	 * Return the stored registration information.
	 *
	 * @return RegistrationInfo
	 */
	public RegistrationInfo getRegistrationInfo();
	
	/**
	 * Stores the registration information. <b>Note:</b> At least one course and an employment status must be chosen.
	 * This validation is done prior to calling this method. It is done in the RegistrationServlet.doPost() method.
	 *
	 * @param name - name of the registrant
	 * @param email - email of the registrant
	 * @param courses - courses selected, at least one course is required
	 * @param employmentStatus - employment status, this is required
	 * @param hotel - hotel accommodation
	 * @param parking - parking permit
	 */
	public void setRegistrationInfo(String name,
									String email,
									List<String> courses,
									String employmentStatus,
									String hotel,
									String parking);
	/**
	 * Returned the stored cost information.
	 *
	 * @return CostInfo
	 */
	public CostInfo getCostInfo();
	
	/**
	 * Removes a selected course.
	 *
	 * @param course - name of the course to remove
	 */
	public void removeCourse(String course);
	
	/**
	 * Return the courses as a String array.
	 *
	 * @return String[]
	 */
	public String[] getCourses();
}
