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
public class PeriodicalVolumeDetail extends AbstractDigitalObjectDetail {

	private ArrayList<InternalPartDetail> intParts;

	private ArrayList<PeriodicalItemDetail> perItems;

	@SuppressWarnings("unused")
	private PeriodicalVolumeDetail() {
		super();
	}

	public PeriodicalVolumeDetail(ArrayList<ArrayList<String>> related) {
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
		return KrameriusModel.PERIODICALVOLUME;
	}

	public void setIntParts(ArrayList<InternalPartDetail> intParts) {
		this.intParts = intParts;
		getContainers().add(0, intParts);
	}

	public void setPerItems(ArrayList<PeriodicalItemDetail> perItems) {
		this.perItems = perItems;
		getContainers().add(perItems);
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
		return 2;
	}

	@Override
	public List<KrameriusModel> getChildContainerModels() {
		return new ArrayList<KrameriusModel>() {
			{
				add(KrameriusModel.INTERNALPART);
				add(KrameriusModel.PERIODICALITEM);
			}
		};
	}

	// handle
	// policy

}
