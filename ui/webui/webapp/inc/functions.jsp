<%!

/**
 * Generates a string that contains a <b>2*num</b> spaces.
 * <br/>
 * <i>Used for indentation of the HTML content that is generated by the
 * various views.</i>
 * 
 * @param num the indentation depth
 * 
 * @return The indentation string.
 */
protected static String sp (long num) {
    StringBuilder b = new StringBuilder();
    for (long i = 0; i < num; i++)
        b.append("  ");
    return b.toString();
}

public static String error(String e, long in) {
    return (sp(in)
        + "<p><font color=\"red\">\n"
        + e
        + sp(in) + "</font></p>");
}

public static String versionSelector(Project p, long in) {
    StringBuilder html = new StringBuilder("");
    html.append(sp(in++) + "<form id=\"selectversion\">\n");
    html.append(sp(in) + "<input type=\"text\" class=\"form\""
        + " name=\"version" + p.getId() + "\""
        + " value=\"\"/>\n");
    html.append(sp(in) + "<input type=\"submit\" class=\"form\""
        + " value=\"Apply\"/>\n");
    html.append(sp(--in) + "</form>\n");
    // Shortcuts
    html.append(sp(in) + "<br/><strong>Jump to:</strong>\n");
    html.append(sp(in++) + "<ul>\n");
    html.append(sp(in) + "<li><a href=\"?version" + p.getId()
        + "=" + p.getFirstVersion().getNumber() + "\">"
        + "First version</a>\n");
    if (p.getVersionsCount() > 1)
        html.append(sp(in) + "<li><a href=\"?version" + p.getId()
            + "=" + p.getLastVersion().getNumber() + "\">"
            + "Last version</a>\n");
    html.append(sp(--in) + "</ul>\n");
    return html.toString();
}

public static Long strToLong(String strVal) {
    try {
        return new Long(strVal);
    }
    catch (NumberFormatException e) {
        return null;
    }
}

public static Long getId(String string_id) {
    return strToLong(string_id);
}

/**
 * Constructs a list of all parameters contained in the given servlet request.
 *
 * @param request the servlet's request object
 *
 * @return The list of request parameters.
 */
public static String debugRequest (HttpServletRequest request) {
    StringBuilder b = new StringBuilder();
    Enumeration<?> e = request.getParameterNames();
    while (e.hasMoreElements()) {
        String key = (String) e.nextElement();
        b.append(key + "=" + request.getParameter(key) + "<br/>\n");
    }
    return b.toString();
}
%><!-- function.jsp -->
