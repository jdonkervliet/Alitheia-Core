package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

public class DBTestSuperObjectProp<T extends DAObject> extends DBTestSuper<T> {
	
	/**
	 * Test method for {@link eu.sqooss.service.db.BugPriority#getBugPriority(java.lang.String, boolean)}.
	 */
	protected final void testGetObjectProp_Equals_1() {
		List<T> list = new ArrayList<T>();
		list.add(getObjectUnderTest());
		this.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
	}
	
	protected final void testGetObjectProp_Null_1() {
		List<T> list = new ArrayList<T>();
		this.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
	}
	
	protected final void testGetObjectProp_Null_2() {
		List<T> list = new ArrayList<T>();
		this.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		Mockito.doReturn(false).when(dbService).addRecord((DAObject) Matchers.any());
	}
	
	protected final void testGetObjectProp_Equals_2() {
		List<T> list = new ArrayList<T>();
		this.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		Mockito.doReturn(true).when(dbService).addRecord((DAObject) Matchers.any());
	}
}
