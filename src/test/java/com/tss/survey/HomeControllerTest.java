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
        assertEquals("Hello World", model.getAttribute("title"));
        assertEquals("This is a template with bootstrap.", model.getAttribute("message"));
    }
}
