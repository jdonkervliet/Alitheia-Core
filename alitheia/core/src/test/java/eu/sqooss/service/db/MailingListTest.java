package eu.sqooss.service.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class MailingListTest extends DBTestSuperDoHQL3<MailingList> {

	@Mock StoredProject p;
	@Mock MailMessage mm;
	@Mock MailingListThread mlt;
	
	@Before
	public void setUp() throws Exception {
		super.setUp(new MailingList());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testSetListId() {
		String listId = "hallo";
		getObjectUnderTest().setListId(listId);
		assertEquals(listId, getObjectUnderTest().getListId());
	}

	@Test
	public final void testSetStoredProject() {
		getObjectUnderTest().setStoredProject(p);
		assertEquals(p, getObjectUnderTest().getStoredProject());
	}

	@Test
	public final void testSetMessages() {
		Set<MailMessage> messages = new HashSet<MailMessage>();
		getObjectUnderTest().setMessages(messages);
		assertEquals(messages, getObjectUnderTest().getMessages());
	}

	@Test
	public final void testSetThreads() {
		Set<MailingListThread> threads = new HashSet<MailingListThread>();
		getObjectUnderTest().setThreads(threads);
		assertEquals(threads, getObjectUnderTest().getThreads());
	}

	@Test
	public final void testGetMessagesNewerThan() {
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(null);
		int size = getObjectUnderTest().getMessagesNewerThan(new Date()).size();
		assertEquals(size, 0);
	}
	
	@Test
	public final void testGetMessagesNewerThan_2() {
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(new ArrayList<MailMessage>());
		int size = getObjectUnderTest().getMessagesNewerThan(new Date()).size();
		assertEquals(size, 0);
	}
	
	@Test
	public final void testGetMessagesNewerThan_3() {
		List<MailMessage> list = new ArrayList<MailMessage>();
		list.add(mm);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		List<MailMessage> gottenList = getObjectUnderTest().getMessagesNewerThan(new Date());
		int size = gottenList.size();
		assertEquals(size, 1);
		assertEquals(list, gottenList);
	}

	@Test
	public final void testGetLatestEmail() {
		List<MailMessage> list = new ArrayList<MailMessage>();
		list.add(mm);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertEquals(mm, getObjectUnderTest().getLatestEmail());
	}
	
	@Test
	public final void testGetLatestEmail_2() {
		List<MailMessage> list = new ArrayList<MailMessage>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertNull(getObjectUnderTest().getLatestEmail());
	}

	@Test
	public final void testGetLatestThread() {
		List<MailingListThread> list = new ArrayList<MailingListThread>();
		list.add(mlt);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertEquals(mlt, getObjectUnderTest().getLatestThread());
	}
	
	@Test
	public final void testGetLatestThread_2() {
		List<MailingListThread> list = new ArrayList<MailingListThread>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertNull(getObjectUnderTest().getLatestThread());
	}

	@Test
	public final void testToString() {
		String id = "id of the list";
		getObjectUnderTest().setListId(id);
		getObjectUnderTest().setStoredProject(p);
		assertTrue(getObjectUnderTest().toString().contains(id));
	}

}
