/**
 * Metadata Editor
 * @author Jiri Kremser
 *  
 */
package cz.fi.muni.xkremser.editor.shared.valueobj;

import java.util.ArrayList;
import java.util.List;

import cz.fi.muni.xkremser.editor.client.KrameriusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class InternalPartDetail.
 */
public class InternalPartDetail extends AbstractDigitalObjectDetail {

	// DC
	// title
	// creator
	// contributor
	// identifier - uuid
	// identifier - handle
	// type (= model:internalpart)
	// rights (treba policy:private)

	@SuppressWarnings("unused")
	private InternalPartDetail() {
		super();
	}

	public InternalPartDetail(ArrayList<ArrayList<String>> related) {
		super(related);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cz.fi.muni.xkremser.editor.shared.valueobj.AbstractDigitalObjectDetail#
	 * getModel()
	 */
	@Override
	public KrameriusModel getModel() {
		return KrameriusModel.INTERNALPART;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cz.fi.muni.xkremser.editor.shared.valueobj.AbstractDigitalObjectDetail#
	 * hasPages()
	 */
	@Override
	public boolean hasPages() {
		return true;
	}

	@Override
	public int hasContainers() {
		return 0;
	}

	@Override
	public List<KrameriusModel> getChildContainerModels() {
		return null;
	}

	// handle
	// policy

}
