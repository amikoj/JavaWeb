package mypack;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class HelloTag extends TagSupport {
	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().print("Hello");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return EVAL_PAGE;
	}

}
