package main;

import java.util.ArrayList;

import customtypes.POUI;
import views.StartupView;

public class AssemblyPOUIDisplayer {
	public static ArrayList<POUI> pouiContainer;
	
	public static void main(String[] args) {
		pouiContainer = new ArrayList<POUI>();
		// add POUI to the container. this is temporary until server is created.
		StartupView startup = new StartupView();
		startup.makeVisible();
	}
}
