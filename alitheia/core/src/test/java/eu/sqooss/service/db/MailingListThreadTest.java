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

public class MailingListThreadTest extends DBTestSuper<MailingListThread> {

	@Mock
	MailingList l;
	@Mock
	MailMessage mm;

	@Before
	public void setUp() throws Exception {
		super.setUp(new MailingListThread());
	}

	@Test
	public final void testSetId() {
		super.testSetId();
	}

	@Test
	public final void testSetMessages() {
		Set<IMailMessage> messages = new HashSet<IMailMessage>();
		getObjectUnderTest().setMessages(messages);
		assertEquals(messages, getObjectUnderTest().getMessages());
	}

	@Test
	public final void testMailingListThreadMailingListDate() {
		Date d = new Date();
		MailingListThread mlt = new MailingListThread(l, d);
		assertEquals(l, mlt.getList());
		assertEquals(d, mlt.getLastUpdated());
	}

	@Test
	public final void testSetList() {
		getObjectUnderTest().setList(l);
		assertEquals(l, getObjectUnderTest().getList());
	}

	@Test
	public final void testSetIsFlameWar() {
		getObjectUnderTest().setIsFlameWar(true);
		assertTrue(getObjectUnderTest().getIsFlameWar());
	}

	@Test
	public final void testSetFlameWar() {
		getObjectUnderTest().setFlameWar(true);
		assertTrue(getObjectUnderTest().getIsFlameWar());
	}

	@Test
	public final void testSetLastUpdated() {
		Date lastUpdated = new Date();
		getObjectUnderTest().setLastUpdated(lastUpdated);
		assertEquals(lastUpdated, getObjectUnderTest().getLastUpdated());
	}

	@Test
	public final void testGetStartingEmail() {
		List<MailMessage> list = new ArrayList<MailMessage>();
		super.setUpMockedAlitheiaCoreAndDbServiceForObjectsByProp(list);
		assertNull(getObjectUnderTest().getStartingEmail());
	}

	@Test
	public final void testGetStartingEmail_2() {
		List<MailMessage> list = new ArrayList<MailMessage>();
		list.add(mm);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertEquals(mm, getObjectUnderTest().getStartingEmail());
	}

	@Test
	public final void testGetMessagesByArrivalOrder() {
		List<MailMessage> list = new ArrayList<MailMessage>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(0, getObjectUnderTest().getMessagesByArrivalOrder().size());
	}

	@Test
	public final void testGetMessagesByArrivalOrder_2() {
		List<MailMessage> list = null;
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(0, getObjectUnderTest().getMessagesByArrivalOrder().size());
	}

	@Test
	public final void testGetMessagesByArrivalOrder_3() {
		List<MailMessage> list = new ArrayList<MailMessage>();
		list.add(mm);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(list, getObjectUnderTest().getMessagesByArrivalOrder());
	}

	@Test
	public final void testGetThreadDepth() {
		List<MailMessage> list = new ArrayList<MailMessage>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertEquals(0, getObjectUnderTest().getThreadDepth());
	}

	@Test
	public final void testGetThreadDepth_2() {
		List<MailMessage> list = null;
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertEquals(0, getObjectUnderTest().getThreadDepth());
	}

	@Test
	public final void testGetThreadDepth_3() {
		int res = 192;
		List<Integer> list = new ArrayList<Integer>();
		list.add(new Integer(res));
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL3(list);
		assertEquals(res, getObjectUnderTest().getThreadDepth());
	}

	@Test
	public final void testGetMessagesAtLevel() {
		List<MailMessage> list = new ArrayList<MailMessage>();
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(0, getObjectUnderTest().getMessagesAtLevel(0).size());
	}

	@Test
	public final void testGetMessagesAtLevel_2() {
		List<MailMessage> list = null;
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(0, getObjectUnderTest().getMessagesAtLevel(0).size());
	}

	@Test
	public final void testGetMessagesAtLevel_3() {
		List<MailMessage> list = new ArrayList<MailMessage>();
		list.add(mm);
		super.setUpMockedAlitheiaCoreAndDbServiceForDoHQL2(list);
		assertEquals(list, getObjectUnderTest().getMessagesAtLevel(0));
	}

}
