package mil.nga.giat.mage.sdk.login;

import java.util.ArrayList;

import mil.nga.giat.mage.sdk.connectivity.ConnectivityUtility;
import mil.nga.giat.mage.sdk.datastore.user.Event;
import mil.nga.giat.mage.sdk.datastore.user.EventHelper;
import mil.nga.giat.mage.sdk.exceptions.EventException;
import mil.nga.giat.mage.sdk.http.post.MageServerPostRequests;

import android.content.Context;
import android.util.Log;

/**
 * Updates user's recent event
 *
 * @author wiedemannse
 *
 */
public class RecentEventTask extends AbstractAccountTask {

    private static final String LOG_NAME = RecentEventTask.class.getName();

    public RecentEventTask(AccountDelegate delegate, Context applicationContext) {
        super(delegate, applicationContext);
    }

    @Override
    protected AccountStatus doInBackground(String... params) {

        // get the user's recent event
        String userRecentEventRemoteId = params[0];

        try {
            Event userRecentEvent = EventHelper.getInstance(mApplicationContext).read(userRecentEventRemoteId);

            // tell the server and update the local store
            if(ConnectivityUtility.isOnline(mApplicationContext) && !LoginTaskFactory.getInstance(mApplicationContext).isLocalLogin()) {
                if(MageServerPostRequests.postCurrentUsersRecentEvent(userRecentEvent, mApplicationContext)) {
                    return new AccountStatus(AccountStatus.Status.SUCCESSFUL_LOGIN, new ArrayList<Integer>(), new ArrayList<String>());
                }
            }
        } catch(EventException ee) {
            Log.e(LOG_NAME, "Unable to get current event.");
        }
        return new AccountStatus(AccountStatus.Status.FAILED_LOGIN, new ArrayList<Integer>(), new ArrayList<String>());
    }
}
