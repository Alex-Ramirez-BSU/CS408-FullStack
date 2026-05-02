package com.tss.survey;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

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
}
