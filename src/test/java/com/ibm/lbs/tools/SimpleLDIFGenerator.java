package com.ibm.lbs.tools;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class SimpleLDIFGenerator implements LDIFGenerator {

  private Template template = null;

  public SimpleLDIFGenerator(String templatePath) {
    Velocity.init();
    try {
      template = Velocity.getTemplate(templatePath);
    } catch (ResourceNotFoundException e) {
      throw e;
    } catch (ParseErrorException e) {
      throw e;
    } catch (MethodInvocationException e) {
      throw e;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public String generate(Map<String, String> context) {
    VelocityContext vContext = new VelocityContext();
    for (String key: context.keySet()) {
        vContext.put(key, context.get(key));
    }

    StringWriter sw = new StringWriter();
    template.merge(vContext, sw);
    return sw.toString();
  }

}
