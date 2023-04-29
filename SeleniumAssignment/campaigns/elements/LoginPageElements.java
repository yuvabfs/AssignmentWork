package campaigns.elements;

import org.openqa.selenium.By;

public class LoginPageElements {
	
	public By signInButton=By.xpath("//div[@id='header']/div[@class='zgh-utilities']/div[@class='zgh-accounts']/a[text()='SIGN IN']");

	public By userNameField=By.xpath("//div[@class='signin_box']//input[@id='login_id']");

	public By userNameSubmitButton=By.xpath("//div[@class='signin_box']//button[@id='nextbtn']/span[text()='Next']");
	
	public By passWordField=By.xpath("//div[@class='signin_box']//input[@id='password']");
	
	public By passWordSubmitButton=By.xpath("//div[@class='signin_box']//button[@id='nextbtn']/span[text()='Sign in']");
	
}
