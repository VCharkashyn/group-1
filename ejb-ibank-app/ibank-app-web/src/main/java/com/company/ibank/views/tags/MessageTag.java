package com.company.ibank.views.tags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MessageTag extends SimpleTagSupport {

    private String id;
    private boolean rendered;
    private String value;

    /**
     * Called by the container to invoke this tag. 
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            if (rendered) {
                if (id != null) {
                    out.println("<div class=\"" + id + "\">");
                }
                if (value != null) {
                    out.print(value);
                }
                JspFragment f = getJspBody();
                if (f != null) {
                    getJspBody().invoke(null);
                }
                if (id != null) {
                    out.println("</div>");
                }
            }

        }
        catch (java.io.IOException ex) {
            throw new JspException("Error in MessageTag tag", ex);
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
