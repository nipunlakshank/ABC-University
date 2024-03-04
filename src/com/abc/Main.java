package com.abc;

import com.abc.gui.Splash;
import com.formdev.flatlaf.IntelliJTheme;

/**
 *
 * @author Nipun
 */
public class Main {
	
	private static Splash splash;
	
	public static void main(String[] args){
		setTheme();
		splash = new Splash();
		splash.setVisible(true);
	}
	
	public static void setTheme() {
		IntelliJTheme.setup(Main.class.getResourceAsStream("assets/themes/arc-theme-orange.theme.json"));
		try {
		} catch (Exception e) {
		}
	}
	
}
