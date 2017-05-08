package dtu.student.pp;

import dtu.student.pp.data.activity.NormalActivity;
import dtu.student.pp.exception.NotProjectLeaderException;
import dtu.student.pp.data.project.Project;
import dtu.student.pp.ProjectPlanner;

import static org.junit.Assert.*;

import dtu.student.pp.exception.UserNotStaffException;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

public class TestProjectPlanner {

    ProjectPlanner pp;

    @Before
    public void initialize() {
        PPState state = new PPState();
        state.createDeveloper("LEAD");
        state.createDeveloper("DEV1");
        pp = new ProjectPlanner("LEAD", state);
    }

    @Test
    public void testCreateActivityNotLeader() {
        Project project = pp.createProject();
        try {
            pp.createActivity(project);
            fail("Should throw NotProjectLeaderException");
        } catch (NotProjectLeaderException e) {
            assertNotNull("Failed to assert", e.getMessage());
            assertEquals("Failed to assert", "User must be the project leader to perform this action!", e.getMessage());
        }
    }

    @Test
    public void testSetLeader() {
        Project project = pp.createProject();
        pp.setLeader(project);
        assertEquals(pp.getUser(), project.getLeader());
    }

    @Test
    public void testCreateActivity() {
        Project project = pp.createProject();
        pp.setLeader(project);
        try {
            NormalActivity activity = pp.createActivity(project);
            assertNotNull(activity);
        } catch (NotProjectLeaderException e){
            fail("Should not throw NotProjectLeaderException");
        }
    }

    @Test
    public void testRemoveActivity() {
        Project project = pp.createProject();
        pp.setLeader(project);
        try {
            NormalActivity activity = pp.createActivity(project);
            pp.removeActivity(activity);
        } catch (NotProjectLeaderException e){
            fail("Should not throw NotProjectLeaderException");
        }
    }

    @Test
    public void testRegisterStaff() {
        Project project = pp.createProject();
        pp.setLeader(project);
        try {
            NormalActivity activity = pp.createActivity(project);
            pp.registerStaff(activity,"DEV1");
        } catch (NotProjectLeaderException e) {
            fail("Should not throw NotProjectLeaderException");
        }
    }

    @Test
    public void testRemoveStaff() {
        Project project = pp.createProject();
        pp.setLeader(project);
        try {
            NormalActivity activity = pp.createActivity(project);
            pp.registerStaff(activity,"DEV1");
            pp.removeStaff(activity, "DEV1");
        } catch (NotProjectLeaderException e) {
            fail("Should not throw NotProjectLeaderException");
        }
    }


    @Test
    public void testRegisterAssistance() {
        Project project = pp.createProject();
        pp.setLeader(project);
        try {
            NormalActivity activity = pp.createActivity(project);
            pp.registerStaff(activity, "LEAD");
            pp.registerAssistance(activity, "DEV1");
        } catch (NotProjectLeaderException e) {
            fail("Should not throw NotProjectLeaderException");
        } catch (UserNotStaffException e) {
            fail("Should not throw UserNotStaffException");
        }
    }

    @Test
    public void testGetProjectsLeading() {
        Project project1 = pp.createProject();
        pp.setLeader(project1);
        assertTrue(pp.getProjectsLeading("LEAD").collect(Collectors.toSet()).contains(project1));
        }

    @Test
    public void testGetActivitiesStaffing() {
        Project project = pp.createProject();
        pp.setLeader(project);

        try {
            NormalActivity activity1 = pp.createActivity(project);
            pp.registerStaff(activity1, "DEV1");
            assertTrue(pp.getActivitiesStaffing("DEV1").collect(Collectors.toSet()).contains(activity1));
            } catch (NotProjectLeaderException e) {
            fail("Should not throw");
        }
    }

    @Test
    public void testGenerateReport(){
        Project project = pp.createProject();
        project.setName("A Test Project");
        pp.setLeader(project);
        try {
            NormalActivity activity = pp.createActivity(project);
            activity.setName("Make a cool design");
            activity.setTimeEstimate((float) 6);
            NormalActivity activity1 = pp.createActivity(project);
            activity1.setName("Code some cool stuff");
            activity1.setTimeEstimate((float) 5);
            pp.registerStaff(activity, pp.getUser());
            pp.registerAssistance(activity, "DEV1");
            pp.registerStaff(activity1, "DEV1");
            pp.registerHours(activity, (float) 3.5);
            pp.generateReport(project);
        } catch (NotProjectLeaderException e) {
            fail("Should not throw NotProjectLeaderException");
        } catch (UserNotStaffException e) {
            fail("Should not throw UserNotStaffException");
        }
    }

}
