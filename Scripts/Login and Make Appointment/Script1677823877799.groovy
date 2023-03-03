import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
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
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Point
import org.openqa.selenium.WebElement



WebUI.openBrowser("https://katalon-demo-cura.herokuapp.com/#appointment")

WebDriver driver = DriverFactory.getWebDriver()
driver.manage().window().setPosition(new Point(+1500, 00))
WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/OnlyLogin/Btn_MakeAppt'))


// Enter username and password
WebUI.setText(findTestObject('Object Repository/OnlyLogin/UserName'), 'John Doe')
WebUI.setText(findTestObject('Object Repository/OnlyLogin/Password'), 'ThisIsNotAPassword')

// Click on the login button
WebUI.click(findTestObject('Object Repository/OnlyLogin/Btn_Login'))

// Verify login success
WebUI.verifyElementVisible(findTestObject('Object Repository/OnlyLogin/LoginVerify_MakeAppt'))

WebUI.selectOptionByIndex(findTestObject('Object Repository/MakeAppointment/SelectFacility'), 2)
WebUI.click(findTestObject('Object Repository/MakeAppointment/CheckApplyReadmiss'))
WebUI.click(findTestObject('Object Repository/MakeAppointment/RadioMiddle'))

// Send current date to the Calendar object
LocalDate currentDate = LocalDate.now()
String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

WebUI.click(findTestObject('Object Repository/MakeAppointment/VisitDate'))
WebUI.setText(findTestObject('Object Repository/MakeAppointment/VisitDate'), formattedDate ) //'02/03/2023'
WebUI.setText(findTestObject('Object Repository/MakeAppointment/Comment'), 'This is a test comment')
WebUI.click(findTestObject('Object Repository/MakeAppointment/Btn_bookApptSubm'))


// Verify appt success
WebUI.verifyElementVisible(findTestObject('Object Repository/MakeAppointment/ApptVerifyConfirmMsg'))

WebUI.click(findTestObject('Object Repository/MakeAppointment/Btn_GotoHome'))

WebUI.closeBrowser()
