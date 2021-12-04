package com.example.PruebaGoogle;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Coronavirus {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
	WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testUntitledTestCase() throws Exception {
	    driver.get("https://es.uadyvirtual.uady.mx/");
	   
	    
	    String textoEjecucion = driver.getTitle();
	    //ERROR: Caught exception [Error: locator strategy either id or name must be specified explicitly.]
	    assertThat("UADY Virtual Educación Superior: Ingresar al sitio",is(textoEjecucion));
	  }
  @Test
  public void testDatosIncorrectos() throws Exception {
    driver.get("https://es.uadyvirtual.uady.mx/login/index.php");
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("a18211328");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("pruebaclase");
    driver.findElement(By.id("loginbtn")).click();
    pause(5000);
    String textoEjecucion = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/section/div/div[2]/div/div/div/div/div[1]/div")).getText();
    String textoEsperado = "Datos erróneos. Por favor, inténtelo otra vez.";
    assertThat(textoEsperado, is(textoEjecucion));
  }
    
  @Test
  public void testDatosCorrectos() throws Exception {
    driver.get("https://es.uadyvirtual.uady.mx/login/index.php");
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("a18211328");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("");
    driver.findElement(By.id("loginbtn")).click();
    pause(5000);
    String textoEjecucion = driver.findElement(By.xpath("/html/body/div[2]/nav/ul[2]/li[2]/div/div/div/div/div/a/span/span[1]")).getText();
    String textoEsperado = "LUIS ALBERTO PENICHE CHABLE";
    assertThat(textoEsperado, is(textoEjecucion));
  }
    
  
  
  /*public void testCoronavirus() throws Exception {
	    driver.get("https://www.google.com.mx/webhp?hl=es&tab=vw");
	    driver.findElement(By.name("q")).sendKeys(Keys.DOWN);
	    driver.findElement(By.name("q")).clear();
	    driver.findElement(By.name("q")).sendKeys("coronavirus");
	    driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	    driver.findElement(By.xpath("//div[@id='kp-wp-tab-overview']/div[3]/div/div[2]/div/div/div/div/div/div/div/div/a/h3")).click();
	    String textoEjecucion = driver.getTitle();
	    //Warning: assertTextPresent may require manual changes
	    assertThat("Todo sobre el COVID-19", is(textoEjecucion));
	    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Todo sobre el COVID-19[\\s\\S]*$"));
	  }*/

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
  private void pause(long mils) {
	  try {
		  Thread.sleep(mils);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }

}
