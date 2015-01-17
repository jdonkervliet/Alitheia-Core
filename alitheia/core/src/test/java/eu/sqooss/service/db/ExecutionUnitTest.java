package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ExecutionUnitTest extends DBTestSuper<ExecutionUnit> {

	@Mock EncapsulationUnit eu;
	@Mock NameSpace ns;
	@Mock ProjectFile file;
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new ExecutionUnit());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testExecutionUnitEncapsulationUnit() {
		ExecutionUnit execu = new ExecutionUnit(eu);
		assertEquals(eu, execu.getEncapsulationUnit());
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
	public final void testSetEncapsulationUnit() {
		getObjectUnderTest().setEncapsulationUnit(eu);
		assertEquals(eu, getObjectUnderTest().getEncapsulationUnit());
	}

	@Test
	public final void testSetFile() {
		getObjectUnderTest().setFile(file);
		assertEquals(file, getObjectUnderTest().getFile());
	}

	@Test
	public final void testSetChanged() {
		getObjectUnderTest().setChanged(true);
		assertTrue(getObjectUnderTest().isChanged());
	}

	@Test
	public final void testSetMeasurements() {
		Set<ExecutionUnitMeasurement> measurements = new HashSet<ExecutionUnitMeasurement>();
		getObjectUnderTest().setMeasurements(measurements);
		assertEquals(measurements, getObjectUnderTest().getMeasurements());
	}

	@Test
	public final void testGetFullyQualifiedName() {
		getObjectUnderTest().setEncapsulationUnit(eu);
		assertEquals(getObjectUnderTest().toString(), getObjectUnderTest().getFullyQualifiedName());
	}

	@Test
	public final void testToString() {
		getObjectUnderTest().setEncapsulationUnit(eu);
		assertEquals("eu::null", getObjectUnderTest().toString());
	}

}
