package org.openmrs.module.coreapps;

import org.openmrs.Location;
import org.openmrs.module.emrapi.utils.ModuleProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * Properties for this module.
 */
@Component("coreAppsProperties")
public class CoreAppsProperties extends ModuleProperties {

	// when adding a new patient identifier via the patient dashboard, the location to use if not specified (and the identifier type requires a location)
	public Location getDefaultPatientIdentifierLocation() {
		return getLocationByGlobalProperty(CoreAppsConstants.GP_DEFAULT_PATIENT_IDENTIFIER_LOCATION);
	}

	public int getRecentDiagnosisPeriodInDays() {
		String gp = getGlobalProperty(CoreAppsConstants.GP_RECENT_DIAGNOSIS_PERIOD_IN_DAYS, false);
		if (StringUtils.hasText(gp)) {
			try {
				return Integer.parseInt(gp);
			} catch (NumberFormatException e) {
				throw new IllegalStateException("Invalid configuration: number of days expected in " + CoreAppsConstants.GP_RECENT_DIAGNOSIS_PERIOD_IN_DAYS, e);
			}
		}
		return 730; //2 years
	}

    public String getDashboardUrl() {
        String url = getGlobalProperty(CoreAppsConstants.GP_DASHBOARD_URL, false);
        if (!StringUtils.hasText(url)) {
            return "/coreapps/clinicianfacing/patient.page?patientId={{patientId}}&app=pih.app.clinicianDashboard";
        }
        else {
            return url;
        }
    }

}
