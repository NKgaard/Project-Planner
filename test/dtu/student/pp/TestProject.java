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
    }

    @Test
    public void testProjectNumber() {
        Project project1 = state.createProject();
        Project project2 = state.createProject();
        assertNotEquals(project1.getProjectNumber(), project2.getProjectNumber());
    }

    @Test
    public void testSetProjectLeader() {
        Project project = state.createProject();
        state.createDeveloper("DVLP");
        assertFalse(project.isLeader("DVLP"));

        project.setLeader("DVLP");
        assertTrue(project.isLeader("DVLP"));
        assertEquals("DVLP", project.getLeader());
    }

    @Test
    public void testAddActivity() {
        Project project = state.createProject();

        NormalActivity activity = state.createActivity(project);

        //The new activity should be registered both in PPState and in the individual project.
        assertTrue(state.getActivities().contains(activity));
        assertTrue(project.getActivities().contains(activity));

        assertEquals(state.getActivities().contains(activity),project.getActivities().contains(activity));

    }

    @Test
    public void testActivityNumber() {
        Project project = state.createProject();

        NormalActivity activity1 = state.createActivity(project);
        NormalActivity activity2 = state.createActivity(project);

        //The activity number should increment.
        assertTrue(activity1.getActivityID() < activity2.getActivityID());
    }

    @Test
    public void testRemoveActivity() {
        Project project = state.createProject();

        NormalActivity activity = state.createActivity(project);
        state.removeActivity(activity);

        assertFalse(state.getActivities().contains(activity));
        assertFalse(project.getActivities().contains(activity));
    }

   @Test
    public void testRemoveProject(){
       Project project = state.createProject();
       NormalActivity activity = state.createActivity(project);
       state.removeProject(project);
       assertFalse(state.getProjects().contains(project));
    }
 }
