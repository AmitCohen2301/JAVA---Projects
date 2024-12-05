package tests;

import java.util.Dictionary;
import org.junit.Test;

import database.DataBaseCommands;
import database.DataBaseCommandsForPermissions;

public class TestsDataBaseCommandsForPermissions {
	
	// Test search permission id by permission name (test searchPermissionIdByPermissionNameInDataBase function)
	@Test
	public void testSearchPermissionIdByPermissionName() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Search permission name exist
			int permissionIdExist = DataBaseCommandsForPermissions.searchPermissionIdByPermissionNameInDataBase(DataBaseCommands.ADMIN_PERMISSION_NAME_IN_DATA_BASE);
			
			// Search permission name not exist
			int permissionIdNotExist1 = DataBaseCommandsForPermissions.searchPermissionIdByPermissionNameInDataBase("moshe");
			
			// Search permission name not exist (null)
			int permissionIdNotExist2 = DataBaseCommandsForPermissions.searchPermissionIdByPermissionNameInDataBase(null);
			
			// Print results
			assert((permissionIdExist != DataBaseCommands.PERMISSION_ID_NOT_FOUND_IN_DATA_BASE) && 
					(permissionIdNotExist1 == DataBaseCommands.PERMISSION_ID_NOT_FOUND_IN_DATA_BASE) &&
					(permissionIdNotExist2 == DataBaseCommands.PERMISSION_ID_NOT_FOUND_IN_DATA_BASE));
			System.out.println("Test search permission id by permission name - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search permission id by permission name - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test search permission name by permission id (test searchPermissionNameByPermissionIdInDataBase function)
	@Test
	public void testSearchPermissionNameByPermissionId() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Search permission id exist
			int permissionIdExist = DataBaseCommandsForPermissions.searchPermissionIdByPermissionNameInDataBase(DataBaseCommands.ADMIN_PERMISSION_NAME_IN_DATA_BASE);
			String permissionNameExist = DataBaseCommandsForPermissions.searchPermissionNameByPermissionIdInDataBase(permissionIdExist);
			
			// Search permission id not exist
			String permissionNameNotExist = DataBaseCommandsForPermissions.searchPermissionNameByPermissionIdInDataBase(
					DataBaseCommands.PERMISSION_ID_NOT_FOUND_IN_DATA_BASE);
			
			// Print results
			assert((permissionNameExist != null) && (permissionNameNotExist == null));
			System.out.println("Test search permission name by permission id - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test search permission name by permission id - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test add permission (test addPermissionToDataBase function)
	@Test
	public void testAddPermission() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Add permission not exist
			boolean addValidPermissionSucceed = DataBaseCommandsForPermissions.addPermissionToDataBase("Super-Admin");
			
			// Add permission not exist (null)
			boolean addInvalidPermission1Failed = DataBaseCommandsForPermissions.addPermissionToDataBase(null);
			
			// Add permission exist
			boolean addInvalidPermission2Failed = DataBaseCommandsForPermissions.addPermissionToDataBase(DataBaseCommands.ADMIN_PERMISSION_NAME_IN_DATA_BASE);
			
			// Print results
			assert(addValidPermissionSucceed && !addInvalidPermission1Failed && !addInvalidPermission2Failed);
			System.out.println("Test add permission - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test add permission - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test update permission (test updatePermissionNameInDataBase function)
	@Test
	public void testUpdatePermission() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Update exist permission to not exist permission
			boolean updateExistPermissionToNotExistPermissionSucceed = DataBaseCommandsForPermissions.updatePermissionNameInDataBase
					(DataBaseCommands.ADMIN_PERMISSION_NAME_IN_DATA_BASE, "try change exist to not exist");
			
			// Update not exist permission (null)
			boolean updateNotExist1PermissionSucceed = DataBaseCommandsForPermissions.updatePermissionNameInDataBase(null, "try change null");
			
			// Update not exist permission
			boolean updateNotExist2PermissionSucceed = DataBaseCommandsForPermissions.updatePermissionNameInDataBase("not exist", "try change not exist");
			
			// Update exist permission to exist permission
			boolean updateExistPermissionToExistPermissionSucceed = DataBaseCommandsForPermissions.updatePermissionNameInDataBase(
					DataBaseCommands.MANAGE_EVENTS_PERMISSION_NAME_IN_DATA_BASE, DataBaseCommands.ORDER_EVENTS_PERMISSION_NAME_IN_DATA_BASE);
			
			// Print results
			assert(updateExistPermissionToNotExistPermissionSucceed && !updateNotExist1PermissionSucceed && !updateNotExist2PermissionSucceed && 
					!updateExistPermissionToExistPermissionSucceed);
			System.out.println("Test update permission - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test update permission - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test remove permission (test removePermissionFromDataBase function)
	@Test
	public void testRemovePermission() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Remove exist permission
			boolean removeExistPermissionSucceed = DataBaseCommandsForPermissions.removePermissionFromDataBase(DataBaseCommands.MANAGE_EVENTS_PERMISSION_NAME_IN_DATA_BASE);
			
			// Remove not exist permission (null)
			boolean removeNotExistPermission1Succeed = DataBaseCommandsForPermissions.removePermissionFromDataBase(null);
			
			// Remove not exist permission
			boolean removeNotExistPermission2Succeed = DataBaseCommandsForPermissions.removePermissionFromDataBase("Super");
			
			// Print results
			assert(removeExistPermissionSucceed && !removeNotExistPermission1Succeed && !removeNotExistPermission2Succeed);
			System.out.println("Test remove permission - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test remove permission - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
	
	// Test get all permissions ids and names (test getAllPermissionsIdsAndNamesInDataBase function)
	@Test
	public void testGetAllPermissionsIdsAndNames() throws Exception {
		try {
			// Init data base
			DataBaseCommands.initDataBase();
			
			// Get all permissions ids and names
			Dictionary<Integer, String> allPermissionsIdsAndNames = DataBaseCommandsForPermissions.getAllPermissionsIdsAndNamesInDataBase();
			
			// Print results
			assert(allPermissionsIdsAndNames.size() == 3);
			System.out.println("Test get all permissions ids and names - Success! \n");
		}
		catch(Exception e)
		{
			System.out.println("Test get all permissions ids and names - Failed!");
			System.out.println(e.getMessage());
			System.out.println();
			throw e;
		}
	}
}