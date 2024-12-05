package tests;

import java.util.Set;
import org.junit.Test;

import database.DataBaseCommands;
import database.DataBaseCommandsForHealthStatus;

public class TestsDataBaseCommandsForHealthStatus {
	
	// Test search health status id by health status name (test searchHealthStatusIdByHealthStatusNameInDataBase function)
	@Test
	public void testSearchHealthStatusIdByHealthStatusName() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Search health status name exist
			int healthStatusIdExist = DataBaseCommandsForHealthStatus.searchHealthStatusIdByHealthStatusNameInDataBase(
					DataBaseCommands.HEALTHY_HEALTH_STATUS_NAME_IN_DATA_BASE);
			
			// Search health status name not exist
			int healthStatusIdNotExist1 = DataBaseCommandsForHealthStatus.searchHealthStatusIdByHealthStatusNameInDataBase("Not-Exist");
			
			// Search health status name not exist (null)
			int healthStatusIdNotExist2 = DataBaseCommandsForHealthStatus.searchHealthStatusIdByHealthStatusNameInDataBase(null);
			
			// Print results
			assert((healthStatusIdExist != DataBaseCommands.HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE) && 
					(healthStatusIdNotExist1 == DataBaseCommands.HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE) &&
					(healthStatusIdNotExist2 == DataBaseCommands.HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE));
			System.out.println("Test search health status id by health status name - Success! \n");
		}
		catch(Exception e) {
			System.out.println("Test search health status id by health status name - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test search health status name by health status id (test searchHealthStatusNameByHealthStatusIdInDataBase function)
	@Test
	public void testSearchHealthStatusNameByHealthStatusId() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Search health status exist
			int healthStatusIdExist = DataBaseCommandsForHealthStatus.searchHealthStatusIdByHealthStatusNameInDataBase(
					DataBaseCommands.HEALTHY_HEALTH_STATUS_NAME_IN_DATA_BASE);
			String healthStatusExist = DataBaseCommandsForHealthStatus.searchHealthStatusNameByHealthStatusIdInDataBase(healthStatusIdExist);
			
			// Search health status not exist
			String healthStatusNotExist = DataBaseCommandsForHealthStatus.searchHealthStatusNameByHealthStatusIdInDataBase(
					DataBaseCommands.HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE);
			
			// Print results
			assert((healthStatusExist != null) && (healthStatusNotExist == null));
			System.out.println("Test search health status name by health status id - Success! \n");
		}
		catch(Exception e) {
			System.out.println("Test search health status name by health status id - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test add health status (test addHealthStatusToDataBase function)
	@Test
	public void testAddHealthStatus() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add health status not exist
			boolean addValidHealthStatusSucceed = DataBaseCommandsForHealthStatus.addHealthStatusToDataBase("Very-Healthy", 0);
			
			// Add health status not exist (null)
			boolean addValidHealthStatus1Failed = DataBaseCommandsForHealthStatus.addHealthStatusToDataBase(null, 0);
			
			// Add health status not exist (negative number of spaces)
			boolean addValidHealthStatus2Failed = DataBaseCommandsForHealthStatus.addHealthStatusToDataBase("Very-Very-Healthy", -5);
			
			// Add health status exist
			boolean addValidHealthStatus3Failed = DataBaseCommandsForHealthStatus.addHealthStatusToDataBase(DataBaseCommands.HEALTHY_HEALTH_STATUS_NAME_IN_DATA_BASE, 0);
			
			// Print results
			assert(addValidHealthStatusSucceed && !addValidHealthStatus1Failed && !addValidHealthStatus2Failed && !addValidHealthStatus3Failed);
			System.out.println("Test add health status - Success! \n");			
		}
		catch(Exception e) {
			System.out.println("Test add health status - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test update health status name (test updateHealthStatusNameInDataBase function)
	@Test
	public void testUpdateHealthStatusName() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Update exist health status to not exist health status
			boolean updateExistHealthStatusToNotExistHealthStatusSucceed = DataBaseCommandsForHealthStatus.updateHealthStatusNameInDataBase(
					DataBaseCommands.HEALTHY_HEALTH_STATUS_NAME_IN_DATA_BASE, "try change exist to not exist");
			
			// Update not exist health status (null)
			boolean updateNotExist1HealthStatusSucceed = DataBaseCommandsForHealthStatus.updateHealthStatusNameInDataBase(null, "try change null");
			
			// Update not exist health status
			boolean updateNotExist2HealthStatusSucceed = DataBaseCommandsForHealthStatus.updateHealthStatusNameInDataBase("not exist", "try change not exist");
			
			// Update exist health status to exist health status
			boolean updateExistHealthStatusToExistHealthStatusSucceed = DataBaseCommandsForHealthStatus.updateHealthStatusNameInDataBase(
					"try change exist to not exist", DataBaseCommands.COVID_19_HEALTH_STATUS_NAME_IN_DATA_BASE);
			
			// Print results
			assert(updateExistHealthStatusToNotExistHealthStatusSucceed && !updateNotExist1HealthStatusSucceed && !updateNotExist2HealthStatusSucceed && 
					!updateExistHealthStatusToExistHealthStatusSucceed);
			System.out.println("Test update health status name - Success! \n");
		} 
		catch(Exception e) {
			System.out.println("Test update health status name - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test update health status space (test updateHealthStatusSpaceInDataBase function)
	@Test
	public void testUpdateHealthStatusSpace() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Update exist health status to valid space
			boolean updateExistHealthStatusToValidSpaceSucceed = DataBaseCommandsForHealthStatus.updateHealthStatusSpaceInDataBase(
					DataBaseCommands.HEALTHY_HEALTH_STATUS_NAME_IN_DATA_BASE, 10);
			
			// Update not exist health status (null) to valid space
			boolean updateNotExist1HealthStatusToValidSpaceSucceed = DataBaseCommandsForHealthStatus.updateHealthStatusSpaceInDataBase(null, 10);
			
			// Update not exist health status to valid space
			boolean updateNotExist2HealthStatusToValidSpaceSucceed = DataBaseCommandsForHealthStatus.updateHealthStatusSpaceInDataBase("not exist", 5);
			
			// Update exist health status to not valid space
			boolean updateExistHealthStatusToNotValidSpaceSucceed = DataBaseCommandsForHealthStatus.updateHealthStatusSpaceInDataBase(
					DataBaseCommands.HEALTHY_HEALTH_STATUS_NAME_IN_DATA_BASE, -5);
			
			// Print results
			assert(updateExistHealthStatusToValidSpaceSucceed && !updateNotExist1HealthStatusToValidSpaceSucceed && !updateNotExist2HealthStatusToValidSpaceSucceed && 
					!updateExistHealthStatusToNotValidSpaceSucceed);
			System.out.println("Test update health status space - Success! \n");
		} 
		catch(Exception e) {
			System.out.println("Test update health status space - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test remove health status (test removeHealthStatusFromDataBase function)
	@Test
	public void testRemoveHealthStatus() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Remove exist health status
			boolean removeExistHealthStatusSucceed = DataBaseCommandsForHealthStatus.removeHealthStatusFromDataBase(
					DataBaseCommands.COVID_19_HEALTH_STATUS_NAME_IN_DATA_BASE);
			
			// Remove not exist health status (null)
			boolean removeNotExist1HealthStatusSucceed = DataBaseCommandsForHealthStatus.removeHealthStatusFromDataBase(null);
			
			// Remove not exist health status
			boolean removeNotExist2HealthStatusSucceed = DataBaseCommandsForHealthStatus.removeHealthStatusFromDataBase("Super-Healthy");
			
			// Print results
			assert(removeExistHealthStatusSucceed && !removeNotExist1HealthStatusSucceed && !removeNotExist2HealthStatusSucceed);
			System.out.println("Test remove health status - Success! \n");
		} 
		catch(Exception e) {
			System.out.println("Test remove health status - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test get all health statuses names (test getAllHealthStatusesNamesInDataBase function)
	@Test
	public void testGetAllHealthStatusesNamesAndSpaces() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Get all health statuses names and spaces
			Set<String> allHealthStatusesNames = DataBaseCommandsForHealthStatus.getAllHealthStatusesNamesInDataBase();
			
			// Print results
			assert(allHealthStatusesNames.size() == 2);
			System.out.println("Test get all health statuses names - Success! \n");
		} 
		catch(Exception e) {
			System.out.println("Test get all health statuses names - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test get space of health status (test getSpaceOfHealthStatusInDataBase function)
	@Test
	public void testGetSpaceOfHealthStatusInDataBase() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Get space of exist health status
			int spaceOfExistHealthStatus = DataBaseCommandsForHealthStatus.getSpaceOfHealthStatusInDataBase(DataBaseCommands.HEALTHY_HEALTH_STATUS_NAME_IN_DATA_BASE);
			
			// Get space of not exist health status (null)
			int spaceOfNotExist1HealthStatus = DataBaseCommandsForHealthStatus.getSpaceOfHealthStatusInDataBase(null);
			
			// Get space of not exist health status
			int spaceOfNotExist2HealthStatus = DataBaseCommandsForHealthStatus.getSpaceOfHealthStatusInDataBase("Super-Healthy");
			
			// Print results
			assert((spaceOfExistHealthStatus != DataBaseCommands.HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE) && 
					(spaceOfNotExist1HealthStatus == DataBaseCommands.HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE) && 
					(spaceOfNotExist2HealthStatus == DataBaseCommands.HEALTH_STATUS_ID_NOT_FOUND_IN_DATA_BASE));
			System.out.println("Test get space of health status - Success! \n");
		} 
		catch(Exception e) {
			System.out.println("Test get space of health status - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
}