/*
 * Metadata Editor
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Matous Jobanek (matous.jobanek@mzk.cz)
 * Moravian Library in Brno
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 
 */

package cz.fi.muni.xkremser.editor.client.view.window;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.HoverEvent;
import com.smartgwt.client.widgets.events.HoverHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;

import cz.fi.muni.xkremser.editor.client.LangConstants;
import cz.fi.muni.xkremser.editor.client.util.Constants;
import cz.fi.muni.xkremser.editor.client.view.other.HtmlCode;

import cz.fi.muni.xkremser.editor.shared.event.OpenFirstDigitalObjectEvent;
import cz.fi.muni.xkremser.editor.shared.rpc.IngestInfo;

/**
 * @author Matous Jobanek
 * @version $Id$
 */

public class IngestInfoWindow
        extends UniversalWindow {

    private static IngestInfoWindow ingestInfoWindow = null;

    public static void setInstanceOf(List<IngestInfo> ingestInfoList, LangConstants lang, EventBus eventBus) {
        if (isInstanceVisible()) {
            closeInstantiatedWindow();
        }

        ingestInfoWindow = new IngestInfoWindow(ingestInfoList, lang, eventBus);
    }

    public static boolean isInstanceVisible() {
        return (ingestInfoWindow != null && ingestInfoWindow.isCreated());
    }

    public static void closeInstantiatedWindow() {
        ingestInfoWindow.hide();
        ingestInfoWindow = null;
    }

    /**
     * @param height
     * @param width
     * @param title
     */

    private IngestInfoWindow(List<IngestInfo> ingestInfoList,
                             final LangConstants lang,
                             final EventBus eventBus) {
        super(550, 350, lang.ingestInfo(), eventBus, 20);

        int maxIngest = 0;
        Layout mainLayout = new HLayout(ingestInfoList.size());
        int lengthOfDirectoryNames = 0;

        for (IngestInfo info : ingestInfoList) {

            List<String> pid = info.getPid();
            List<String> username = info.getUsername();
            List<String> time = info.getTime();

            if (maxIngest < pid.size()) maxIngest = pid.size();

            Layout mainInfoLayout = new VLayout();
            HTMLFlow directoryFlow = new HTMLFlow(HtmlCode.title(info.getDirectory(), 3) + "<br>");
            directoryFlow.setWidth(info.getDirectory().length() * 8);
            lengthOfDirectoryNames += info.getDirectory().length() * 8;
            mainInfoLayout.addMember(directoryFlow);

            for (int i = 0; i < pid.size(); i++) {

                HTMLFlow titleFlow =
                        new HTMLFlow("<br>" + HtmlCode.bold(lang.ingestNumber() + ": " + (i + 1))
                                + "<br><br>");
                mainInfoLayout.addMember(titleFlow);

                final String pidString = (pid.get(i).contains("uuid:") ? "" : "uuid:") + pid.get(i);
                Layout pidLayout = new HLayout(2);
                HTMLFlow pidFlow;
                if (!pidString.equals(Constants.MISSING)) {
                    pidFlow = new HTMLFlow(HtmlCode.bold("PID: ") + pidString);
                } else {
                    pidFlow = new HTMLFlow(HtmlCode.bold("PID: ") + HtmlCode.redFont(lang.noTitle()));
                }
                pidFlow.setWidth(280);

                final ImgButton editButton = new ImgButton();
                editButton.setSrc(Constants.PATH_IMG_EDIT);
                editButton.setHoverStyle("interactImageHover");
                editButton.setCanHover(true);
                editButton.setHoverOpacity(85);
                editButton.setWidth(16);
                editButton.setHeight(16);
                editButton.setShowRollOver(false);
                editButton.setShowDown(false);
                editButton.addClickHandler(new ClickHandler() {

                    @Override
                    public void onClick(ClickEvent event) {
                        eventBus.fireEvent(new OpenFirstDigitalObjectEvent(pidString, null));
                    }
                });
                editButton.addHoverHandler(new HoverHandler() {

                    @Override
                    public void onHover(HoverEvent event) {
                        editButton.setPrompt(lang.menuEdit());
                    }
                });

                pidLayout.addMember(pidFlow);
                pidLayout.addMember(editButton);
                pidLayout.setExtraSpace(3);
                pidLayout.setAutoHeight();
                mainInfoLayout.addMember(pidLayout);

                HTMLFlow userNameFlow =
                        new HTMLFlow(HtmlCode.bold(lang.username())
                                + ": "
                                + (!username.get(i).equals(Constants.MISSING) ? username.get(i)
                                        : HtmlCode.redFont(lang.noTitle())));
                userNameFlow.setExtraSpace(3);
                mainInfoLayout.addMember(userNameFlow);

                HTMLFlow timeFlow =
                        new HTMLFlow(HtmlCode.bold(lang.date())
                                + ": "
                                + (!time.get(i).equals(Constants.MISSING) ? time.get(i)
                                        : HtmlCode.redFont(lang.noTitle())));
                timeFlow.setExtraSpace(12);
                mainInfoLayout.addMember(timeFlow);
            }
            mainLayout.addMember(mainInfoLayout);
        }

        int newWidth =
                (ingestInfoList.size() * 360) > (lengthOfDirectoryNames + 10) ? (ingestInfoList.size() * 360)
                        : (lengthOfDirectoryNames + 10);
        setWidth(newWidth);
        int newHeight = 110 + (maxIngest * 100);
        setHeight(newHeight);

        setEdgeOffset(15);
        addItem(mainLayout);
        centerInPage();
        show();
        focus();

        setWidth100();
        setHeight100();

        if (newWidth < getWidth()) setWidth(newWidth);
        if (newHeight < getHeight()) setHeight(newHeight);
        mainLayout.setWidth(getWidth() - (40 * ingestInfoList.size()));

    }
}
