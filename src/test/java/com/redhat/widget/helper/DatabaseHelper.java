package com.redhat.widget.helper;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.redhat.widget.rest.Widget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHelper {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseHelper.class);

    @Value("${widget.rest.url}")
    private String widgetRestUrl;

    @Autowired
    private ObjectMapper objectMapper;

    public Widget findByName(String name) throws IOException, InterruptedException {

        String encName = URLEncoder.encode(name, StandardCharsets.UTF_8);

        String command = "curl -X 'GET' " +
                "  '" +
                widgetRestUrl +
                "/name/" +
                encName +
                "' " +
                "  -H 'accept: application/json'";
        List<String> result = Shell.execute(command);

        Widget retval = null;
        if (!result.isEmpty()) {
            try {
                retval = objectMapper.readValue(result.get(0), Widget.class);
                LOG.info("{}", retval);
            } catch (UnrecognizedPropertyException ex) {
                try {
                    ErrorResponse errorResponse = objectMapper.readValue(result.get(0), ErrorResponse.class);
                    LOG.info("{}", errorResponse);
                } catch (Throwable t) {
                    LOG.error(t.getMessage(), t);
                }
            }
        } else {
            LOG.warn("No widget found with name {}", name);
        }

        return retval;
    }

    public Widget create(String name, String description) throws IOException, InterruptedException {

        String encName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        String encDescription = URLEncoder.encode(description, StandardCharsets.UTF_8);

        String command = "curl -X 'PUT' " +
                "  '" +
                widgetRestUrl +
                "' -H 'accept: */*' " +
                "  -H 'Content-Type: application/json' " +
                "  -d '{ " +
                "  \"id\": null, " +
                "  \"name\": \"" +
                encName +
                "\", " +
                "  \"description\": \"" +
                encDescription +
                "\" " +
                "}'";
        List<String> result = Shell.execute(command);

        Widget retval = null;
        if (!result.isEmpty()) {
            try {
                retval = objectMapper.readValue(result.get(0), Widget.class);
                LOG.info("{}", retval);
            } catch (UnrecognizedPropertyException ex) {
                try {
                    ErrorResponse errorResponse = objectMapper.readValue(result.get(0), ErrorResponse.class);
                    LOG.info("{}", errorResponse);
                } catch (Throwable t) {
                    LOG.error(t.getMessage(), t);
                }
            }
        } else {
            LOG.warn("Could not create widget with name {}", name);
        }

        return retval;
    }

    public void deleteAll() throws IOException, InterruptedException {

        String command = "curl -X DELETE " +
                "  '" +
                widgetRestUrl +
                "/deleteAll'" +
                "  -H 'accept: application/json'";
        List<String> result = Shell.execute(command);

        if (!result.isEmpty()) {
            try {
                LOG.debug("{}", result);
            } catch (Throwable ex) {
                try {
                    ErrorResponse errorResponse = objectMapper.readValue(result.get(0), ErrorResponse.class);
                    LOG.warn("{}", errorResponse);
                } catch (Throwable t) {
                    LOG.error(t.getMessage(), t);
                }
            }
        }
    }

}
