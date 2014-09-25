package hw4.service;

import hw4.model.RegistrationInfo;

/**
 * The RegistrationService interface provides entry points to the business logic.
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
									String[] courses,
									String employmentStatus,
									String hotel,
									String parking);
}
