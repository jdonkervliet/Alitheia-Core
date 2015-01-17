package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class MailMessageTest extends DBTestSuperObjectProp<MailMessage> {

	@Mock MailingListThread thread;
	@Mock Developer dev;
	@Mock MailingList ml;
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new MailMessage());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testSetThread() {
		getObjectUnderTest().setThread(thread);
		assertEquals(thread, getObjectUnderTest().getThread());
	}

	@Test
	public final void testSetFileName() {
		String fileName = "/path/to/file";
		getObjectUnderTest().setFileName(fileName);
		assertEquals(fileName, getObjectUnderTest().getFileName());
	}

	@Test
	public final void testSetDepth() {
		int depth = 1238901;
		getObjectUnderTest().setDepth(depth);
		assertEquals(depth, getObjectUnderTest().getDepth());
	}

	@Test
	public final void testSetSender() {
		getObjectUnderTest().setSender(dev);
		assertEquals(dev, getObjectUnderTest().getSender());
	}

	@Test
	public final void testSetList() {
		getObjectUnderTest().setList(ml);
		assertEquals(ml, getObjectUnderTest().getList());
	}

	@Test
	public final void testSetMessageId() {
		String value = "thisIsNotAMsgId";
		getObjectUnderTest().setMessageId(value);
		assertEquals(value, getObjectUnderTest().getMessageId());
	}

	@Test
	public final void testSetSendDate() {
		Date dt = new Date();
		getObjectUnderTest().setSendDate(dt);
		assertEquals(dt, getObjectUnderTest().getSendDate());
	}

	@Test
	public final void testSetSubject() {
		String value = "myValueHere";
		getObjectUnderTest().setSubject(value);
		assertEquals(value, getObjectUnderTest().getSubject());
	}

	@Test
	public final void testSetFilename() {
		String fileName = "/path/to/file";
		getObjectUnderTest().setFilename(fileName);
		assertEquals(fileName, getObjectUnderTest().getFilename());
	}

	@Test
	public final void testSetMeasurements() {
		Set<MailMessageMeasurement> measurements = new HashSet<MailMessageMeasurement>();
		getObjectUnderTest().setMeasurements(measurements);
		assertEquals(measurements, getObjectUnderTest().getMeasurements());
	}
	
	@Test
	public final void testSetParent() {
		getObjectUnderTest().setParent(getObjectUnderTest());
		assertEquals(getObjectUnderTest(), getObjectUnderTest().getParent());
	}

	@Test
	public final void testGetMessageById() {
		super.testGetObjectProp_Null_1();
		assertNull(MailMessage.getMessageById(""));
	}
	
	@Test
	public final void testGetMessageById_2() {
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(null);
		assertNull(MailMessage.getMessageById(""));
	}
	
	@Test
	public final void testGetMessageById_3() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), MailMessage.getMessageById(""));
	}
	
	@Test
	public final void testGetMessageByFilename() {
		super.testGetObjectProp_Null_1();
		assertNull(MailMessage.getMessageByFileName(""));
	}
	
	@Test
	public final void testGetMessageByFilename_2() {
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(null);
		assertNull(MailMessage.getMessageByFileName(""));
	}
	
	@Test
	public final void testGetMessageByFilename_3() {
		super.testGetObjectProp_Equals_1();
		assertEquals(getObjectUnderTest(), MailMessage.getMessageByFileName(""));
	}

	@Test
	public final void testGetLatestMailMessage() {
		List<MailMessage> list = new ArrayList<MailMessage>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertNull(MailMessage.getLatestMailMessage(null));
	}
	
	@Test
	public final void testGetLatestMailMessage_2() {
		List<MailMessage> list = new ArrayList<MailMessage>();
		list.add(getObjectUnderTest());
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertEquals(getObjectUnderTest(), MailMessage.getLatestMailMessage(null));
	}

	@Test
	public final void testToString() {
		Date date = new Date();
		String subject = "This is a test string";
		getObjectUnderTest().setSendDate(date);
		getObjectUnderTest().setSender(dev);
		getObjectUnderTest().setSubject(subject);
		String res = getObjectUnderTest().toString();
		assertTrue(res.contains(date.toString()));
		assertTrue(res.contains(dev.toString()));
		assertTrue(res.contains(subject));
	}

}
