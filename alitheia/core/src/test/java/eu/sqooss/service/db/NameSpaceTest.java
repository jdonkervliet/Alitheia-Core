package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class NameSpaceTest extends DBTestSuperDoHQL2<NameSpace> {

	@Mock private ProjectVersion pv;
	private Language lang;

	@Before
	public void setUp() throws Exception {
		super.setUp(new NameSpace());
		lang = Language.PYTHON;
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testSetName() {
		String name = "this is a name";
		getObjectUnderTest().setName(name);
		assertEquals(name, getObjectUnderTest().getName());
	}

	@Test
	public final void testSetChangeVersion() {
		getObjectUnderTest().setChangeVersion(pv);
		assertEquals(pv, getObjectUnderTest().getChangeVersion());
	}

	@Test
	public final void testSetEncapsulationUnits() {
		Set<EncapsUnit> encapsulationUnits = new HashSet<EncapsUnit>();
		getObjectUnderTest().setEncapsulationUnits(encapsulationUnits);
		assertEquals(encapsulationUnits, getObjectUnderTest().getEncapsulationUnits());
	}
	
	@Test
	public final void testSetEncapsulationUnits_2() {
		Set<EncapsUnit> encapsulationUnits = null;
		getObjectUnderTest().setEncapsulationUnits(encapsulationUnits);
		assertNotNull(getObjectUnderTest().getEncapsulationUnits());
	}

	@Test
	public final void testSetExecutionUnits() {
		Set<ExecUnit> executionUnits = new HashSet<ExecUnit>();
		getObjectUnderTest().setExecutionUnits(executionUnits);
		assertEquals(executionUnits, getObjectUnderTest().getExecutionUnits());
	}
	
	@Test
	public final void testSetExecutionUnits_2() {
		Set<ExecUnit> executionUnits = null;
		getObjectUnderTest().setExecutionUnits(executionUnits);
		assertNotNull(getObjectUnderTest().getExecutionUnits());
	}

	@Test
	public final void testSetLang() {
		getObjectUnderTest().setLang(lang);
		assertEquals(lang, getObjectUnderTest().getLang());
	}

	@Test
	public final void testFindByVersionName() {
		super.testGetStaticDoHQL2_ShouldBeNull();
		assertNull(NameSpace.findByVersionName(pv, ""));
	}
	
	@Test
	public final void testFindByVersionName_2() {
		super.testGetStaticDoHQL2_ShouldBeObject();
		assertEquals(getObjectUnderTest(), NameSpace.findByVersionName(pv, ""));
	}

}
