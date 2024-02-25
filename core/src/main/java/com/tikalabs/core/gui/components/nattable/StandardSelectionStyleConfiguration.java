package com.tikalabs.core.gui.components.nattable;

import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.selection.config.DefaultSelectionStyleConfiguration;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.style.SelectionStyleLabels;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.eclipse.nebula.widgets.nattable.util.GUIHelper;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import com.tikalabs.core.gui.components.nattable.utils.ColorHelper;

public class StandardSelectionStyleConfiguration extends DefaultSelectionStyleConfiguration {

	public Font selectionFont = GUIHelper.DEFAULT_FONT;
	public Font selectedHeaderFont = GUIHelper.DEFAULT_FONT;
	public Color selectionBgColor = ColorHelper.COLOR_SELECTION_BACKGROUND;
	public Color anchorBgColor = ColorHelper.COLOR_SELECTION_BACKGROUND;
	public Color selectionFgColor = GUIHelper.COLOR_BLACK;
	public Color selectedHeaderBgColor = ColorHelper.COLOR_SELECTION_BACKGROUND;
	public Color selectedHeaderFgColor = GUIHelper.COLOR_BLACK;

	public Color anchorFgColor = GUIHelper.COLOR_BLACK;

	@Override
	protected void configureSelectionStyle(IConfigRegistry configRegistry) {
		Style cellStyle = new Style();
		cellStyle.setAttributeValue(CellStyleAttributes.FONT, this.selectionFont);
		cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, this.selectionBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, this.selectionFgColor);

		configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.SELECT);
	}

	@Override
	protected void configureSelectionAnchorStyle(IConfigRegistry configRegistry) {
		// Selection anchor style for normal display mode
		Style cellStyle = new Style();
		cellStyle.setAttributeValue(CellStyleAttributes.BORDER_STYLE, this.anchorBorderStyle);
		configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL,
				SelectionStyleLabels.SELECTION_ANCHOR_STYLE);

		// Selection anchor style for select display mode
		cellStyle = new Style();
		cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, this.anchorBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, this.anchorFgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.BORDER_STYLE, this.anchorBorderStyle);
		configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.SELECT,
				SelectionStyleLabels.SELECTION_ANCHOR_STYLE);
	}

	@Override
	protected void configureHeaderHasSelectionStyle(IConfigRegistry configRegistry) {
		Style cellStyle = new Style();

		cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, this.selectedHeaderFgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, this.selectedHeaderBgColor);
		cellStyle.setAttributeValue(CellStyleAttributes.FONT, this.selectedHeaderFont);
		cellStyle.setAttributeValue(CellStyleAttributes.BORDER_STYLE, this.selectedHeaderBorderStyle);

		configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.SELECT,
				GridRegion.COLUMN_HEADER);
		configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cloneStyle(cellStyle),
				DisplayMode.SELECT, GridRegion.CORNER);
		configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cloneStyle(cellStyle),
				DisplayMode.SELECT, GridRegion.ROW_HEADER);
	}

	private Style cloneStyle(Style originalStyle) {
		return new Style(originalStyle);

	}

}