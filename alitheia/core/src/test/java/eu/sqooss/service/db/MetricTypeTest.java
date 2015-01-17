package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class MetricTypeTest extends DBTestSuperObjectProp<MetricType> {

	MetricType.Type type;

	@Before
	public void setUp() throws Exception {
		super.setUp(new MetricType());
		type = MetricType.Type.BUG;
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testMetricTypeType() {
		MetricType mt = new MetricType(type);
		assertEquals(type, mt.getEnumType());
		assertEquals(type.toString(), mt.getType());
	}

	@Test
	public final void testSetEnumType() {
		getObjectUnderTest().setEnumType(type);
		assertEquals(type, getObjectUnderTest().getEnumType());
		assertEquals(type.toString(), getObjectUnderTest().getType());
	}

	@Test
	public final void testSetType() {
		getObjectUnderTest().setType(type.toString());
		assertEquals(type.toString(), getObjectUnderTest().getType());
		assertEquals(type, getObjectUnderTest().getEnumType());
	}

	@Test
	public final void testGetMetricType() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), MetricType.getMetricType(type));
	}

	@Test
	public final void testGetMetricType_2() {
		super.testGetObjectProp_Null_1();
		assertNull(MetricType.getMetricType(type));
	}

	@Test
	public final void testFromActivators() {
		assertNull(MetricType.fromActivator(MetricType.class));
	}

	@Test
	public final void testFromActivator_2() {
		assertEquals(MetricType.Type.BUG, MetricType.fromActivator(Bug.class));
	}

	@Test
	public final void testFromActivator_3() {
		assertEquals(MetricType.Type.DEVELOPER,
				MetricType.fromActivator(Developer.class));
	}

	@Test
	public final void testFromActivator_4() {
		assertEquals(MetricType.Type.ENCAPSUNIT,
				MetricType.fromActivator(EncapsulationUnit.class));
	}

	@Test
	public final void testFromActivator_5() {
		assertEquals(MetricType.Type.EXECUNIT,
				MetricType.fromActivator(ExecutionUnit.class));
	}

	@Test
	public final void testFromActivator_6() {
		assertEquals(MetricType.Type.MAILING_LIST,
				MetricType.fromActivator(MailingList.class));
	}

	@Test
	public final void testFromActivator_7() {
		assertEquals(MetricType.Type.MAILMESSAGE,
				MetricType.fromActivator(MailMessage.class));
	}

	@Test
	public final void testFromActivator_8() {
		assertEquals(MetricType.Type.MAILTHREAD,
				MetricType.fromActivator(MailingListThread.class));
	}

	@Test
	public final void testFromActivator_9() {
		assertEquals(MetricType.Type.NAMESPACE,
				MetricType.fromActivator(NameSpace.class));
	}

	@Test
	public final void testFromActivator_10() {
		assertEquals(MetricType.Type.PROJECT,
				MetricType.fromActivator(StoredProject.class));
	}

	@Test
	public final void testFromActivator_11() {
		assertEquals(MetricType.Type.PROJECT_VERSION,
				MetricType.fromActivator(ProjectVersion.class));
	}

	@Test
	public final void testFromActivator_12() {
		assertEquals(MetricType.Type.SOURCE_DIRECTORY,
				MetricType.fromActivator(ProjectDirectory.class));
	}

	@Test
	public final void testFromActivator_13() {
		assertEquals(MetricType.Type.SOURCE_FILE,
				MetricType.fromActivator(ProjectFile.class));
	}

	@Test(expected = NullPointerException.class)
	public final void testToActivator() {
		getObjectUnderTest().setType("invalidType");
	}

	@Test
	public final void testToActivator_2() {
		MetricType.Type t = MetricType.Type.BUG;
		getObjectUnderTest().setEnumType(t);
		assertEquals(Bug.class, getObjectUnderTest().toActivator());
	}

	@Test
	public final void testToActivator_3() {
		MetricType.Type t = MetricType.Type.SOURCE_FILE;
		getObjectUnderTest().setEnumType(t);
		assertEquals(ProjectFile.class, getObjectUnderTest().toActivator());
	}

	@Test
	public final void testToActivator_4() {
		MetricType.Type t = MetricType.Type.DEVELOPER;
		getObjectUnderTest().setEnumType(t);
		assertEquals(Developer.class, getObjectUnderTest().toActivator());
	}

	@Test
	public final void testToActivator_5() {
		MetricType.Type t = MetricType.Type.ENCAPSUNIT;
		getObjectUnderTest().setEnumType(t);
		assertEquals(EncapsulationUnit.class, getObjectUnderTest()
				.toActivator());
	}

	@Test
	public final void testToActivator_6() {
		MetricType.Type t = MetricType.Type.EXECUNIT;
		getObjectUnderTest().setEnumType(t);
		assertEquals(ExecutionUnit.class, getObjectUnderTest().toActivator());
	}

	@Test
	public final void testToActivator_7() {
		MetricType.Type t = MetricType.Type.MAILING_LIST;
		getObjectUnderTest().setEnumType(t);
		assertEquals(MailingList.class, getObjectUnderTest().toActivator());
	}

	@Test
	public final void testToActivator_8() {
		MetricType.Type t = MetricType.Type.MAILMESSAGE;
		getObjectUnderTest().setEnumType(t);
		assertEquals(MailMessage.class, getObjectUnderTest().toActivator());
	}

	@Test
	public final void testToActivator_9() {
		MetricType.Type t = MetricType.Type.MAILTHREAD;
		getObjectUnderTest().setEnumType(t);
		assertEquals(MailingListThread.class, getObjectUnderTest()
				.toActivator());
	}

	@Test
	public final void testToActivator_10() {
		MetricType.Type t = MetricType.Type.NAMESPACE;
		getObjectUnderTest().setEnumType(t);
		assertEquals(NameSpace.class, getObjectUnderTest().toActivator());
	}

	@Test
	public final void testToActivator_11() {
		MetricType.Type t = MetricType.Type.PROJECT;
		getObjectUnderTest().setEnumType(t);
		assertEquals(StoredProject.class, getObjectUnderTest().toActivator());
	}

	@Test
	public final void testToActivator_12() {
		MetricType.Type t = MetricType.Type.PROJECT_VERSION;
		getObjectUnderTest().setEnumType(t);
		assertEquals(ProjectVersion.class, getObjectUnderTest().toActivator());
	}

	@Test
	public final void testToActivator_13() {
		MetricType.Type t = MetricType.Type.SOURCE_DIRECTORY;
		getObjectUnderTest().setEnumType(t);
		assertEquals(ProjectDirectory.class, getObjectUnderTest().toActivator());
	}
}
