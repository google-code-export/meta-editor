/**
 * Metadata Editor
 * @author Jiri Kremser
 *  
 */
package cz.fi.muni.xkremser.editor.client.view;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.UiHandlers;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;

import cz.fi.muni.xkremser.editor.client.presenter.AppPresenter;

// TODO: Auto-generated Javadoc
/**
 * The Class AppView.
 */
public class AppView extends ViewWithUiHandlers<AppView.MyUiHandlers> implements AppPresenter.MyView {

	/**
	 * The Interface MyUiHandlers.
	 */
	public interface MyUiHandlers extends UiHandlers {
	}

	/** The left container. */
	private final Layout leftContainer;

	/** The top container. */
	private final Layout topContainer;

	/** The main container. */
	private final Layout mainContainer;

	/** The widget. */
	public VLayout widget;

	// private HasWidgets mainContainer;

	/**
	 * Instantiates a new app view.
	 */
	public AppView() {
		widget = new VLayout();

		leftContainer = new VLayout();
		leftContainer.setWidth(275);
		leftContainer.setShowResizeBar(true);
		mainContainer = new VLayout(); // TODO: consider some panel
		widget.setWidth100();
		widget.setHeight100();
		widget.setLeaveScrollbarGap(true);
		widget.setOverflow(Overflow.AUTO);
		topContainer = new HLayout();
		topContainer.setWidth100();
		topContainer.setHeight(45);

		// Image logo = new Image(MEditorResources.INSTANCE.logo());
		HTMLFlow logo = new HTMLFlow("<a href='/'><img class='noFx' src='images/logo_bw.png' alt='logo'></a>");
		// Img logo = new Img("logo_bw.png", 140, 40);
		// Img logo = new Img("mzk_logo.gif", 283, 87);
		topContainer.addMember(logo);
		widget.addMember(topContainer);

		HLayout underTop = new HLayout();
		underTop.setAutoWidth();
		underTop.setAutoHeight();
		underTop.setOverflow(Overflow.AUTO);
		underTop.setWidth100();
		underTop.setHeight100();
		widget.addMember(underTop);

		underTop.addMember(leftContainer);
		underTop.addMember(mainContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwtplatform.mvp.client.View#asWidget()
	 */
	@Override
	public Widget asWidget() {
		return widget;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwtplatform.mvp.client.ViewImpl#setInSlot(java.lang.Object,
	 * com.google.gwt.user.client.ui.Widget)
	 */
	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == AppPresenter.TYPE_SetMainContent) {
			setMainContent(content);
			// } else if (slot == AppPresenter.TYPE_SetTopContent) {
			// setTopContent(content);
		} else if (slot == AppPresenter.TYPE_SetLeftContent) {
			setLeftContent(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	/**
	 * Sets the main content.
	 * 
	 * @param content
	 *          the new main content
	 */
	private void setMainContent(Widget content) {
		mainContainer.clear();
		if (content != null) {
			mainContainer.addMember(content);
		}
	}

	/**
	 * Sets the left content.
	 * 
	 * @param content
	 *          the new left content
	 */
	private void setLeftContent(Widget content) {
		// leftContainer.clear();
		if (content != null) {
			leftContainer.addMember(content);
		}
	}

	// private void setTopContent(Widget content) {
	// topContainer.clear();
	// if (content != null) {
	// topContainer.addMember(content);
	// }
	// }

}