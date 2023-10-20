package Greenmouse.BOG.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Greenmouse.BOG.reuseables.launchApp;

public class order extends launchApp{
	
	WebDriver driver;
	
	@Test
	public void open() throws InterruptedException, IOException{
	
	launchApp lc = new launchApp();
	
	lc.launch();

	lc.login();
	
	lc.buy();
	
	//lc.tearDown();

	}

}
