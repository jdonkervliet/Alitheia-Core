/**
 * 
 */
package eu.sqooss.impl.service.webadmin;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.osgi.framework.BundleContext;

import eu.sqooss.core.AlitheiaCore;
import eu.sqooss.service.cluster.ClusterNodeService;
import eu.sqooss.service.db.ClusterNode;
import eu.sqooss.service.db.DAObject;
import eu.sqooss.service.db.DBService;
import eu.sqooss.service.db.StoredProject;
import eu.sqooss.service.logging.Logger;
import eu.sqooss.service.pa.PluginAdmin;
import eu.sqooss.service.updater.Updater;
import eu.sqooss.service.updater.UpdaterService;
import eu.sqooss.service.updater.UpdaterService.UpdaterStage;

/**
 * @author jesse
 *
 */
public class ProjectsViewTest {

	@Mock
	BundleContext bundleContext;
	VelocityContext velocityContext;
	VelocityEngine velocityEngine;

	@Mock
	HttpServletRequest req;
	@Mock
	AlitheiaCore alitheiaCore;
	@Mock
	DBService dbService;

	List<ClusterNode> clusterNodeList;
	@Mock
	ClusterNode clusterNode;

	@Mock
	PluginAdmin pluginAdmin;
	@Mock
	ClusterNodeService clusterNodeService;

	Set<StoredProject> storedProjectSet;
	@Mock
	StoredProject storedProject;

	@Mock
	UpdaterService updaterService;
	@Mock
	Updater updater;

	/**
	 * @throws java.lang.Exception
	 */
	@SuppressWarnings({ "unchecked" })
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		Mockito.doReturn(new Locale("ENGLISH")).when(req).getLocale();

		Mockito.doReturn(dbService).when(alitheiaCore).getDBService();
		Mockito.doReturn(pluginAdmin).when(alitheiaCore).getPluginAdmin();
		Mockito.doReturn(clusterNodeService).when(alitheiaCore)
				.getClusterNodeService();
		Mockito.doReturn(updaterService).when(alitheiaCore).getUpdater();

		clusterNodeList = new ArrayList<ClusterNode>();
		clusterNodeList.add(clusterNode);
		Mockito.doReturn(clusterNodeList)
				.when(dbService)
				.findObjectsByProperties((Class<DAObject>) Mockito.any(),
						Mockito.anyMap());

		storedProjectSet = new HashSet<StoredProject>();
		storedProjectSet.add(storedProject);

		AlitheiaCore.setTestInstance(alitheiaCore);
		initVelocity();
		velocityContext = new VelocityContext();
		new ProjectsView(bundleContext, velocityContext, velocityEngine);
	}

	/*
	 * The utility method used for the initialization of the velocity engine.
	 */
	private void initVelocity() {
		velocityEngine = new VelocityEngine();
		velocityEngine.setProperty("runtime.log.logsystem.class",
				"org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
		velocityEngine.setProperty("runtime.log.logsystem.log4j.category",
				Logger.NAME_SQOOSS_WEBADMIN);
		String resourceLoader = "classpath";
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER,
				resourceLoader);
		velocityEngine
				.setProperty(resourceLoader + "."
						+ RuntimeConstants.RESOURCE_LOADER + ".class",
						"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.impl.service.webadmin.ProjectsView#render(javax.servlet.http.HttpServletRequest)}
	 * . Most basic case.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testRenderBasic() throws IOException {

		String html = Jsoup.parse(ProjectsView.render(req)).toString();

		File input = new File(
				"src/test/resources/eu/sqooss/impl/service/webadmin/html-ref-all-null.html");
		String referenceHtml = Jsoup.parse(input, "UTF-8", "http://localhost/")
				.toString();

		System.out.println(html);
		System.out.println("@@@");
		System.out.println(referenceHtml);

		assertEquals(referenceHtml.replaceAll("(?m) +$", ""),
				html.replaceAll("(?m) +$", ""));
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.impl.service.webadmin.ProjectsView#render(javax.servlet.http.HttpServletRequest)}
	 * . With form of ACT_REQ_SHOW_PROJECT.
	 * 
	 * @throws Exception
	 * @throws ParseErrorException
	 * @throws ResourceNotFoundException
	 */
	@Test
	public void testRenderWithFormActReqShowProject()
			throws ResourceNotFoundException, ParseErrorException, Exception {
		Mockito.doReturn("conShowProject").when(req)
				.getParameter(Mockito.anyString());
		Mockito.doReturn("42").when(req).getParameter("projectId");
		Mockito.doReturn(storedProject)
				.when(dbService)
				.findObjectById((Class<DAObject>) Mockito.any(),
						Mockito.anyLong());

		String html = Jsoup.parse(ProjectsView.render(req)).toString();

		File input = new File(
				"src/test/resources/eu/sqooss/impl/service/webadmin/html-ref-form-act-req-show-project.html");
		String referenceHtml = Jsoup.parse(input, "UTF-8", "http://localhost/")
				.toString();

		System.out.println(html);
		System.out.println("@@@");
		System.out.println(referenceHtml);

		assertEquals(referenceHtml.replaceAll("(?m) +$", ""),
				html.replaceAll("(?m) +$", ""));
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.impl.service.webadmin.ProjectsView#render(javax.servlet.http.HttpServletRequest)}
	 * . With form of ACT_REQ_SHOW_PROJECT.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testRenderWithFormActReqAddProject() throws IOException {
		Mockito.doReturn("reqAddProject").when(req)
				.getParameter(Mockito.anyString());
		Mockito.doReturn("42").when(req).getParameter("projectId");
		Mockito.doReturn(storedProject)
				.when(dbService)
				.findObjectById((Class<DAObject>) Mockito.any(),
						Mockito.anyLong());

		String html = Jsoup.parse(ProjectsView.render(req)).toString();

		File input = new File(
				"src/test/resources/eu/sqooss/impl/service/webadmin/html-ref-form-act-req-add-project.html");
		String referenceHtml = Jsoup.parse(input, "UTF-8", "http://localhost/")
				.toString();
		
		System.out.println(html);
		System.out.println("@@@");
		System.out.println(referenceHtml);

		assertEquals(referenceHtml.replaceAll("(?m) +$", ""),
				html.replaceAll("(?m) +$", ""));
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.impl.service.webadmin.ProjectsView#render(javax.servlet.http.HttpServletRequest)}
	 * . With form of ACT_REQ_REM_PROJECT.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testRenderWithFormActReqRemProject() throws IOException {
		Mockito.doReturn("reqRemProject").when(req)
				.getParameter(Mockito.anyString());
		Mockito.doReturn("42").when(req).getParameter("projectId");
		Mockito.doReturn(storedProject)
				.when(dbService)
				.findObjectById((Class<DAObject>) Mockito.any(),
						Mockito.anyLong());

		String html = Jsoup.parse(ProjectsView.render(req)).toString();

		File input = new File(
				"src/test/resources/eu/sqooss/impl/service/webadmin/html-ref-form-act-req-rem-project.html");
		String referenceHtml = Jsoup.parse(input, "UTF-8", "http://localhost/")
				.toString();
		
		System.out.println(html);
		System.out.println("@@@");
		System.out.println(referenceHtml);

		assertEquals(referenceHtml.replaceAll("(?m) +$", ""),
				html.replaceAll("(?m) +$", ""));
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.impl.service.webadmin.ProjectsView#render(javax.servlet.http.HttpServletRequest)}
	 * . With projects.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testRenderFormWithProjects() throws IOException {
		Mockito.doReturn(storedProjectSet).when(clusterNode).getProjects();

		String html = Jsoup.parse(ProjectsView.render(req)).toString();

		File input = new File(
				"src/test/resources/eu/sqooss/impl/service/webadmin/html-ref-form-projects.html");
		String referenceHtml = Jsoup.parse(input, "UTF-8", "http://localhost/")
				.toString();

		System.out.println(html);
		System.out.println("@@@");
		System.out.println(referenceHtml);

		assertEquals(referenceHtml.replaceAll("(?m) +$", ""),
				html.replaceAll("(?m) +$", ""));
	}

	/**
	 * Test method for
	 * {@link eu.sqooss.impl.service.webadmin.ProjectsView#render(javax.servlet.http.HttpServletRequest)}
	 * . With projects in toolbar.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testRenderWithProjectsInToolbar() throws IOException {
		Mockito.doReturn("bla").when(req).getParameter(Mockito.anyString());
		Mockito.doReturn("42").when(req).getParameter("projectId");
		Mockito.doReturn(storedProject)
				.when(dbService)
				.findObjectById((Class<DAObject>) Mockito.any(),
						Mockito.anyLong());

		Set<Updater> updaterSet = new HashSet<Updater>();
		updaterSet.add(updater);

		Mockito.doReturn(updaterSet)
				.when(updaterService)
				.getUpdaters(Mockito.any(StoredProject.class),
						Mockito.any(UpdaterStage.class));

		String html = Jsoup.parse(ProjectsView.render(req)).toString();

		File input = new File(
				"src/test/resources/eu/sqooss/impl/service/webadmin/html-ref-projects-in-toolbar.html");
		String referenceHtml = Jsoup.parse(input, "UTF-8", "http://localhost/")
				.toString();

		System.out.println(html);
		System.out.println("@@@");
		System.out.println(referenceHtml);

		assertEquals(referenceHtml.replaceAll("(?m) +$", ""),
				html.replaceAll("(?m) +$", ""));
	}
}
