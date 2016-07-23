package com.ibm.lbs.tools;

import java.util.Map;

public interface LDIFGenerator {
  
  /**
   * @param context
   * @return
   */
  public String generate(Map<String, String> context);
}
