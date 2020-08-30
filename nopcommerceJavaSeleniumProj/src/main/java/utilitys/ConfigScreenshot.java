package utilitys;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import reusableFunations.Log;

public class ConfigScreenshot {
	
	
	private ReadConfigFile readConfigFile;
	private String foldarPath; // =".\\src\\test\\java\\screenshots\\";
	private WebDriver driver;
	private String dateFormat;
	static Log log = new Log(ConfigScreenshot.class.getName());
	
	

	public ConfigScreenshot(WebDriver driver) {
		this.driver = driver;
		this.readConfigFile = new ReadConfigFile();
	}

	public void takeScreenshot(String screenshotName) {

		if (driver == null || readConfigFile == null) {

			log.error("Class Name : " + ConfigScreenshot.class.getName()

					+ " values of -> Driver : " + driver + "," + "   ReadConfigFile : " + readConfigFile);

			throw new RuntimeException("driver : " + driver + " , readConfigFile : " + readConfigFile);

		}

		// System.out.println(this.readConfigFile.getScreenshotPath());
		this.dateFormat = new SimpleDateFormat("_yyy_MM_dd__hh_mm_ss").format(new Date());
		this.foldarPath = this.readConfigFile.getScreenshotPath();
		File screenshot = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
		File foldarPathLoc = new File(this.foldarPath + screenshotName + this.dateFormat + ".png");
		if (screenshot != null && foldarPathLoc != null && this.foldarPath != null) {
			try {
				FileUtils.copyFile(screenshot, foldarPathLoc);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
				throw new RuntimeException("Screenshot not crated chick Screensht class");

			}
		} else {
			System.exit(1);
			throw new RuntimeException("Screenshot not crated chick Screensht class");
		}

	}
}
