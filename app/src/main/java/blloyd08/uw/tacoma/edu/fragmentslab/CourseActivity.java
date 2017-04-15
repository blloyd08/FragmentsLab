package blloyd08.uw.tacoma.edu.fragmentslab;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import blloyd08.uw.tacoma.edu.fragmentslab.course.CourseContent;

public class CourseActivity extends AppCompatActivity implements
        CourseFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        if (findViewById(R.id.fragment_container)!=null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new CourseFragment())
                    .commit();
        }
    }

    @Override
    public void onListFragmentInteraction(CourseContent.CourseItem item) {
        // Capture the course fragment from the activity layout
        CourseDetailFragment courseDetailFragment = (CourseDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.course_item_frag);

        if (courseDetailFragment != null) {
            // If courseDetail frag is available, we're in two-pane layout...

            // Call a method in the course detail fragment to update its content
            courseDetailFragment.updateCourseItemView(item);

        } else {
            // If the frag is not available, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected student
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back

            courseDetailFragment =
                    CourseDetailFragment.getCourseDetailFragment(item);

            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, courseDetailFragment)
                    .addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.course_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("tag","Test");
        switch(item.getItemId()) {
            case R.id.about:
                showAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAbout() {
        Log.d("Tag", "showAbout");


        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new AboutFragment())
                .addToBackStack(null);

        // Commit the transaction
        transaction.commit();
/*        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new AboutFragment()).commit();*/
    }
}
