package org.sliit;

import java.util.Iterator;
import java.util.List;

import org.sliit.domain.Feed;
import org.sliit.service.RepositoryController;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.view.MenuItem;

import de.yanniks.app.R;


public class FeedPreferenceActivity extends PreferenceActivity {

	public static final String PREF_START_CHANNEL_KEY = "startChannel";
	public static final String PREF_ITEM_VIEW_KEY = "itemView";
	public static final String PREF_MAX_ITEMS_KEY = "maxItems";
	public static final String PREF_MAX_HOURS_KEY = "maxHours";
	public static final String PREF_UPDATE_PERIOD_KEY = "updatePeriod";
	
	public static final String DEFAULT_START_CHANNEL = "1";
	public static final String DEFAULT_ITEM_VIEW = "0"; // 0 => Offline, 1 => Online
	public static final String DEFAULT_MAX_ITEMS_PER_FEED = "20";
	public static final String DEFAULT_MAX_HOURS_PER_FEED = "-1"; // Never
	public static final String DEFAULT_UPDATE_PERIOD = "60"; // 60 minutes = 1 hour
	
	private RepositoryController mRepositoryController;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        mRepositoryController = new RepositoryController(this);
        mRepositoryController.open();
        
        addPreferencesFromResource(R.xml.preferences);
    }
	
	@Override
    protected void onDestroy() {
		super.onDestroy();
    	mRepositoryController.close();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        {
            switch (item.getItemId())
            {
                case android.R.id.home:
                    onBackPressed();
                    break;
                default:
            }
        }
        return false;
    }
}
