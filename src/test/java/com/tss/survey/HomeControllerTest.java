package com.tss.survey;

import com.tss.survey.repository.TrailRepository;
import com.tss.survey.model.Trail;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    //HomePage Tests---------------------------------------------------------------------------------------------------
    @Test
    void indexReturnsIndexView() {
        HomeController controller = new HomeController();
        Model model = new ExtendedModelMap();

        String viewName = controller.index(model);

        assertEquals("index", viewName);
        assertEquals("Trail Tracker", model.getAttribute("title"));
        assertEquals("This is the homepage.", model.getAttribute("message"));
    }

    @Test
    void indexModelHasTitleAndMessage() {
        HomeController controller = new HomeController();
        Model model = new ExtendedModelMap();

        controller.index(model);

        assertNotNull(model.getAttribute("title"));
        assertNotNull(model.getAttribute("message"));
    }

    @Test
    void indexTitleMatchesHomepageTheme() {
        HomeController controller = new HomeController();
        Model model = new ExtendedModelMap();

        controller.index(model);

        String title = (String) model.getAttribute("title");

        assertNotNull(title);
        assertTrue(title.contains("Trail"));
    }
    //HomePage Ends---------------------------------------------------------------------------------------------------

    //View Trails Tests-----------------------------------------------------------------------------------------------
    @Test
    void trailsReturnsTrailsView() {
        HomeController controller = new HomeController();

        TrailRepository trailRepository = mock(TrailRepository.class);
        ReflectionTestUtils.setField(controller, "trailRepository", trailRepository);

        when(trailRepository.findAll()).thenReturn(List.of());

        Model model = new ExtendedModelMap();

        String viewName = controller.Trails(null, null, null, model);

        assertEquals("trails", viewName);
    }

    @Test
    void trailsAddsAttributesToModel() {
        HomeController controller = new HomeController();

        TrailRepository trailRepository = mock(TrailRepository.class);
        ReflectionTestUtils.setField(controller, "trailRepository", trailRepository);

        when(trailRepository.findAll()).thenReturn(List.of());

        Model model = new ExtendedModelMap();

        controller.Trails(null, null, null, model);

        assertEquals("Trails Explored", model.getAttribute("title"));
        assertNotNull(model.getAttribute("trails"));
    }

    @Test
    void trailsWithFiltersReturnsTrailsView() {
        HomeController controller = new HomeController();

        TrailRepository trailRepository = mock(TrailRepository.class);
        ReflectionTestUtils.setField(controller, "trailRepository", trailRepository);

        when(trailRepository.findAll()).thenReturn(List.of());

        Model model = new ExtendedModelMap();

        String viewName = controller.Trails("Easy", 5, "Table Rock", model);

        assertEquals("trails", viewName);
        assertEquals("Table Rock", model.getAttribute("name"));
        assertEquals("Easy", model.getAttribute("difficulty"));
        assertEquals(5, model.getAttribute("rating"));
    }
    //View Trails Ends-----------------------------------------------------------------------------------------------

    //Stats Page Tests-----------------------------------------------------------------------------------------------
    @Test
    void statsReturnsStatsView() {
        HomeController controller = new HomeController();

        TrailRepository trailRepository = mock(TrailRepository.class);
        ReflectionTestUtils.setField(controller, "trailRepository", trailRepository);

        when(trailRepository.findAll()).thenReturn(List.of());
        when(trailRepository.count()).thenReturn(0L);

        Model model = new ExtendedModelMap();

        String viewName = controller.stats(model);

        assertEquals("stats", viewName);
    }

    @Test
    void statsAddsCorrectModelAttributes() {
        HomeController controller = new HomeController();

        TrailRepository trailRepository = mock(TrailRepository.class);
        ReflectionTestUtils.setField(controller, "trailRepository", trailRepository);

        Trail trail1 = new Trail();
        trail1.setDistance(5.0);
        trail1.setRating(4);
        trail1.setDifficulty("Easy");
        trail1.setDate(LocalDate.of(2026, 1, 10));

        Trail trail2 = new Trail();
        trail2.setDistance(3.0);
        trail2.setRating(2);
        trail2.setDifficulty("Medium");
        trail2.setDate(LocalDate.of(2026, 2, 15));

        Trail trail3 = new Trail();
        trail3.setDistance(7.0);
        trail3.setRating(5);
        trail3.setDifficulty("Hard");
        trail3.setDate(LocalDate.of(2026, 2, 20));

        when(trailRepository.findAll()).thenReturn(List.of(trail1, trail2, trail3));
        when(trailRepository.count()).thenReturn(3L);

        Model model = new ExtendedModelMap();

        controller.stats(model);
        Double avg = (Double) model.getAttribute("averageRating");

        assertEquals("Trail Statistics", model.getAttribute("title"));
        assertEquals(3L, model.getAttribute("totalTrails"));
        assertEquals(15.0, model.getAttribute("totalDistance"));
        assertNotNull(avg);
        assertEquals(11.0 / 3.0, avg, 0.01);
        assertEquals(1, model.getAttribute("easyTrails"));
        assertEquals(1, model.getAttribute("mediumTrails"));
        assertEquals(1, model.getAttribute("hardTrails"));
        assertEquals(7.0, model.getAttribute("longestTrail"));
    }

    @Test
    void statsCalculatesTrailsPerMonth() {
        HomeController controller = new HomeController();

        TrailRepository trailRepository = mock(TrailRepository.class);
        ReflectionTestUtils.setField(controller, "trailRepository", trailRepository);

        Trail januaryTrail = new Trail();
        januaryTrail.setDistance(2.0);
        januaryTrail.setRating(3);
        januaryTrail.setDifficulty("Easy");
        januaryTrail.setDate(LocalDate.of(2026, 1, 5));

        Trail februaryTrail = new Trail();
        februaryTrail.setDistance(4.0);
        februaryTrail.setRating(4);
        februaryTrail.setDifficulty("Medium");
        februaryTrail.setDate(LocalDate.of(2026, 2, 10));

        when(trailRepository.findAll()).thenReturn(List.of(januaryTrail, februaryTrail));
        when(trailRepository.count()).thenReturn(2L);

        Model model = new ExtendedModelMap();

        controller.stats(model);

        int[] trailsPerMonth = (int[]) model.getAttribute("trailsPerMonth");

        assertNotNull(trailsPerMonth);
        assertEquals(1, trailsPerMonth[0]); // January
        assertEquals(1, trailsPerMonth[1]); // February
        assertEquals(0, trailsPerMonth[2]); // March
    }
    //Stats Page Ends-----------------------------------------------------------------------------------------------

    //Details Page Tests--------------------------------------------------------------------------------------------
    @Test
    void detailsReturnsDetailsView() {
        HomeController controller = new HomeController();

        TrailRepository trailRepository = mock(TrailRepository.class);
        ReflectionTestUtils.setField(controller, "trailRepository", trailRepository);

        when(trailRepository.findById(1)).thenReturn(Optional.of(new Trail()));

        Model model = new ExtendedModelMap();

        String viewName = controller.Trail(1, model);

        assertEquals("details", viewName);
        assertNotNull(model.getAttribute("trail"));
    }
    //Details Page Ends--------------------------------------------------------------------------------------------

    //Add Trail Tests----------------------------------------------------------------------------------------------
    @Test
    void addPageReturnsAddView() {
        HomeController controller = new HomeController();
        Model model = new ExtendedModelMap();

        String viewName = controller.addTrail(model);

        assertEquals("add", viewName);
        assertNotNull(model.getAttribute("trail"));
    }
    //Add Trail Ends-----------------------------------------------------------------------------------------------

    //Save Trail Tests---------------------------------------------------------------------------------------------
    @Test
    void addTrailRedirectsToTrails() {
        HomeController controller = new HomeController();

        TrailRepository trailRepository = mock(TrailRepository.class);
        ReflectionTestUtils.setField(controller, "trailRepository", trailRepository);

        Trail trail = new Trail();

        String viewName = controller.addTrail(trail);

        verify(trailRepository).save(trail);
        assertEquals("redirect:/trails", viewName);
    }
    //Save Trail Ends---------------------------------------------------------------------------------------------


    //Delete Trail Tests------------------------------------------------------------------------------------------
    @Test
    void deleteTrailRedirectsToTrails() {
        HomeController controller = new HomeController();

        TrailRepository trailRepository = mock(TrailRepository.class);
        ReflectionTestUtils.setField(controller, "trailRepository", trailRepository);

        String viewName = controller.deleteTrail(1);

        verify(trailRepository).deleteById(1);
        assertEquals("redirect:/trails", viewName);
    }
    //Delete Trail Ends------------------------------------------------------------------------------------------

    //Edit Trail Tests-------------------------------------------------------------------------------------------
    @Test
    void editTrailReturnsAddView() {
        HomeController controller = new HomeController();

        TrailRepository trailRepository = mock(TrailRepository.class);
        ReflectionTestUtils.setField(controller, "trailRepository", trailRepository);

        when(trailRepository.findById(1)).thenReturn(Optional.of(new Trail()));

        Model model = new ExtendedModelMap();

        String viewName = controller.editTrail(1, model);

        assertEquals("add", viewName);
        assertNotNull(model.getAttribute("trail"));
    }
    //Edit Trail Ends--------------------------------------------------------------------------------------------
}
