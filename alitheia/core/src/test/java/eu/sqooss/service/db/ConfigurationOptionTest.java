/**
 * 
 */
package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * @author stefan
 *
 */
public class ConfigurationOptionTest extends DBTestSuperObjectProp<ConfigurationOption> {

	@Mock StoredProjectConfig spc;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp(new ConfigurationOption());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}
	
	/**
	 * Test method for {@link eu.sqooss.service.db.ConfigurationOption#ConfigurationOption(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testConfigurationOptionStringString() {
		String key = "key";
		String desc = "desc";
		ConfigurationOption option = new ConfigurationOption(key, desc);
		assertEquals(key, option.getKey());
		assertEquals(desc, option.getDescription());
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.ConfigurationOption#setKey(java.lang.String)}.
	 */
	@Test
	public final void testSetKey() {
		String key = "key";
		getObjectUnderTest().setKey(key);
		assertEquals(key, getObjectUnderTest().getKey());
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.ConfigurationOption#setDescription(java.lang.String)}.
	 */
	@Test
	public final void testSetDescription() {
		String key = "key";
		getObjectUnderTest().setDescription(key);
		assertEquals(key, getObjectUnderTest().getDescription());
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.ConfigurationOption#setConfigurations(java.util.Set)}.
	 */
	@Test
	public final void testSetConfigurations() {
		Set<StoredProjectConfig> set = new HashSet<StoredProjectConfig>();
		getObjectUnderTest().setConfigurations(set);
		assertEquals(set, getObjectUnderTest().getConfigurations());
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.ConfigurationOption#setValues(eu.sqooss.service.db.StoredProject, java.util.List, boolean)}.
	 */
	@Test
	public final void testSetValues_1() {
		List<StoredProjectConfig> list = new ArrayList<StoredProjectConfig>();
		List<String> stringList = new ArrayList<String>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		getObjectUnderTest().setValues(null, stringList, false);
		Mockito.verify(super.dbService, Mockito.times(0)).addRecord((DAObject) Matchers.any());
	}
	
	/**
	 * Test method for {@link eu.sqooss.service.db.ConfigurationOption#setValues(eu.sqooss.service.db.StoredProject, java.util.List, boolean)}.
	 */
	@Test
	public final void testSetValues_2() {
		List<StoredProjectConfig> list = new ArrayList<StoredProjectConfig>();
		List<String> stringList = new ArrayList<String>();
		String e = "hi there";
		String e2 = "another string";
		Mockito.doReturn(e).doReturn(e).when(spc).getValue();
		
		list.add(spc);
		list.add(spc);
		stringList.add(e);
		stringList.add(e2);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		getObjectUnderTest().setValues(null, stringList, false);
		Mockito.verify(super.dbService, Mockito.times(0)).addRecord((DAObject) Matchers.any());
	}
	
	/**
	 * Test method for {@link eu.sqooss.service.db.ConfigurationOption#setValues(eu.sqooss.service.db.StoredProject, java.util.List, boolean)}.
	 */
	@Test
	public final void testSetValues_3() {
		List<StoredProjectConfig> list = new ArrayList<StoredProjectConfig>();
		List<String> stringList = new ArrayList<String>();
		String e = "hi there";
		String e2 = "another string";
		Mockito.doReturn(e2).when(spc).getValue();
		
		list.add(spc);
		stringList.add(e);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		getObjectUnderTest().setValues(null, stringList, false);
		Mockito.verify(super.dbService, Mockito.times(1)).addRecord((DAObject) Matchers.any());
	}
	
	@Test
	public final void testSetValues_4() {
		List<StoredProjectConfig> list = new ArrayList<StoredProjectConfig>();
		List<String> stringList = new ArrayList<String>();
		String e = "hi there";
		String e2 = "another string";
		Mockito.doReturn(e2).when(spc).getValue();
		
		list.add(spc);
		stringList.add(e);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		getObjectUnderTest().setValues(null, stringList, true);
		Mockito.verify(super.dbService, Mockito.times(1)).addRecord((DAObject) Matchers.any());
		Mockito.verify(super.dbService, Mockito.times(1)).deleteRecords(Matchers.eq(list));
	}
	
	
	@Test
	public final void testGetValues_1() {
		List<String> stringList = new ArrayList<String>();
		String e = "hi there";
		stringList.add(e);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(stringList);
		assertEquals(stringList, getObjectUnderTest().getValues(null));
	}


	/**
	 * Test method for {@link eu.sqooss.service.db.ConfigurationOption#fromKey(java.lang.String)}.
	 */
	@Test
	public final void testFromKey() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), ConfigurationOption.fromKey(""));
	}
	
	@Test
	public final void testFromKey_2() {
		super.testGetObjectProp_Null_1();
		assertNull(ConfigurationOption.fromKey(""));
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.ConfigurationOption#toString()}.
	 */
	@Test
	public final void testToString() {
		assertEquals("null - null", getObjectUnderTest().toString());
	}
	
	/**
	 * Test method for {@link eu.sqooss.service.db.ConfigurationOption#toString()}.
	 */
	@Test
	public final void testToString_2() {
		getObjectUnderTest().setKey("k");
		getObjectUnderTest().setDescription("d");
		assertEquals("k - d", getObjectUnderTest().toString());
	}

}
