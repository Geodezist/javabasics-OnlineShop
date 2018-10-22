package ua.com.bpgdev.onlineshop.web.templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {
    private final static  String HTML_DIR = "templates/";
    private final Configuration cfg;

    private static PageGenerator pageGenerator;

    public static PageGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String filename) {
        return getPage(filename, null);
    }


    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }

    private PageGenerator() {
        cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setClassForTemplateLoading(this.getClass(), "/resources");
    }

}
