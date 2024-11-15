package com.redhat.widget.rest;

import java.io.IOException;

import com.redhat.widget.helper.DatabaseHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WidgetRestControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(WidgetRestControllerTest.class);

    private static final String DONTCARE = "dontcare";

    private static final String DONTCARE2 = "dont care";

    private static DatabaseHelper databaseHelper;

    @BeforeAll
    public static void setUp(@Autowired DatabaseHelper databaseHelper) throws IOException, InterruptedException {

        WidgetRestControllerTest.databaseHelper = databaseHelper;

        databaseHelper.deleteAll();
    }

    @AfterAll
    public static void tearDown() throws IOException, InterruptedException {

        databaseHelper.deleteAll();
    }

    @Test
    public void testFindWidgetByName() throws IOException, InterruptedException {

        Widget widget = databaseHelper.create(DONTCARE2, DONTCARE);
        Widget widget2 = databaseHelper.findByName(DONTCARE2);

        assertThat(widget2).isNotNull();
        assertThat(widget2).isEqualTo(widget);
    }

}
