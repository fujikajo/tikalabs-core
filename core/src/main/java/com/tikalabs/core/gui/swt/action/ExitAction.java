package com.tikalabs.core.gui.swt.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ExitAction extends Action {

    private Shell shell;

    public ExitAction(Shell shell) {
        super("Exit@Ctrl+Q");
        this.shell = shell;
        setAccelerator(SWT.MOD1 + 'Q'); // Ctrl+Q as accelerator
    }

    @Override
    public void run() {
        if (shell != null && !shell.isDisposed()) {
            shell.dispose();
        }
        Display.getDefault().dispose();
    }
}
