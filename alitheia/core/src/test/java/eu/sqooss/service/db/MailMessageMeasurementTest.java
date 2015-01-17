package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class MailMessageMeasurementTest extends
		DBTestSuper<MailMessageMeasurement> {

	@Mock Metric m;
	@Mock MailMessage mail;
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new MailMessageMeasurement());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testMailMessageMeasurementMetricMailMessageString() {
		String value = "value";
		MailMessageMeasurement mmm = new MailMessageMeasurement(m, mail, value);
		assertEquals(mail, mmm.getMail());
		assertEquals(m, mmm.getMetric());
		assertEquals(value, mmm.getResult());
	}

	@Test
	public final void testSetMail() {
		getObjectUnderTest().setMail(mail);
		assertEquals(mail, getObjectUnderTest().getMail());
	}

	@Test
	public final void testSetMetric() {
		getObjectUnderTest().setMetric(m);
		assertEquals(m, getObjectUnderTest().getMetric());
	}

	@Test
	public final void testSetResult() {
		String value = "value";
		getObjectUnderTest().setResult(value);
		assertEquals(value, getObjectUnderTest().getResult());
	}

}
