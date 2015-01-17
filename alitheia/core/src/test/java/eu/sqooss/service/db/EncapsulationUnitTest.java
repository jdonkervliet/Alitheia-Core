package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class EncapsulationUnitTest extends DBTestSuper<EncapsulationUnit>{

	@Mock ProjectFile pf;
	@Mock NameSpace ns;
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new EncapsulationUnit());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testEncapsulationUnitProjectFile() {
		EncapsulationUnit eu = new EncapsulationUnit(pf);
		assertEquals(pf, eu.getFile());
	}

	@Test
	public final void testSetName() {
		String name = "name";
		getObjectUnderTest().setName(name);
		assertEquals(name, getObjectUnderTest().getName());
	}

	@Test
	public final void testSetNamespace() {
		getObjectUnderTest().setNamespace(ns);
		assertEquals(ns, getObjectUnderTest().getNamespace());
	}

	@Test
	public final void testSetExecUnits() {
		Set<ExecUnit> set = new HashSet<ExecUnit>();
		getObjectUnderTest().setExecUnits(set);
		assertEquals(set, getObjectUnderTest().getExecUnits());
	}

	@Test
	public final void testSetFile() {
		getObjectUnderTest().setFile(pf);
		assertEquals(pf, getObjectUnderTest().getFile());
	}

	@Test
	public final void testSetMeasurements() {
		Set<EncapsulationUnitMeasurement> measurements = new HashSet<EncapsulationUnitMeasurement>();
		getObjectUnderTest().setMeasurements(measurements);
		assertEquals(measurements, getObjectUnderTest().getMeasurements());
	}

	@Test
	public final void testToString() {
		getObjectUnderTest().setName("name");
		assertEquals("name", getObjectUnderTest().toString());
	}

}
