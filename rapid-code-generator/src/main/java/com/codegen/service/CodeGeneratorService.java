package com.codegen.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.codegen.model.GeneratorInput;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class CodeGeneratorService {

    @Autowired
    private FreeMarkerConfigurer freemarkerConfig;

    Map<String, Object> dataModel =  null;
    StringWriter out = null;
    public String generateEntity(GeneratorInput input) {
        try {
            System.out.println("Received className: " + input.getClassName());
            System.out.println(" Received fields: " + input.getFields());

            dataModel=new HashMap<>();
            dataModel.put("className", input.getClassName());
            dataModel.put("fields", input.getFields());

            Template template = freemarkerConfig.getConfiguration().getTemplate("Entity.java.ftl");

            out =new StringWriter();
            template.process(dataModel, out);


            return out.toString();
//            return "Code generated successfully:\n\n" + out.toString();


        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating code: " + e.getMessage();
        }
    }
    
    public String generateRepository(GeneratorInput input) {
      	try {
    	dataModel= new HashMap<>();
    	dataModel.put("className", input.getClassName());
    	
  
			Template temp =  freemarkerConfig.getConfiguration().getTemplate("Repository.java.ftl");
			
			 out = new StringWriter();
			 
			temp.process(dataModel, out);
			
			return out.toString();
			
		} catch (IOException | TemplateException e) {
			
			e.printStackTrace();
			 return "Error generating code: " + e.getMessage();
		}
    }

	public String generateService(GeneratorInput input) {
		try {
	    	dataModel= new HashMap<>();
	    	dataModel.put("className", input.getClassName());
	    	
	  
				Template temp =  freemarkerConfig.getConfiguration().getTemplate("Service.java.ftl");
				
				 out = new StringWriter();
				 
				temp.process(dataModel, out);
				
				return out.toString();
				
			} catch (IOException | TemplateException e) {
				
				e.printStackTrace();
				 return "Error generating code: " + e.getMessage();
			}
	}
	
	

	public String generateController(GeneratorInput input) {
		try {
	    	dataModel= new HashMap<>();
	    	dataModel.put("className", input.getClassName());
	    	
	  
				Template temp =  freemarkerConfig.getConfiguration().getTemplate("Controller.java.ftl");
				
				 out = new StringWriter();
				 
				temp.process(dataModel, out);
				
				return out.toString();
				
			} catch (IOException | TemplateException e) {
				
				e.printStackTrace();
				 return "Error generating code: " + e.getMessage();
			}
	}
	
	private void writeTemplateToFile(String templateName, Map<String, Object> dataModel, String outputPath) throws Exception {
	    Template template = freemarkerConfig.getConfiguration().getTemplate(templateName);
	    try (Writer out = new FileWriter(outputPath)) {
	        template.process(dataModel, out);
	    }
	    catch (IOException | TemplateException e) {
	        e.printStackTrace(); 
	    }
	}

	public void createSpringBootStructure(String basePath, String basePackage) {
	    String baseDir = basePath + "/src/main/java/" + basePackage.replace(".", "/");
	    String resourceDir = basePath + "/src/main/resources";

	    new File(basePath).mkdirs();
	    new File(baseDir + "/controller").mkdirs();
	    new File(baseDir + "/service").mkdirs();
	    new File(baseDir + "/repository").mkdirs();
	    new File(baseDir + "/model").mkdirs();
	    new File(resourceDir).mkdirs();
	}
	
	

	public String generateFullSpringBootApp(GeneratorInput input) {
	    try {
	    	
	    	
	        String basePath = "generated-app/";
	        
	        File baseDir = new File(basePath);
	        deleteDirectory(baseDir); 
//	        String basePackage = input.getClassName().substring(0, input.getClassName().lastIndexOf("."));
//	        String className = input.getClassName().substring(input.getClassName().lastIndexOf(".") + 1);

	        String fullClassPath = input.getClassName();
	        String className = fullClassPath.substring(fullClassPath.lastIndexOf(".") + 1);
	       

	       
	        
	        // Extract only up to "com.codegen"
	        String[] parts = fullClassPath.split("\\.");
	        String basePackage = parts[0] + "." + parts[1]; 

	        
	        
	        String packagePath = basePackage.replace(".", "/");
	        String javaBasePath = basePath + "src/main/java/" + packagePath + "/";
	        String resourcesPath = basePath + "src/main/resources/";

	        new File(javaBasePath + "controller").mkdirs();
	        new File(javaBasePath + "service").mkdirs();
	        new File(javaBasePath + "repository").mkdirs();
	        new File(javaBasePath + "model").mkdirs();
	        new File(resourcesPath).mkdirs();
	       
	     

	        Map<String, Object> model = new HashMap<>();
	        model.put("className", input.getClassName());
	        model.put("fields", input.getFields());
	       
	        model.put("package", basePackage);
	        model.put("basePackage", basePackage);
	        model.put("groupId", "com.codegen"); 
	        model.put("artifactId", className.toLowerCase()); 
	     


	      
	        writeTemplateToFile("Entity.java.ftl", model, javaBasePath + "model/" + className + ".java");
	        writeTemplateToFile("Repository.java.ftl", model, javaBasePath + "repository/" + className + "Repository.java");
	        writeTemplateToFile("Service.java.ftl", model, javaBasePath + "service/" + className + "Service.java");
	        writeTemplateToFile("Controller.java.ftl", model, javaBasePath + "controller/" + className + "Controller.java");
	        writeTemplateToFile("Application.java.ftl", model, javaBasePath + "Application.java");
	        writeTemplateToFile("application.properties.ftl", model, resourcesPath + "application.properties");
	        writeTemplateToFile("pom.xml.ftl", model, basePath + "pom.xml");



	    

	        String jarResult = buildJar(basePath);
	        
	      
	     String zipResult = zipGeneratedApp(basePath, basePath + className + "-generated-app.zip");

	     return "Spring Boot application generated successfully at " + basePath + "\n"
	          + jarResult + "\n"
	          + zipResult;

//	        return "Spring Boot application generated successfully at " + basePath + "\n" + jarResult;

	        

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    }
	    
	    
	    
	}
	public void deleteDirectory(File directory) {
	    if (directory.exists()) {
	        File[] files = directory.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                if (file.isDirectory()) {
	                    deleteDirectory(file);
	                } else {
	                    file.delete();
	                }
	            }
	        }
	        directory.delete();
	    }
	}

	public String zipGeneratedApp(String sourceDirPath, String zipFilePath) {
	    try {
	        Path sourceDir = Paths.get(sourceDirPath);
	        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
	            Files.walk(sourceDir).filter(path -> !Files.isDirectory(path)).forEach(path -> {
	                ZipEntry zipEntry = new ZipEntry(sourceDir.relativize(path).toString());
	                try {
	                    zos.putNextEntry(zipEntry);
	                    Files.copy(path, zos);
	                    zos.closeEntry();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            });
	        }
	        return "ZIP file created successfully: " + zipFilePath;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "Error creating ZIP file: " + e.getMessage();
	    }
	}

	
	
	public String buildJar(String projectPath) {
	    try {
	    	ProcessBuilder processBuilder = new ProcessBuilder(
	    		    "cmd.exe", "/c",
	    		    "C:\\Users\\MohammadJuned\\Downloads\\apache-maven-3.9.9-bin\\apache-maven-3.9.9\\bin\\mvn.cmd",
	    		    "clean", "package"
	    		);
	       
	        processBuilder.directory(new File(projectPath));
	        processBuilder.redirectErrorStream(true); 
	        processBuilder.inheritIO(); 

	        Process process = processBuilder.start();
	        int exitCode = process.waitFor();

	        if (exitCode == 0) {
	            return "JAR file built successfully! You can find it at: " + projectPath + "/target/";
	        } else {
	            return "Build failed. Please check the console logs.";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error during JAR build: " + e.getMessage();
	    }
	}



	
	



}
