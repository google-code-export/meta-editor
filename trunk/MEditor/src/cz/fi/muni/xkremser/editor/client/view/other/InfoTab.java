
package cz.fi.muni.xkremser.editor.client.view.other;

import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tab.Tab;

import cz.fi.muni.xkremser.editor.client.LangConstants;
import cz.fi.muni.xkremser.editor.client.view.window.EditorSC;

import cz.fi.muni.xkremser.editor.shared.domain.DigitalObjectModel;
import cz.fi.muni.xkremser.editor.shared.rpc.DigitalObjectDetail;
import cz.fi.muni.xkremser.editor.shared.rpc.DublinCore;

public class InfoTab
        extends Tab {

    private final TextItem labelItem;
    private final String originalLabel;
    private final DigitalObjectModel model;
    private final IButton quickEdit = new IButton();

    public InfoTab(String title,
                   String icon,
                   final LangConstants lang,
                   final DigitalObjectDetail detail,
                   String type,
                   String firstPageURL) {
        super(title, icon);
        String label = detail.getLabel();
        this.model = detail.getModel();;
        this.originalLabel = label;
        VStack layout = new VStack();
        layout.setPadding(15);
        DublinCore dc = detail.getDc();
        final String lockOwner = detail.getLockOwner();
        final String lockDescription = detail.getLockDescription();

        Button lockInfoButton = null;
        if (lockOwner != null) {
            lockInfoButton = new Button();
            lockInfoButton.setTitle(lang.lockInfoButton());
            lockInfoButton.setWidth(160);

            if ("".equals(lockOwner)) {
                lockInfoButton.setIcon("icons/16/lock_lock_all.png");
            } else {
                lockInfoButton.setIcon("icons/16/lock_lock_all_red.png");
            }

            lockInfoButton.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    if (null != lockOwner) {
                        StringBuffer objectLockedBuffer = new StringBuffer();
                        EditorSC.objectIsLock(lang,
                                              lockOwner,
                                              lockDescription,
                                              detail.getTimeToExpirationLock());
                    }
                }
            });
        }

        HTMLFlow info = new HTMLFlow("<h2>" + lang.doInfo() + "</h2>");
        info.setExtraSpace(25);
        HTMLFlow pid =
                new HTMLFlow("<b>PID:</b> "
                        + (dc.getIdentifier() != null && dc.getIdentifier().size() > 0 ? dc.getIdentifier()
                                .get(0) : lang.noTitle()));
        pid.setExtraSpace(5);
        HTMLFlow tit =
                new HTMLFlow("<b>"
                        + lang.name()
                        + ":</b> "
                        + (dc.getTitle() != null && dc.getTitle().size() > 0 ? dc.getTitle().get(0)
                                : lang.noTitle()));
        tit.setExtraSpace(5);
        HTMLFlow typ = new HTMLFlow("<b>" + lang.dcType() + ":</b> " + type);
        typ.setExtraSpace(5);
        boolean isPage = DigitalObjectModel.PAGE.equals(model);
        String imgTitle = isPage ? lang.fullImg() : lang.doFirstPage();
        HTMLFlow prev = new HTMLFlow("<b>" + imgTitle + ":</b>");
        prev.setExtraSpace(5);

        labelItem = new TextItem();
        labelItem.setTitle("<b>Label</b>");
        labelItem.setTitleStyle("color: black;");
        labelItem.setValue(label);
        if (label.length() > 25) {
            labelItem.setWidth(label.length() + 200);
        }
        final DynamicForm form = new DynamicForm();
        form.setFields(labelItem);
        form.setWidth(150);
        form.setExtraSpace(5);
        HTMLFlow img =
                new HTMLFlow("<img style='border: 3px solid;max-height: 300px;max-width: 300px;' src='./images/full/"
                        + (isPage ? "" : "uuid:") + firstPageURL + "' />");

        quickEdit.setTitle(lang.quickEdit());
        quickEdit.setExtraSpace(15);

        layout.setMembers(lockInfoButton != null ? lockInfoButton : new HTMLFlow(),
                          info,
                          pid,
                          tit,
                          typ,
                          form,
                          quickEdit,
                          prev,
                          img);

        setPane(layout);
    }

    public String getLabelItem() {
        return labelItem.getValueAsString();
    }

    public String getOriginalLabel() {
        return originalLabel;
    }

    public DigitalObjectModel getModel() {
        return model;
    }

    public IButton getQuickEdit() {
        return quickEdit;
    }
}
