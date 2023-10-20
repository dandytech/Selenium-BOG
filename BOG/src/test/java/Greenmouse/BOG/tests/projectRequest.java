package Greenmouse.BOG.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Greenmouse.BOG.reuseables.launchApp;

public class projectRequest extends launchApp {
	WebDriver driver;
	
	@Test
	public void requestProvider() throws InterruptedException, IOException {
		launchApp request = new launchApp();
	
		request.launch();
		
		request.login();
		
		request.requestService();
			
	}
	
	
	

}
