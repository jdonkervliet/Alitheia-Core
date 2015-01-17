package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

public class PluginConfigurationTest extends DBTestSuperObjectProp<PluginConfiguration> {

	@Mock Plugin p;
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new PluginConfiguration());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testSetValue() {
		String value = "value";
		getObjectUnderTest().setValue(value);
		assertEquals(value, getObjectUnderTest().getValue());
	}

	@Test
	public final void testSetType() {
		String type = "type";
		getObjectUnderTest().setType(type);
		assertEquals(type, getObjectUnderTest().getType());
	}

	@Test
	public final void testSetPlugin() {
		getObjectUnderTest().setPlugin(p);
		assertEquals(p, getObjectUnderTest().getPlugin());
	}

	@Test
	public final void testSetName() {
		String name = "name";
		getObjectUnderTest().setName(name);
		assertEquals(name, getObjectUnderTest().getName());
	}

	@Test
	public final void testSetMsg() {
		String msg = "msg";
		getObjectUnderTest().setMsg(msg);
		assertEquals(msg, getObjectUnderTest().getMsg());
	}

	@Test
	public final void testGetConfigurationEntry() {
		super.testGetObjectProp_Null_1();
		assertNull(PluginConfiguration.getConfigurationEntry(p, new HashMap<String, Object>()));
	}

	@Test
	public final void testGetConfigurationEntry_2() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), PluginConfiguration.getConfigurationEntry(p, new HashMap<String, Object>()));
	}

	@Test
	public final void testUpdConfigurationEntry() {
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(null);
		super.testGetObjectProp_Null_1();
		assertFalse(PluginConfiguration.updConfigurationEntry(p, new HashMap<String, Object>()));
	}
	
	@Test
	public final void testUpdConfigurationEntry_2() {
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(null);
		List<PluginConfiguration> list = new ArrayList<PluginConfiguration>();
		list.add(getObjectUnderTest());
		Mockito.doReturn(list).doReturn(new ArrayList<PluginConfiguration>())
		.when(dbService)
		.findObjectsByProperties((Class<DAObject>) Matchers.any(),
				Matchers.anyMap());
		assertFalse(PluginConfiguration.updConfigurationEntry(p, new HashMap<String, Object>()));
	}
	
	@Test
	public final void testUpdConfigurationEntry_3() {
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(null);
		List<PluginConfiguration> list = new ArrayList<PluginConfiguration>();
		list.add(getObjectUnderTest());
		Mockito.doReturn(list).doReturn(list)
		.when(dbService)
		.findObjectsByProperties((Class<DAObject>) Matchers.any(),
				Matchers.anyMap());
		assertTrue(PluginConfiguration.updConfigurationEntry(p, new HashMap<String, Object>()));
	}

}
