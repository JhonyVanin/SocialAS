package activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.example.social.R;

import fragment.ChatPlaceholderFragment;
import fragment.FriendsPlaceholderFragment;
import fragment.NavigationPlaceholderFragment;
import fragment.ProfilePlaceholderFragment;

public class MainActivity extends ActionBarActivity implements NavigationPlaceholderFragment.NavigationDrawerCallbacks {

	private NavigationPlaceholderFragment mNavigationDrawerFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationPlaceholderFragment) getSupportFragmentManager().findFragmentById(
				R.id.navigation_drawer);

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

		switch (position) {
		case 0:
			fragmentManager.beginTransaction().replace(R.id.container, new ProfilePlaceholderFragment()).commit();
			actionBar.setTitle("Profile");
			break;
		case 1:
			fragmentManager.beginTransaction().replace(R.id.container, new FriendsPlaceholderFragment()).commit();
			actionBar.setTitle("Friends");
			break;
		case 2:
			fragmentManager.beginTransaction().replace(R.id.container, new ChatPlaceholderFragment()).commit();
			actionBar.setTitle("Chat");
			break;
		}

	}
}
