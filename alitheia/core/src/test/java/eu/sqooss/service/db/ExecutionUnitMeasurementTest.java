package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ExecutionUnitMeasurementTest extends
		DBTestSuper<ExecutionUnitMeasurement> {

	@Mock ExecutionUnit eu;
	@Mock Metric m;
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new ExecutionUnitMeasurement());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testExecutionUnitMeasurementExecutionUnitMetricString() {
		String st = "hi";
		ExecutionUnitMeasurement eum = new ExecutionUnitMeasurement(eu, m, st);
		assertEquals(eu, eum.getExecutionUnit());
		assertEquals(m, eum.getMetric());
		assertEquals(st, eum.getResult());
	}

	@Test
	public final void testSetExecutionUnit() {
		getObjectUnderTest().setExecutionUnit(eu);
		assertEquals(eu, getObjectUnderTest().getExecutionUnit());
	}

	@Test
	public final void testSetMetric() {
		getObjectUnderTest().setMetric(m);
		assertEquals(m, getObjectUnderTest().getMetric());
	}

	@Test
	public final void testSetResult() {
		String result = "get result here";
		getObjectUnderTest().setResult(result);
		assertEquals(result, getObjectUnderTest().getResult());
	}

}
