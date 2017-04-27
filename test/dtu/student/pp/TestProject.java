package dtu.student.pp;

import dtu.student.pp.data.activity.NormalActivity;
import dtu.student.pp.exception.NotProjectLeaderException;
import dtu.student.pp.data.project.Project;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestProject {
    // 100% Coverage of the Project class (excluding 'equals' methods).

    PPState state;

    @Before
    public void initialize() {
        state = new PPState();
    }

    @Test
    public void testCreateProject() {
        //Test the creation of a new project.
        Project project = state.createProject();
        assertNotNull(project);

        //Test that the new project can be queried by it's number.
        //assertNotNull(project.getProjectNumber());
        //assertEquals(pps.getProject(project.getProjectNumber()), project);
    }

    @Test
    public void testProjectNumber() {
        //TODO Use mock tests to ensure that the project number depends on the year.
        Project project1 = state.createProject();
        Project project2 = state.createProject();
        assertNotEquals(project1.getProjectNumber(), project2.getProjectNumber());
    }

    @Test
    public void testCreateActivityNotLeader() {
//        Project project = state.createProject();
//        state.createActivity(project);
//        try {
//            fail("Should throw NotProjectLeaderException");
//        } catch (NotProjectLeaderException e) {
//            assertNotNull("Failed to assert", e.getMessage());
//            assertEquals("Failed to assert", "User must be the project leader to perform this action!", e.getMessage());
//        }
    }

    @Test
    public void testSetProjectLeader() {
        Project project = state.createProject();
        Developer dev = state.createDeveloper("DVLP".toCharArray());
        assertFalse(project.isLeader(dev));

        project.setLeader(dev);
        assertTrue(project.isLeader(dev));
        assertEquals(dev, project.getLeader());
    }

    @Test
    public void testAddActivity() {
        Project project = state.createProject();
        Developer dev = state.createDeveloper("DVLP".toCharArray());
        project.setLeader(dev);
        NormalActivity activity = state.createActivity(project);

        project.addActivity(activity);

        //The new activity should be registered both in PPState and in the individual project.
        assertTrue(state.getActivities().contains(activity));
        assertTrue(project.getActivities().contains(activity));

        assertEquals(state.getActivities().contains(activity),project.getActivities().contains(activity));

    }

    @Test
    public void testActivityNumber() {
        Project project = state.createProject();
        Developer dev = state.createDeveloper("DVLP".toCharArray());
        project.setLeader(dev);

        NormalActivity activity1 = state.createActivity(project);
        NormalActivity activity2 = state.createActivity(project);
        //The activity number should increment.
        assertTrue(activity1.getActivityID() < activity2.getActivityID());
    }

    //Since there is no log out capability, we won't test removing activity while not logged in for now.

    @Test
    public void testRemoveActivity() {
        Project project = state.createProject();
        Developer dev = state.createDeveloper("DVLP".toCharArray());
        project.setLeader(dev);

        NormalActivity activity = state.createActivity(project);
        state.removeActivity(activity);

        assertFalse(state.getActivities().contains(activity));
        assertFalse(project.getActivities().contains(activity));

    }

   @Test
    public void testRemoveProject(){

    }
}
