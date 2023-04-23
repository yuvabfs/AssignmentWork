package workdrive.elementClasses;

import org.openqa.selenium.By;

public class SignInPage {
	public By userNameField=By.xpath("//div[@class='signin_box']//input[@id='login_id']");
	
	public By submitUsername=By.xpath("//div[@class='signin_box']//button[@id='nextbtn']//span[text()='Next']");
	
	public By passwordField=By.xpath("//div[@class='signin_box']//input[@id='password']");
	
	public By submitcredential=By.xpath("//div[@class='signin_box']//button[@id='nextbtn']//span[text()='Sign in']");
}
