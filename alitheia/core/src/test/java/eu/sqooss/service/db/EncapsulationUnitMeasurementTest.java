/**
 * 
 */
package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * @author stefan
 *
 */
public class EncapsulationUnitMeasurementTest extends
		DBTestSuper<EncapsulationUnitMeasurement> {

	@Mock EncapsulationUnit eu;
	@Mock Metric m;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp(new EncapsulationUnitMeasurement());
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.EncapsulationUnitMeasurement#setId(long)}.
	 */
	@Test
	public final void testSetId() {
		super.testSetId();
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.EncapsulationUnitMeasurement#getResult()}.
	 */
	@Test
	public final void testGetResult() {
		String result = "result here";
		getObjectUnderTest().setResult(result);
		assertEquals(result, getObjectUnderTest().getResult());
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.EncapsulationUnitMeasurement#EncapsulationUnitMeasurement(eu.sqooss.service.db.EncapsulationUnit, eu.sqooss.service.db.Metric, java.lang.String)}.
	 */
	@Test
	public final void testEncapsulationUnitMeasurementEncapsulationUnitMetricString() {
		String s = "hallo";
		EncapsulationUnitMeasurement eum = new EncapsulationUnitMeasurement(eu, m, s);
		assertEquals(s, eum.getResult());
		assertEquals(eu, eum.getEncapsulationUnit());
		assertEquals(m, eum.getMetric());
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.EncapsulationUnitMeasurement#setEncapsulationUnit(eu.sqooss.service.db.EncapsulationUnit)}.
	 */
	@Test
	public final void testSetEncapsulationUnit() {
		getObjectUnderTest().setEncapsulationUnit(eu);
		assertEquals(eu, getObjectUnderTest().getEncapsulationUnit());
	}

	/**
	 * Test method for {@link eu.sqooss.service.db.EncapsulationUnitMeasurement#setMetric(eu.sqooss.service.db.Metric)}.
	 */
	@Test
	public final void testSetMetric() {
		getObjectUnderTest().setMetric(m);
		assertEquals(m, getObjectUnderTest().getMetric());
	}
}
