package eu.sqooss.impl.service.webadmin;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import eu.sqooss.core.AlitheiaCore;
import eu.sqooss.service.abstractmetric.AlitheiaPlugin;
import eu.sqooss.service.db.DAObject;
import eu.sqooss.service.db.DBService;
import eu.sqooss.service.db.Plugin;
import eu.sqooss.service.pa.PluginAdmin;
import eu.sqooss.service.pa.PluginInfo;

public class PluginsViewTest {

	@Mock
	private AlitheiaCore alitheiaCore;
	@Mock
	private BundleContext bundleContext;
	@Mock
	private VelocityContext velocityContext;
	@Mock
	private HttpServletRequest req;
	@Mock
	private PluginAdmin pluginAdmin;
	@Mock
	PluginInfo pluginInfo;
	Collection<PluginInfo> pluginCollection;
	@Mock
	private ServiceReference serviceReference;
	@Mock
	private AlitheiaPlugin alitheiaPlugin;
	@Mock
	private DBService dbService;
	@Mock
	private Plugin plugin;

	@Before
	public void setUp() {
		pluginCollection = new ArrayList<PluginInfo>();

		MockitoAnnotations.initMocks(this);
		Mockito.doReturn(new Locale("ENGLISH")).when(req).getLocale();
		Mockito.doReturn(pluginAdmin).when(alitheiaCore).getPluginAdmin();
		Mockito.doReturn(pluginCollection).when(pluginAdmin).listPlugins();

		AlitheiaCore.setTestInstance(alitheiaCore);

		new ProjectsView(bundleContext, velocityContext, null);
	}

	@Test
	public void testRenderBasic() throws IOException {
		String refhtml = Jsoup
				.parse(new File(
						"src/test/resources/eu/sqooss/impl/service/webadmin/pluginsview-render-basic.html"),
						null).toString().replaceAll("(?m) +$", "");

		String html = Jsoup.parse(PluginsView.render(req)).toString()
				.replaceAll("(?m) +$", "");

		assertEquals(refhtml, html);
	}

	@Test
	public void testRenderSinglePlugin() throws IOException {
		pluginCollection.add(pluginInfo);

		Mockito.doReturn(serviceReference).when(pluginInfo).getServiceRef();

		String refhtml = Jsoup
				.parse(new File(
						"src/test/resources/eu/sqooss/impl/service/webadmin/pluginsview-render-single-plugin.html"),
						null).toString().replaceAll("(?m) +$", "");

		String html = Jsoup.parse(PluginsView.render(req)).toString()
				.replaceAll("(?m) +$", "");

		assertEquals(refhtml, html);
	}

	@Test
	public void testRenderSinglePluginWithInfoObject() throws IOException {
		pluginCollection.add(pluginInfo);

		Mockito.doReturn("This-is-a-nice-parameter").when(req)
				.getParameter(Mockito.anyString());
		Mockito.doReturn(serviceReference).when(pluginInfo).getServiceRef();
		Mockito.doReturn(pluginInfo).when(pluginAdmin)
				.getPluginInfo(Mockito.anyString());

		String refhtml = Jsoup
				.parse(new File(
						"src/test/resources/eu/sqooss/impl/service/webadmin/pluginsview-render-single-plugin-with-info-object.html"),
						null).toString().replaceAll("(?m) +$", "");

		String html = Jsoup.parse(PluginsView.render(req)).toString()
				.replaceAll("(?m) +$", "");

		assertEquals(refhtml, html);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRenderSinglePluginWithInfoObjectIsInstalled()
			throws IOException {
		pluginCollection.add(pluginInfo);

		Mockito.doReturn("This-is-a-nice-parameter").when(req)
				.getParameter(Mockito.anyString());
		Mockito.doReturn(serviceReference).when(pluginInfo).getServiceRef();
		Mockito.doReturn(pluginInfo).when(pluginAdmin)
				.getPluginInfo(Mockito.anyString());
		pluginInfo.installed = true;
		Mockito.doReturn(alitheiaPlugin).when(pluginAdmin)
				.getPlugin(Mockito.any(PluginInfo.class));
		Mockito.doReturn(dbService).when(alitheiaCore).getDBService();
		List<Plugin> plugins = new ArrayList<Plugin>();
		plugins.add(plugin);
		Mockito.doReturn(plugins)
				.when(dbService)
				.findObjectsByProperties((Class<DAObject>) Mockito.any(),
						Mockito.anyMap());

		String refhtml = Jsoup
				.parse(new File(
						"src/test/resources/eu/sqooss/impl/service/webadmin/pluginsview-render-single-plugin-with-info-object-is-installed.html"),
						null).toString().replaceAll("(?m) +$", "");

		String html = Jsoup.parse(PluginsView.render(req)).toString()
				.replaceAll("(?m) +$", "");

		assertEquals(refhtml, html);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRenderSinglePluginWithInfoObjectIsInstalledCreateProperty()
			throws IOException {
		pluginCollection.add(pluginInfo);

		Mockito.doReturn("createProperty").when(req)
				.getParameter(Mockito.anyString());
		Mockito.doReturn(serviceReference).when(pluginInfo).getServiceRef();
		Mockito.doReturn(pluginInfo).when(pluginAdmin)
				.getPluginInfo(Mockito.anyString());
		pluginInfo.installed = true;
		Mockito.doReturn(alitheiaPlugin).when(pluginAdmin)
				.getPlugin(Mockito.any(PluginInfo.class));
		Mockito.doReturn(dbService).when(alitheiaCore).getDBService();
		List<Plugin> plugins = new ArrayList<Plugin>();
		plugins.add(plugin);
		Mockito.doReturn(plugins)
				.when(dbService)
				.findObjectsByProperties((Class<DAObject>) Mockito.any(),
						Mockito.anyMap());

		String refhtml = Jsoup
				.parse(new File(
						"src/test/resources/eu/sqooss/impl/service/webadmin/pluginsview-render-single-plugin-with-info-object-is-installed-create-property.html"),
						null).toString().replaceAll("(?m) +$", "");

		String html = Jsoup.parse(PluginsView.render(req)).toString()
				.replaceAll("(?m) +$", "");

		assertEquals(refhtml, html);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRenderSinglePluginWithInfoObjectIsInstalledConfirmProperty()
			throws IOException {
		pluginCollection.add(pluginInfo);

		Mockito.doReturn("confirmProperty").when(req)
				.getParameter(Mockito.anyString());
		Mockito.doReturn(serviceReference).when(pluginInfo).getServiceRef();
		Mockito.doReturn(pluginInfo).when(pluginAdmin)
				.getPluginInfo(Mockito.anyString());
		pluginInfo.installed = true;
		Mockito.doReturn(alitheiaPlugin).when(pluginAdmin)
				.getPlugin(Mockito.any(PluginInfo.class));
		Mockito.doReturn(dbService).when(alitheiaCore).getDBService();
		List<Plugin> plugins = new ArrayList<Plugin>();
		plugins.add(plugin);
		Mockito.doReturn(plugins)
				.when(dbService)
				.findObjectsByProperties((Class<DAObject>) Mockito.any(),
						Mockito.anyMap());
		Mockito.doReturn(true).when(pluginInfo)
				.hasConfProp(Mockito.anyString(), Mockito.anyString());

		String refhtml = Jsoup
				.parse(new File(
						"src/test/resources/eu/sqooss/impl/service/webadmin/pluginsview-render-single-plugin-with-info-object-is-installed-confirm-property.html"),
						null).toString().replaceAll("(?m) +$", "");

		String html = Jsoup.parse(PluginsView.render(req)).toString()
				.replaceAll("(?m) +$", "");

		assertEquals(refhtml, html);
	}
}
