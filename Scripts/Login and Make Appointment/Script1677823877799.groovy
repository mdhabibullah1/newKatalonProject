import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List as List
import java.util.ArrayList as ArrayList
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Point
import org.openqa.selenium.WebElement
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import org.apache.poi.hwpf.HWPFDocument
import org.apache.poi.hwpf.extractor.WordExtractor
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.By
import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.charset.Charset
import java.io.FileInputStream
import org.apache.commons.io.IOUtils
import javax.swing.text.*
import javax.swing.text.rtf.*





//WebUI.openBrowser("https://katalon-demo-cura.herokuapp.com/#appointment")
WebUI.openBrowser(GlobalVariable.ServerURL)

WebDriver driver = DriverFactory.getWebDriver()
//driver.manage().window().setPosition(new Point(+1500, 00))
driver.manage().window().setPosition(new Point(-1900, 00))
WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/OnlyLogin/Btn_MakeAppt'))


// Enter username and password
//WebUI.setText(findTestObject('Object Repository/OnlyLogin/UserName'), 'John Doe')
//WebUI.setText(findTestObject('Object Repository/OnlyLogin/Password'), 'ThisIsNotAPassword')

WebUI.setText(findTestObject('Object Repository/OnlyLogin/UserName'), GlobalVariable.UserName)
WebUI.setText(findTestObject('Object Repository/OnlyLogin/Password'), GlobalVariable.Password)

// Click on the login button
WebUI.click(findTestObject('Object Repository/OnlyLogin/Btn_Login'))

// Verify login success
WebUI.verifyElementVisible(findTestObject('Object Repository/OnlyLogin/LoginVerify_MakeAppt'))

//WebUI.selectOptionByIndex(findTestObject('Object Repository/MakeAppointment/SelectFacility'), 2)

WebElement dropdown = driver.findElement(By.xpath('//select[@id="combo_facility"]'))
Select select = new Select(dropdown)
List<WebElement> options = select.getOptions()
WebElement lastOption = options.get(options.size() - 1)
select.selectByVisibleText(lastOption.getText())

WebUI.click(findTestObject('Object Repository/MakeAppointment/CheckApplyReadmiss'))
WebUI.click(findTestObject('Object Repository/MakeAppointment/RadioMiddle'))

// Send current date to the Calendar object
LocalDate currentDate = LocalDate.now()
String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

//WebUI.click(findTestObject('Object Repository/MakeAppointment/VisitDate'))
WebUI.setText(findTestObject('Object Repository/MakeAppointment/VisitDate'), formattedDate ) //'02/03/2023'

//WebUI.setText(findTestObject('Object Repository/MakeAppointment/Comment'), 'This is a test comment')


String filePath =  GlobalVariable.TxtFileReadingLocation   //"/Users/mds/Desktop/All_file_format_for_test/Txt.rtf" 
File file = new File(filePath)
String rtfContent = IOUtils.toString(new FileInputStream(file), "UTF-8");

// create a new instance of DefaultStyledDocument
DefaultStyledDocument doc = new DefaultStyledDocument();

// create an RTF editor kit to parse the RTF content
RTFEditorKit kit = new RTFEditorKit();

// read the RTF content into the document object
kit.read(new ByteArrayInputStream(rtfContent.getBytes()), doc, 0);

// print the plain text
String plainText = doc.getText(0, doc.getLength());

WebUI.setText(findTestObject('Object Repository/MakeAppointment/Comment'), plainText)

WebUI.click(findTestObject('Object Repository/MakeAppointment/Btn_bookApptSubm'))

// Verify appt success
WebUI.verifyElementVisible(findTestObject('Object Repository/MakeAppointment/ApptVerifyConfirmMsg'))

WebUI.delay(1)
//Take screenshot 
WebUI.takeFullPageScreenshot(GlobalVariable.ScreenShotSLocation)

WebUI.click(findTestObject('Object Repository/MakeAppointment/Btn_GotoHome')) 

WebUI.closeBrowser()
