package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import eu.sqooss.core.AlitheiaCore;

public abstract class DBTestSuper<T extends DAObject> {

	@Mock
	protected AlitheiaCore alitheiaCore;
	@Mock
	protected DBService dbService;

	T objectUnderTest;

	public DBTestSuper() {
		super();
	}

	public void setUp(T object) throws Exception {
		MockitoAnnotations.initMocks(this);
		objectUnderTest = object;
	}

	public T getObjectUnderTest() {
		return objectUnderTest;
	}

	protected void setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(
			List<?> list) {
		AlitheiaCore.setTestInstance(alitheiaCore);
		Mockito.when(alitheiaCore.getDBService()).thenReturn(dbService);
		Mockito.doReturn(list)
				.when(dbService)
				.findObjectsByProperties((Class<DAObject>) Matchers.any(),
						Matchers.anyMap());
	}

	protected void setUpMockedDBService() {
		AlitheiaCore.setTestInstance(alitheiaCore);
		Mockito.when(alitheiaCore.getDBService()).thenReturn(dbService);
	}

	protected void setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(List<?> list) {
		AlitheiaCore.setTestInstance(alitheiaCore);
		Mockito.when(alitheiaCore.getDBService()).thenReturn(dbService);
		Mockito.doReturn(list).when(dbService)
				.doHQL(Matchers.anyString(), Matchers.anyMap(), Matchers.eq(1));
	}

	protected void setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(List<?> list) {
		AlitheiaCore.setTestInstance(alitheiaCore);
		Mockito.when(alitheiaCore.getDBService()).thenReturn(dbService);
		Mockito.doReturn(list).when(dbService)
				.doHQL(Matchers.anyString(), Matchers.anyMap());
	}

	protected void setUpMockedAlitheiaCoreAndDbServiceForDoHQL1(List<?> list) {
		AlitheiaCore.setTestInstance(alitheiaCore);
		Mockito.when(alitheiaCore.getDBService()).thenReturn(dbService);
		Mockito.doReturn(list).when(dbService).doHQL(Matchers.anyString());
	}

	public void testSetId() {
		long id = 1231;
		objectUnderTest.setId(id);
		assertEquals(id, objectUnderTest.getId());
	}
	
	@After
	public void after() {
		AlitheiaCore.setTestInstance(null);
	}

}
