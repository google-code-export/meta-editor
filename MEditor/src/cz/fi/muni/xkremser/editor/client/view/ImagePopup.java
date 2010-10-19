/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package cz.fi.muni.xkremser.editor.client.view;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Example file.
 * 
 * @gwt.CSS .gwt-PopupPanel
 * @gwt.CSS html>body .gwt-PopupPanel
 * @gwt.CSS * html .gwt-PopupPanel
 * @gwt.CSS .gwt-DecoratedPopupPanel
 * @gwt.CSS html>body .gwt-DecoratedPopupPanel
 * @gwt.CSS * html .gwt-DecoratedPopupPanel
 */
public class ImagePopup {
	/**
	 * The constants used in this Content Widget.
	 * 
	 * @gwt.SRC
	 */
	public static interface CwConstants extends Constants {
		String cwBasicPopupClickOutsideInstructions();

		String cwBasicPopupDescription();

		String cwBasicPopupInstructions();

		String cwBasicPopupName();

		String cwBasicPopupShowButton();
	}

	public ImagePopup() {
	}

	/**
	 * Initialize this example.
	 * 
	 * @gwt.SRC
	 */
	public Widget onInitialize() {
		// Create a basic popup widget
		final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
		simplePopup.setGlassEnabled(true);
		simplePopup.ensureDebugId("cwBasicPopup-simplePopup");
		simplePopup.setWidth("150px");
		simplePopup.setWidget(new HTML("blablsldjkfsklfasfasf"));

		// Create a button to show the popup
		Button openButton = new Button("butonsdbutrtbooobnnbutoto", new ClickListener() {
			@Override
			public void onClick(Widget sender) {
				// Reposition the popup relative to the button
				int left = sender.getAbsoluteLeft() + 10;
				int top = sender.getAbsoluteTop() + 10;
				simplePopup.setPopupPosition(left, top);

				// Show the popup
				simplePopup.show();
			}
		});

		// Create a popup to show the full size image
		Image jimmyFull = new Image("http://evanii.brloh.org/gallery/gallery1/0_1.jpg");
		final PopupPanel imagePopup = new PopupPanel(true);
		imagePopup.setAnimationEnabled(true);
		imagePopup.ensureDebugId("cwBasicPopup-imagePopup");
		imagePopup.setWidget(jimmyFull);
		jimmyFull.addClickListener(new ClickListener() {
			@Override
			public void onClick(Widget sender) {
				imagePopup.hide();
			}
		});

		// Add an image thumbnail
		Image jimmyThumb = new Image("http://evanii.brloh.org/gallery/gallery1/tn_0_1.jpg");
		jimmyThumb.ensureDebugId("cwBasicPopup-thumb");
		jimmyThumb.addStyleName("cw-BasicPopup-thumb");
		jimmyThumb.addClickListener(new ClickListener() {
			@Override
			public void onClick(Widget sender) {
				imagePopup.center();
			}
		});

		// Add the widgets to a panel
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(5);
		vPanel.add(openButton);
		vPanel.add(new HTML("<br><br><br>" + "vpaneeellll widgentt panell "));
		vPanel.add(jimmyThumb);
		// DOM.setStyleAttribute(vPanel, "zIndex", "800001");

		// Return the panel
		return vPanel;
	}
}