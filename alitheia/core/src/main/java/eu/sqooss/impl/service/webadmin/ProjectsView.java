/*
 * This file is part of the Alitheia system, developed by the SQO-OSS
 * consortium as part of the IST FP6 SQO-OSS project, number 033331.
 *
 * Copyright 2007 - 2010 - Organization for Free and Open Source Software,  
 *                Athens, Greece.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *
 *     * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials provided
 *       with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package eu.sqooss.impl.service.webadmin;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.osgi.framework.BundleContext;

import eu.sqooss.core.AlitheiaCore;
import eu.sqooss.service.abstractmetric.AlitheiaPlugin;
import eu.sqooss.service.admin.AdminAction;
import eu.sqooss.service.admin.AdminService;
import eu.sqooss.service.admin.actions.AddProject;
import eu.sqooss.service.admin.actions.UpdateProject;
import eu.sqooss.service.db.Bug;
import eu.sqooss.service.db.ClusterNode;
import eu.sqooss.service.db.MailMessage;
import eu.sqooss.service.db.Project;
import eu.sqooss.service.db.ProjectVersion;
import eu.sqooss.service.db.StoredProject;
import eu.sqooss.service.pa.PluginInfo;
import eu.sqooss.service.scheduler.SchedulerException;
import eu.sqooss.service.updater.UpdaterService.UpdaterStage;

public class ProjectsView extends AbstractView {
	// Script for submitting this page
	private static String SUBMIT = "document.projects.submit();";

	// Action parameter's values
	private static String ACT_REQ_ADD_PROJECT = "reqAddProject";
	private static String ACT_CON_ADD_PROJECT = "conAddProject";
	private static String ACT_REQ_REM_PROJECT = "reqRemProject";
	private static String ACT_CON_REM_PROJECT = "conRemProject";
	private static String ACT_REQ_SHOW_PROJECT = "conShowProject";
	private static String ACT_CON_UPD_ALL = "conUpdateAll";
	private static String ACT_CON_UPD = "conUpdate";
	private static String ACT_CON_UPD_ALL_NODE = "conUpdateAllOnNode";

	// Servlet parameters
	private static String REQ_PAR_ACTION = "reqAction";
	private static String REQ_PAR_PROJECT_ID = "projectId";
	private static String REQ_PAR_PRJ_NAME = "projectName";
	private static String REQ_PAR_PRJ_WEB = "projectHomepage";
	private static String REQ_PAR_PRJ_CONT = "projectContact";
	private static String REQ_PAR_PRJ_BUG = "projectBL";
	private static String REQ_PAR_PRJ_MAIL = "projectML";
	private static String REQ_PAR_PRJ_CODE = "projectSCM";
	private static String REQ_PAR_SYNC_PLUGIN = "reqParSyncPlugin";
	private static String REQ_PAR_UPD = "reqUpd";

	/**
	 * Instantiates a new projects view.
	 *
	 * @param bundlecontext
	 *            the <code>BundleContext</code> object
	 * @param vc
	 *            the <code>VelocityContext</code> object
	 */
	public ProjectsView(BundleContext bundlecontext, VelocityContext vc,
			VelocityEngine ve) {
		super(bundlecontext, vc, ve);

		vc.put("SUBMIT", SUBMIT);

		// Action parameter's values
		vc.put("ACT_REQ_ADD_PROJECT", ACT_REQ_ADD_PROJECT);
		vc.put("ACT_CON_ADD_PROJECT", ACT_CON_ADD_PROJECT);
		vc.put("ACT_REQ_REM_PROJECT", ACT_REQ_REM_PROJECT);
		vc.put("ACT_CON_REM_PROJECT", ACT_CON_REM_PROJECT);
		vc.put("ACT_REQ_SHOW_PROJECT", ACT_REQ_SHOW_PROJECT);
		vc.put("ACT_CON_UPD_ALL", ACT_CON_UPD_ALL);
		vc.put("ACT_CON_UPD", ACT_CON_UPD);
		vc.put("ACT_CON_UPD_ALL_NODE", ACT_CON_UPD_ALL_NODE);

		// Servlet parameters
		vc.put("REQ_PAR_ACTION", REQ_PAR_ACTION);
		vc.put("REQ_PAR_PROJECT_ID", REQ_PAR_PROJECT_ID);
		vc.put("REQ_PAR_PRJ_NAME", REQ_PAR_PRJ_NAME);
		vc.put("REQ_PAR_PRJ_WEB", REQ_PAR_PRJ_WEB);
		vc.put("REQ_PAR_PRJ_CONT", REQ_PAR_PRJ_CONT);
		vc.put("REQ_PAR_PRJ_BUG", REQ_PAR_PRJ_BUG);
		vc.put("REQ_PAR_PRJ_MAIL", REQ_PAR_PRJ_MAIL);
		vc.put("REQ_PAR_PRJ_CODE", REQ_PAR_PRJ_CODE);
		vc.put("REQ_PAR_SYNC_PLUGIN", REQ_PAR_SYNC_PLUGIN);
		vc.put("REQ_PAR_UPD", REQ_PAR_UPD);
	}

	/**
	 * Renders the various project's views.
	 *
	 * @param req
	 *            the servlet's request object
	 *
	 * @return The HTML presentation of the generated view.
	 */
	public static String render(HttpServletRequest req) {
		// Stores the accumulated error messages
		StringBuilder e = new StringBuilder();
		// Indentation spacer
		int in = 6;

		// Initialize the resource bundles with the request's locale
		initResources(req.getLocale());

		// Request values
		String reqValAction = "";
		Long reqValProjectId = null;

		// Selected project
		StoredProject selProject = null;

		// ===============================================================
		// Parse the servlet's request object
		// ===============================================================
		if (req != null) {

			// Retrieve the selected editor's action (if any)
			reqValAction = req.getParameter(REQ_PAR_ACTION);

			// Retrieve the selected project's DAO (if any)
			reqValProjectId = fromString(req.getParameter(REQ_PAR_PROJECT_ID));
			if (reqValProjectId != null) {
				selProject = sobjDB.findObjectById(StoredProject.class,
						reqValProjectId);
			}

			if (reqValAction == null) {
				reqValAction = "";
			} else if (reqValAction.equals(ACT_CON_ADD_PROJECT)) {
				selProject = addProject(e, req, in);
			} else if (reqValAction.equals(ACT_CON_REM_PROJECT)) {
				selProject = removeProject(e, selProject, in);
			} else if (reqValAction.equals(ACT_CON_UPD)) {
				triggerUpdate(e, selProject, in, req.getParameter(REQ_PAR_UPD));
			} else if (reqValAction.equals(ACT_CON_UPD_ALL)) {
				triggerAllUpdate(e, selProject, in);
			} else if (reqValAction.equals(ACT_CON_UPD_ALL_NODE)) {
				triggerAllUpdateNode(e, selProject, in);
			} else {
				// Retrieve the selected plug-in's hash-code
				String reqValSyncPlugin = req.getParameter(REQ_PAR_SYNC_PLUGIN);
				syncPlugin(e, selProject, reqValSyncPlugin);
			}
		}
		String html = createFrom(e, selProject, reqValAction, in);
		return html;
	}

	private static StoredProject addProject(StringBuilder e,
			HttpServletRequest r, int indent) {
		AdminService as = AlitheiaCore.getInstance().getAdminService();
		AdminAction aa = as.create(AddProject.MNEMONIC);
		aa.addArg("scm", r.getParameter(REQ_PAR_PRJ_CODE));
		aa.addArg("name", r.getParameter(REQ_PAR_PRJ_NAME));
		aa.addArg("bts", r.getParameter(REQ_PAR_PRJ_BUG));
		aa.addArg("mail", r.getParameter(REQ_PAR_PRJ_MAIL));
		aa.addArg("web", r.getParameter(REQ_PAR_PRJ_WEB));
		as.execute(aa);

		if (aa.hasErrors()) {
			vc.put("RESULTS", aa.errors());
			return null;
		} else {
			vc.put("RESULTS", aa.results());
			return StoredProject.getProjectByName(r
					.getParameter(REQ_PAR_PRJ_NAME));
		}
	}

	// ---------------------------------------------------------------
	// Remove project
	// ---------------------------------------------------------------
	private static StoredProject removeProject(StringBuilder e,
			StoredProject selProject, int indent) {
		if (selProject != null) {
			// Deleting large projects in the foreground is
			// very slow
			ProjectDeleteJob pdj = new ProjectDeleteJob(sobjCore, selProject);
			try {
				sobjSched.enqueue(pdj);
			} catch (SchedulerException e1) {
				e.append(sp(indent)).append(getErr("e0034")).append("<br/>\n");
			}
			selProject = null;
		} else {
			e.append(sp(indent) + getErr("e0034") + "<br/>\n");
		}
		return selProject;
	}

	// ---------------------------------------------------------------
	// Trigger an update
	// ---------------------------------------------------------------
	private static void triggerUpdate(StringBuilder e,
			StoredProject selProject, int indent, String mnem) {
		AdminService as = AlitheiaCore.getInstance().getAdminService();
		AdminAction aa = as.create(UpdateProject.MNEMONIC);
		aa.addArg("project", selProject.getId());
		aa.addArg("updater", mnem);
		as.execute(aa);

		if (aa.hasErrors()) {
			vc.put("RESULTS", aa.errors());
		} else {
			vc.put("RESULTS", aa.results());
		}
	}

	// ---------------------------------------------------------------
	// Trigger update on all resources for that project
	// ---------------------------------------------------------------
	private static void triggerAllUpdate(StringBuilder e, Project selProject,
			int indent) {
		AdminService as = AlitheiaCore.getInstance().getAdminService();
		AdminAction aa = as.create(UpdateProject.MNEMONIC);
		aa.addArg("project", selProject.getId());
		as.execute(aa);

		if (aa.hasErrors()) {
			vc.put("RESULTS", aa.errors());
		} else {
			vc.put("RESULTS", aa.results());
		}
	}

	// ---------------------------------------------------------------
	// Trigger update on all resources on all projects of a node
	// ---------------------------------------------------------------
	private static void triggerAllUpdateNode(StringBuilder e,
			Project selProject, int in) {
		Set<Project> projectList = ClusterNode.thisNode().getProjects();

		for (Project project : projectList) {
			triggerAllUpdate(e, project, in);
		}
	}

	// ---------------------------------------------------------------
	// Trigger synchronize on the selected plug-in for that project
	// ---------------------------------------------------------------
	private static void syncPlugin(StringBuilder e, StoredProject selProject,
			String reqValSyncPlugin) {
		if ((reqValSyncPlugin != null) && (selProject != null)) {
			PluginInfo pInfo = sobjPA.getPluginInfo(reqValSyncPlugin);
			if (pInfo != null) {
				AlitheiaPlugin pObj = sobjPA.getPlugin(pInfo);
				if (pObj != null) {
					compMA.syncMetric(pObj, selProject);
					sobjLogger.debug("Syncronise plugin (" + pObj.getName()
							+ ") on project (" + selProject.getName() + ").");
				}
			}
		}
	}

	private static String createFrom(StringBuilder e, StoredProject selProject,
			String reqValAction, int in) {

		VelocityContext vc = AbstractView.vc;
		Template t = null;
		try {
			t = ve.getTemplate("projects-view.html");
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// ===============================================================
		// Create the form
		// ===============================================================
		vc.put("form_id", "projects");
		vc.put("form_name", "projects");
		vc.put("form_method", "post");
		vc.put("form_action", "/projects");

		// ===============================================================
		// Display the accumulated error messages (if any)
		// ===============================================================
		// Get the complete list of projects stored in the SQO-OSS framework
		Set<Project> projects = ClusterNode.thisNode().getProjects();
		Collection<PluginInfo> metrics = sobjPA.listPlugins();

		vc.put("reqValAction", reqValAction);
		vc.put("selProject", selProject);
		vc.put("metrics", metrics);

		// ===================================================================
		// "Show project info" view
		// ===================================================================
		if ((reqValAction.equals(ACT_REQ_SHOW_PROJECT)) && (selProject != null)) {
			Map<String, String> tableRows = new HashMap<>();
			tableRows.put("Project name", selProject.getName());
			tableRows.put("Homepage", selProject.getWebsiteUrl());
			tableRows.put("Contact e-mail", selProject.getContactUrl());
			tableRows.put("Bug database", selProject.getBtsUrl());
			tableRows.put("Mailing list", selProject.getMailUrl());
			tableRows.put("Source code", selProject.getScmUrl());
			vc.put("tableRows", tableRows);

			// ------------------------------------------------------------
			// Tool-bar
			// ------------------------------------------------------------
			vc.put("toolbar_button_value", getLbl("btn_back"));
			vc.put("toolbar_button_onclick", SUBMIT);
		}
		// ===================================================================
		// "Add project" editor
		// ===================================================================
		else if (reqValAction.equals(ACT_REQ_ADD_PROJECT)) {
			Map<String, String> normalInputRows = new HashMap<>();
			normalInputRows.put("Project name", REQ_PAR_PRJ_NAME);
			normalInputRows.put("Homepage", REQ_PAR_PRJ_WEB);
			normalInputRows.put("Contact e-mail", REQ_PAR_PRJ_CONT);
			normalInputRows.put("Bug database", REQ_PAR_PRJ_BUG);
			normalInputRows.put("Mailing list", REQ_PAR_PRJ_MAIL);
			normalInputRows.put("Source code", REQ_PAR_PRJ_CODE);
			vc.put("normalInputRows", normalInputRows);

			// ------------------------------------------------------------
			// Tool-bar
			// ------------------------------------------------------------
			vc.put("req-add-project-add-button-value", getLbl("project_add"));
		}
		// ===================================================================
		// "Delete project" confirmation view
		// ===================================================================
		else if ((reqValAction.equals(ACT_REQ_REM_PROJECT))
				&& (selProject != null)) {
			vc.put("req-rem-project-legend-label", getLbl("l0059"));
			vc.put("req-rem-project-delete-project-message",
					getMsg("delete_project"));

			// ------------------------------------------------------------
			// Tool-bar
			// ------------------------------------------------------------
			vc.put("req-rem-project-button-value", getLbl("l0006"));
			vc.put("req-rem-project-cancel-button-value", getLbl("l0004"));

		}
		// ===================================================================
		// Projects list view
		// ===================================================================
		else {
			vc.put("default-table-header-1", getLbl("l0066"));
			vc.put("default-table-header-2", getLbl("l0067"));
			vc.put("default-table-header-3", getLbl("l0068"));
			vc.put("default-table-header-4", getLbl("l0069"));
			vc.put("default-table-header-5", getLbl("l0070"));
			vc.put("default-table-header-6", getLbl("l0071"));
			vc.put("default-table-header-7", getLbl("l0073"));

			vc.put("default-projects", projects);
			vc.put("default-no-projects-message", getMsg("no_projects"));

			if (!projects.isEmpty()) {
				// ------------------------------------------------------------
				// Create the content rows
				// ------------------------------------------------------------
				for (Project nextPrj : projects) {
					boolean selected = false;
					if ((selProject != null)
							&& (selProject.getId() == nextPrj.getId())) {
						selected = true;
					}

					vc.put("default-selected-button-value", getLbl("btn_info"));

					// Last project version
					String lastVersion = getLbl("l0051");
					ProjectVersion v = ProjectVersion
							.getLastProjectVersion(nextPrj);
					if (v != null) {
						lastVersion = String.valueOf(v.getSequence()) + "("
								+ v.getRevisionId() + ")";
					}

					vc.put("default-last-version-message", lastVersion);

					// Date of the last known email
					MailMessage mm = MailMessage.getLatestMailMessage(nextPrj);
					String lastMailMessage = (String) ((mm == null) ? getLbl("l0051")
							: mm.getSendDate().toString());

					vc.put("default-last-mail-message", lastMailMessage);

					// ID of the last known bug entry
					Bug bug = Bug.getLastUpdate(nextPrj);

					String lastBugEntry = ((bug == null) ? getLbl("l0051")
							: bug.getBugID());
					vc.put("default-last-bug-entry", lastBugEntry);

					// Evaluation state
					String evalState = getLbl("project_not_evaluated");
					if (nextPrj.isEvaluated()) {
						evalState = getLbl("project_is_evaluated");
					}

					String evaluationState = evalState;
					vc.put("default-evaluation-state", evaluationState);

					// Cluster node
					String nodename = null;
					if (null != nextPrj.getClusternode()) {
						nodename = nextPrj.getClusternode().getName();
					} else {
						nodename = "(local)";
					}

					vc.put("default-cluster-node", nodename);

					if ((selected) && (metrics.isEmpty() == false)) {
						showLastAppliedVersion(nextPrj, metrics, vc);
					}
				}
			}
			// ----------------------------------------------------------------
			// Tool-bar
			// ----------------------------------------------------------------
			addToolBar(selProject, in, vc);
		}

		StringWriter writer = new StringWriter();
		try {
			t.merge(vc, writer);
		} catch (ResourceNotFoundException | ParseErrorException
				| MethodInvocationException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int index = writer.toString().indexOf("$");
		if (index >= 0) {
			System.out.println("Warning, $ found at index " + index + ".");
		}
		return writer.toString();
	}

	private static void addToolBar(StoredProject selProject, long in,
			VelocityContext vc) {
		vc.put("toolbar-button-value", getLbl("l0008"));
		vc.put("toolbar-button-window-location", ((selProject != null) ? "?"
				+ REQ_PAR_PROJECT_ID + "=" + selProject.getId() : ""));
		vc.put("toolbar-act-req-add-button-value", getLbl("add_project"));
		vc.put("toolbar-act-req-rem-button-value", getLbl("l0059"));
		vc.put("toolbar-button-disable", ((selProject != null) ? ""
				: " disabled"));

		vc.put("toolbar-select-disable", ((selProject != null) ? ""
				: " disabled=\"disabled\""));

		if (selProject != null) {
			vc.put("toolbar-updaters-import",
					sobjUpdater.getUpdaters(selProject, UpdaterStage.IMPORT));
			vc.put("toolbar-updaters-parse",
					sobjUpdater.getUpdaters(selProject, UpdaterStage.PARSE));
			vc.put("toolbar-updaters-inference",
					sobjUpdater.getUpdaters(selProject, UpdaterStage.INFERENCE));
			vc.put("toolbar-updaters-default",
					sobjUpdater.getUpdaters(selProject, UpdaterStage.DEFAULT));
		}

		vc.put("sobjClusterNode", sobjClusterNode);
	}

	private static void showLastAppliedVersion(Project project,
			Collection<PluginInfo> metrics, VelocityContext vc) {
		List<PluginInfo> installedMetrics = new ArrayList<>();
		vc.put("default-installed-metrics", installedMetrics);

		for (PluginInfo m : metrics) {
			if (m.installed) {
				installedMetrics.add(m);
			}
		}
	}
}

// vi: ai nosi sw=4 ts=4 expandtab

