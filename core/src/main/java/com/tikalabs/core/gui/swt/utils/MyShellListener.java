package com.tikalabs.core.gui.swt.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MyShellListener implements ShellListener {

	private Shell shell;
	private Display display;

	public MyShellListener(Display display, Shell shell) {
		this.display = display;
		this.shell = shell;
	}

	@Override
	public void shellActivated(ShellEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shellClosed(ShellEvent e) {
		// Prompt confirmation when the user tries to close the shell
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		messageBox.setText("Confirmation");
		messageBox.setMessage("Are you sure you want to exit?");
		if (messageBox.open() == SWT.YES) {
			// Dispose the display to exit the application
			display.dispose();
		} else {
			// Cancel the close operation
			e.doit = false;
		}
	}

	@Override
	public void shellDeactivated(ShellEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shellDeiconified(ShellEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shellIconified(ShellEvent e) {
		// TODO Auto-generated method stub

	}

}
