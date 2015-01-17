package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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

public class DeveloperTest extends DBTestSuperDoHQL2<Developer>{

	@Mock StoredProject sp;
	@Mock OhlohDeveloper ohloh;
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new Developer());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testSetName() {
		String name = "Stefan Hugtenburg";
		getObjectUnderTest().setName(name);
		assertEquals(name, getObjectUnderTest().getName());
	}

	@Test
	public final void testSetUsername() {
		String username = "MrHug";
		getObjectUnderTest().setUsername(username);
		assertEquals(username, getObjectUnderTest().getUsername());
	}

	@Test
	public final void testSetStoredProject() {
		getObjectUnderTest().setStoredProject(sp);
		assertEquals(sp, getObjectUnderTest().getStoredProject());
	}

	@Test
	public final void testSetCommits() {
		Set<Version> set = new HashSet<Version>();
		getObjectUnderTest().setCommits(set);
		assertEquals(set, getObjectUnderTest().getCommits());
	}

	@Test
	public final void testSetMails() {
		Set<IMailMessage> mails = new HashSet<IMailMessage>();
		getObjectUnderTest().setMails(mails);
		assertEquals(mails, getObjectUnderTest().getMails());
	}

	@Test
	public final void testSetAliases() {
		Set<DeveloperAlias> set = new HashSet<DeveloperAlias>();
		getObjectUnderTest().setAliases(set);
		assertEquals(set, getObjectUnderTest().getAliases());
	}

	@Test
	public final void testAddAlias() {
		Set<DeveloperAlias> set = new HashSet<DeveloperAlias>();
		getObjectUnderTest().setAliases(set);
		String email = "e-mail here";
		getObjectUnderTest().addAlias(email);
		assertTrue(getObjectUnderTest().getAliases().contains(new DeveloperAlias(email, getObjectUnderTest())));
		assertEquals(1, getObjectUnderTest().getAliases().size());
	}
	
	@Test
	public final void testAddAlias_2() {
		Set<DeveloperAlias> set = new HashSet<DeveloperAlias>();
		getObjectUnderTest().setAliases(set);
		String email = "e-mail here";
		getObjectUnderTest().addAlias(email);
		getObjectUnderTest().addAlias(email);
		assertTrue(getObjectUnderTest().getAliases().contains(new DeveloperAlias(email, getObjectUnderTest())));
		assertEquals(1, getObjectUnderTest().getAliases().size());
	}

	@Test
	public final void testGetDeveloperByEmailStringStoredProject() {
		super.testGetStaticDoHQL2_ShouldBeObject();
		assertEquals(getObjectUnderTest(), Developer.getDeveloperByEmail("", null));
	}
	
	@Test
	public final void testGetDeveloperByEmailStringStoredProject_2() {
		super.testGetStaticDoHQL2_ShouldBeNull();
		assertNull(Developer.getDeveloperByEmail("", null));
	}
	
	@Test
	public final void testGetDeveloperByEmailStringStoredProject_3() {
		List<OhlohDeveloper> list = new ArrayList<OhlohDeveloper>();
		List<Developer> devList = new ArrayList<Developer>();
		list.add(ohloh);
		devList.add(getObjectUnderTest());
		super.testGetStaticDoHQL2_ShouldBeNull();
		Mockito.doReturn(list).doReturn(devList).when(dbService).findObjectsByProperties((Class<DAObject>) Matchers.any(), Matchers.anyMap());
		Mockito.doReturn(false).when(dbService).addRecord((DAObject) Matchers.any());
		assertEquals(getObjectUnderTest(), Developer.getDeveloperByEmail("", null));
	}
	
	@Test
	public final void testGetDeveloperByEmailStringStoredProject_4() {
		List<OhlohDeveloper> list = new ArrayList<OhlohDeveloper>();
		List<Developer> devList = new ArrayList<Developer>();
		list.add(ohloh);
		super.testGetStaticDoHQL2_ShouldBeNull();
		Mockito.doReturn(list).doReturn(devList).when(dbService).findObjectsByProperties((Class<DAObject>) Matchers.any(), Matchers.anyMap());
		Mockito.doReturn(false).when(dbService).addRecord((DAObject) Matchers.any());
		assertNull(Developer.getDeveloperByEmail("", null, false));
	}
	
	@Test
	public final void testGetDeveloperByEmailStringStoredProject_5() {
		List<OhlohDeveloper> list = new ArrayList<OhlohDeveloper>();
		List<Developer> devList = new ArrayList<Developer>();
		list.add(ohloh);
		super.testGetStaticDoHQL2_ShouldBeNull();
		Mockito.doReturn(list).doReturn(devList).when(dbService).findObjectsByProperties((Class<DAObject>) Matchers.any(), Matchers.anyMap());
		Mockito.doReturn(true).when(dbService).addRecord((DAObject) Matchers.any());
		assertFalse(Developer.getDeveloperByEmail("", null, true) == null);
	}

	@Test
	public final void testGetDeveloperByUsernameStringStoredProject() {
		super.testGetStaticDoHQL2_ShouldBeObject();
		Mockito.doReturn(true).when(dbService).addRecord((DAObject) Matchers.any());
		assertFalse(Developer.getDeveloperByUsername("", null) == null);
	}
	
	@Test
	public final void testGetDeveloperByUsernameStringStoredProject_2() {
		super.testGetStaticDoHQL2_ShouldBeObject();
		Mockito.doReturn(false).when(dbService).addRecord((DAObject) Matchers.any());
		assertNull(Developer.getDeveloperByUsername("", null));
	}

	@Test
	public final void testGetDeveloperByNameStringStoredProjectBoolean() {
		List<Developer> list = new ArrayList<Developer>();
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		Mockito.doReturn(true).when(dbService).addRecord((DAObject) Matchers.any());
		assertFalse(Developer.getDeveloperByName("", sp, true) == null);
	}
	
	@Test
	public final void testGetDeveloperByNameStringStoredProjectBoolean_2() {
		List<Developer> list = new ArrayList<Developer>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		Mockito.doReturn(true).when(dbService).addRecord((DAObject) Matchers.any());
		assertEquals(getObjectUnderTest(), Developer.getDeveloperByName("", sp, false));
	}
	
	@Test
	public final void testGetDeveloperByNameStringStoredProjectBoolean_3() {
		List<Developer> list = new ArrayList<Developer>();
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		assertNull(Developer.getDeveloperByName("", sp, false));
	}
	
	@Test
	public final void testGetDeveloperByNameStringStoredProjectBoolean_4() {
		List<Developer> list = new ArrayList<Developer>();
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		Mockito.doReturn(false).when(dbService).addRecord((DAObject) Matchers.any());
		assertNull(Developer.getDeveloperByName("", sp, true));
	}
	
	@Test
	public final void testToString() {
		String e = "null aka:null ()";
		assertEquals(e, getObjectUnderTest().toString());
	}
	
	@Test
	public final void testToString_2() {
		String e = "null, aka:null (<hoi> )";
		getObjectUnderTest().addAlias("hoi");
		assertEquals(e, getObjectUnderTest().toString());
	}


}
