package cz.fi.muni.xkremser.editor.shared.rpc.action;

import java.util.ArrayList;

import com.gwtplatform.annotation.GenDispatch;
import com.gwtplatform.annotation.Out;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

import cz.fi.muni.xkremser.editor.shared.rpc.RecentlyModifiedItem;

@GenDispatch(isSecure = false)
public class GetRecentlyModified extends UnsecuredActionImpl<ScanInputQueueResult> {

	@Out(1)
	private ArrayList<RecentlyModifiedItem> items;
}
