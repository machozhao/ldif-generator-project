package com.ibm.lbs.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LDIFGeneratorTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testOutput4TAMLdif() throws IOException {
    int count = 1000000;
    String outputDir = "/Users/zhaodonglu/Temp";
    File amLdifFile = new File(outputDir, "am_all_1M.ldif");
    BufferedWriter out = new BufferedWriter(new FileWriter(amLdifFile));
    for (int i = 0; i < count; i++) {
      String uid = String.format("t%08d", i);
      String password = "passw0rd";
      String uuid = String.format("64a7f120-0c34-e6e6-a866-0050%08d", i);

      Map<String, String> context = new HashMap<String, String>();
      context.put("uid", uid);
      context.put("userPassword", password);
      context.put("uuid", uuid);

      {
        LDIFGenerator generator = new SimpleLDIFGenerator("am_user_ldif.vm");
        String ldif = generator.generate(context);

        out.write(ldif);
        // Assert.assertEquals("", ldif);
        System.out.print(ldif);
      }
      {
        LDIFGenerator generator = new SimpleLDIFGenerator("principal_ldif.vm");
        String ldif = generator.generate(context);

        out.write(ldif);
        // Assert.assertEquals("", ldif);
        System.out.print(ldif);
      }
    }

    out.close();
  }

  @Test
  public void testOutput4TIMLdif() throws IOException {
    int count = 1000000;
    String outputDir = "/Users/zhaodonglu/Temp";
    File amLdifFile = new File(outputDir, "im_all_1M.ldif");
    BufferedWriter out = new BufferedWriter(new FileWriter(amLdifFile));
    for (int i = 0; i < count; i++) {
      String uid = String.format("t%08d", i);
      String password = "passw0rd";

      Map<String, String> context = new HashMap<String, String>();
      context.put("uid", uid);
      context.put("userPassword", password);
      context.put("erglobalid4User",        String.format("88874424164%08d", i));
      context.put("erglobalid4SysAccount",  String.format("66874424164%08d", i));

      {
        LDIFGenerator generator = new SimpleLDIFGenerator("tim_user_ldif.vm");
        String ldif = generator.generate(context);

        out.write(ldif);
        // Assert.assertEquals("", ldif);
        System.out.print(ldif);
      }
    }

    out.close();
  }

}
