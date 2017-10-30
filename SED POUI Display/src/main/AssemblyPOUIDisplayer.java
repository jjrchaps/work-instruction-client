package main;

import connections.ServerConnection;
import views.StartupView;

public class AssemblyPOUIDisplayer {
	public static void main(String[] args) {
		ServerConnection connection = new ServerConnection();
		StartupView startup = new StartupView(connection);
		startup.makeVisible();
	}
}