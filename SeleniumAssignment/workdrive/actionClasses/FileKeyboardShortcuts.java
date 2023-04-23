package workdrive.actionClasses;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class FileKeyboardShortcuts {
	
	WebDriver driver;
	
	public FileKeyboardShortcuts(WebDriver driver)
	{
		this.driver=driver;
		
		
	}
	

	
	
	public void pressShortCut(CharSequence CONTROL, String Operation) {
        Actions actions = new Actions(driver);

		actions.keyDown(CONTROL);
        actions.sendKeys(Operation);
        actions.keyUp(CONTROL);
        actions.build().perform();
       /* 
        try {
			Robot actipn=new Robot();
			actipn.keyPress(KeyEvent.VK_TAB);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
	}

}
