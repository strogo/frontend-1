package com.gu.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
	
	public static WebDriver createDriver(String type, String httpProxy) {
		
		WebDriver driver;
		
		if (type.equals("htmlUnit")) {
			
			java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setJavascriptEnabled(true);
			
			driver = new HtmlUnitDriver(capabilities);
			
		} else if (type.equals("phantomJS")) {
	      
      // prepare capabilities
      Capabilities caps = new DesiredCapabilities();
      caps.setJavascriptEnabled(true);                //< not really needed: JS enabled by default
      caps.setCapability("takesScreenshot", true);    //< yeah, GhostDriver haz screenshotz!
      caps.setCapability(
          PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
          "/path/to/your/phantomjs/binary/greater/than/1.7"
      );

      // Launch driver (will take care and ownership of the phantomjs process)
      WebDriver driver = new PhantomJSDriver(caps);
	      
	  } else {

			FirefoxProfile profile = new FirefoxProfile();
			
			if (!httpProxy.isEmpty()) {
				try {
					URL proxyUrl = new URL(httpProxy);
					profile.setPreference("network.proxy.type", 1);
					// set the proxy's url
					profile.setPreference("network.proxy.http", proxyUrl.getHost());
					// extract the port, or use the default
					int port = (proxyUrl.getPort() != -1) ? proxyUrl.getPort() : proxyUrl.getDefaultPort();
					profile.setPreference("network.proxy.http_port", port);
				} catch (MalformedURLException e) {
					System.out.println("Unable to parse `httpProxy`: " + e.getMessage());
				}
			}

			driver = new FirefoxDriver(profile);
			
		}
		return driver;
		
	}
	
}