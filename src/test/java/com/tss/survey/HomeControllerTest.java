package com.tss.survey;

import com.tss.survey.repository.TrailRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

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

}
