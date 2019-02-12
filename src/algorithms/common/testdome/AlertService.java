package algorithms.common.testdome;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * Refactor the AlertService and MapAlertDAO classes:
	> Create a new package-private interface, named AlertDAO, that contains the same methods as MapAlertDAO.
	> MapAlertDAO should implement the AlertDAO interface.
	> AlertService should have a constructor that accepts AlertDAO.
	> The raiseAlert and getAlertTime methods should use the object passed through the constructor.
 *	
 * @author wendellopes
 *
 */

/**
  Compilation OK and all test cases pass, great!
  MapAlertDAO implements AlertDAO interface: Correct answer 
  AlertService accepts AlertDAO in its constructor: Correct answer 
  The raiseAlert and getAlertTime methods use AlertDAO passed through constructor: Correct answer 
 
 */

class AlertService {
    private AlertDAO storage = new MapAlertDAO(); // change MapAlertDAO to AlertDAO and remove 'final'
    
    // Step 3 - AlertService should have a constructor that accepts AlertDAO.
    public AlertService(AlertDAO alertDAO) {
    		// Step 4 - The raiseAlert and getAlertTime methods should use the object passed through the constructor.
    		this.storage = alertDAO;
    }
		
    public UUID raiseAlert() {
        return this.storage.addAlert(new Date());
    }
	
    public Date getAlertTime(UUID id) {
        return this.storage.getAlert(id);
    }	
}

class MapAlertDAO implements AlertDAO { // Step 2 - MapAlertDAO should implement the AlertDAO interface.
    private final Map<UUID, Date> alerts = new HashMap<UUID, Date>();
	
    public UUID addAlert(Date time) {
    	UUID id = UUID.randomUUID();
        this.alerts.put(id, time);
        return id;
    }
	
    public Date getAlert(UUID id) {
        return this.alerts.get(id);
    }	
}
/* 
 * Step 1 - Create a new package-private interface, named AlertDAO, that contains the same methods as MapAlertDAO.
 */
interface AlertDAO{
	UUID addAlert(Date time);
	Date getAlert(UUID id);
}