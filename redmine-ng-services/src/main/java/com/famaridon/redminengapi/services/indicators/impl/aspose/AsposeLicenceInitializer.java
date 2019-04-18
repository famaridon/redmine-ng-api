package com.famaridon.redminengapi.services.indicators.impl.aspose;

import java.io.InputStream;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class AsposeLicenceInitializer {
  private static final Logger LOGGER = LoggerFactory.getLogger(AsposeLicenceInitializer.class);

  @PostConstruct
  private void init(){
    com.aspose.words.License license = new com.aspose.words.License();
    try (InputStream licenseStream=new java.io.FileInputStream("Aspose.Total.Java.lic");){
      LOGGER.info("loading aspose license");
      license.setLicense(licenseStream);
    } catch (Exception e) {
      LOGGER.warn("No aspose license found", e);
    }
  }
}
