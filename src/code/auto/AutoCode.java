package code.auto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class AutoCode {

	 public Template getTemplate(String name) {
	        try {
	            Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
	            cfg.setClassForTemplateLoading(this.getClass(), "/code/ftl");
	            Template temp = cfg.getTemplate(name);
	            return temp;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public void auto(String name, Map<String, Object> root, String outFile) {
	        FileWriter out = null;
	        try {
	            out = new FileWriter(new File(outFile));
	            Template temp = this.getTemplate(name);
	            temp.process(root, out);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (TemplateException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (out != null)
	                    out.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
