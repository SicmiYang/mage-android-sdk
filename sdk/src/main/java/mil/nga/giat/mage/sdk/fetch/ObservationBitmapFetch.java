package mil.nga.giat.mage.sdk.fetch;

import android.content.Context;

import mil.nga.giat.mage.sdk.http.get.MageServerGetRequests;

public class ObservationBitmapFetch extends AbstractServerFetch {

	public ObservationBitmapFetch(Context context) {
		super(context);
	}

	private static final String LOG_NAME = ObservationBitmapFetch.class.getName();

	public void fetch() {
		MageServerGetRequests.getAndSaveObservationIcons(mContext);
	}
}