package com.sum.util;

import java.io.File;
import java.io.FileWriter;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class Ftl2CodeGanerator {
    
    private static transient Logger log = Logger.getLogger(Ftl2CodeGanerator.class);
    
    private String templateDir;
    private String destinationDir;
    private String templateFileName;
    private String destinationFileName;
    
    public Ftl2CodeGanerator(String templateDir, String destinationDir, String templateFileName, String destinationFileName){
        this.destinationFileName = destinationFileName;
        this.templateDir = templateDir;
        this.destinationDir = destinationDir;
        this.templateFileName = templateFileName;
    }
    
    public void generate(Map<String, Object> input) throws Exception{
        Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(templateDir));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		Template template = cfg.getTemplate(templateFileName);
		
		FileWriter fileWriter = new FileWriter(new File(destinationDir+"/"+destinationFileName));
		try {
			template.process(input, fileWriter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			fileWriter.close();
		}
    }
}
