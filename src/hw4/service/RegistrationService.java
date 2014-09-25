/**
 * 
 */
package hw4.service;

import hw4.model.RegistrationInfo;

/**
 *
 * @author Nathan Shih
 * @date Sep 24, 2014
 */
public interface RegistrationService {

	public RegistrationInfo getRegistrationInfo();
	
	public void setRegistrationInfo(String name,
									String email,
									String[] courses,
									String employmentStatus,
									String hotel,
									String parking);
}
