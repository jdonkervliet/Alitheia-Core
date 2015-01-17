package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class PluginTest extends DBTestSuperObjectProp<Plugin> {

	@Before
	public void setUp() throws Exception {
		super.setUp(new Plugin());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testSetName() {
		String name = "name";
		getObjectUnderTest().setName(name);
		assertEquals(name, getObjectUnderTest().getName());
	}

	@Test
	public final void testSetInstalldate() {
		Date installdate = new Date();
		getObjectUnderTest().setInstalldate(installdate);
		assertEquals(installdate, getObjectUnderTest().getInstalldate());
	}

	@Test
	public final void testSetVersion() {
		String version = "version";
		getObjectUnderTest().setVersion(version);
		assertEquals(version, getObjectUnderTest().getVersion());
	}

	@Test
	public final void testSetDescription() {
		String description = "description";
		getObjectUnderTest().setDescription(description);
		assertEquals(description, getObjectUnderTest().getDescription());
	}

	@Test
	public final void testSetActive() {
		getObjectUnderTest().setActive(true);
		assertTrue(getObjectUnderTest().isActive());
	}

	@Test
	public final void testSetHashcode() {
		String hashcode = "hashcode'";
		getObjectUnderTest().setHashcode(hashcode);
		assertEquals(hashcode, getObjectUnderTest().getHashcode());
	}

	@Test
	public final void testSetConfigurations() {
		Set<PluginConfiguration> configurations = new HashSet<PluginConfiguration>();
		getObjectUnderTest().setConfigurations(configurations);
		assertEquals(configurations, getObjectUnderTest().getConfigurations());
	}

	@Test
	public final void testSetSupportedMetrics() {
		Set<Metric> supportedMetrics = new HashSet<Metric>();
		getObjectUnderTest().setSupportedMetrics(supportedMetrics);
		assertEquals(supportedMetrics, getObjectUnderTest().getSupportedMetrics());
	}

	@Test
	public final void testGetPluginByName() {
		super.testGetObjectProp_Null_1();
		assertEquals(0, Plugin.getPluginByName("").size());
	}

	@Test
	public final void testGetPluginByHashcode() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), Plugin.getPluginByHashcode("hashcode"));
	}
	
	@Test
	public final void testGetPluginByHashcode_2() {
		super.testGetObjectProp_Null_1();
		assertNull(Plugin.getPluginByHashcode("hashcode"));
	}

}
