package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DBTestSuperDoHQL2<T extends DAObject> extends DBTestSuper<T> {
	
	protected void testGetStaticDoHQL2_ShouldBeNull() {
		List<T> list = new ArrayList<T>();
		this.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);

	}

	protected void testGetStaticDoHQL2_ShouldBeObject() {
		List<T> list = new ArrayList<T>();
		list.add(getObjectUnderTest());
		this.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
	}
}
