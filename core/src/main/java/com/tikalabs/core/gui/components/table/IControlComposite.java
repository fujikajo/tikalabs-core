package com.tikalabs.core.gui.components.table;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface IControlComposite {

	public String getName();

	public String getDescriptionLabel();

	public String getDescription();

	public Control createControl(Composite parent);

	public void upDate();

}
