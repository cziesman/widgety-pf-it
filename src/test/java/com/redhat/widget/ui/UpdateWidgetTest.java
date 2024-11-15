package com.redhat.widget.ui;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.springframework.beans.factory.annotation.Value;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UpdateWidgetTest extends AbstractSeleniumTest {

    @Value("${widget.baseurl}")
    private String baseUrl;

    @Test
    @Order(1)
    void givenWeCanViewTheWidgetsPage() {

        WEB_DRIVER.get(baseUrl);
        WEB_DRIVER.manage().window().setSize(new Dimension(1363, 863));
        WEB_DRIVER.findElement(By.cssSelector("#form\\3Aj_idt8 > .ui-button-text")).click();
        WEB_DRIVER.findElement(By.id("dialogs:name")).sendKeys("a-widget");
        WEB_DRIVER.findElement(By.id("dialogs:description")).sendKeys("a really cool widget");
        WEB_DRIVER.findElement(By.cssSelector("#dialogs\\3Aj_idt33 > .ui-button-text")).click();
    }

    @Test
    @Order(2)
    void whenWeUpdateAWidget() {

        WEB_DRIVER.findElement(By.cssSelector(".pi-pencil")).click();
        WEB_DRIVER.findElement(By.id("dialogs:description")).click();
        WEB_DRIVER.findElement(By.id("dialogs:description")).sendKeys("a really really cool widget");
        WEB_DRIVER.findElement(By.cssSelector("#dialogs\\3Aj_idt33 > .ui-button-text")).click();
    }

    @Test
    @Order(3)
    void thenTheUpdatedWidgetAppearsInTheList() {

        WEB_DRIVER.findElement(By.cssSelector("td:nth-child(3)")).click();
    }

}
