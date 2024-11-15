package com.redhat.widget.helper;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.redhat.widget.config.OffsetDateTimeDeserializer;
import com.redhat.widget.config.OffsetDateTimeSerializer;

public class ErrorResponse {

    private static final String OFFSET_DATE_TIME_FORMAT = "yyyy-MM-dd'T'hh:mm:ss.SSSZ";

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = OFFSET_DATE_TIME_FORMAT)
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
    private OffsetDateTime timestamp;

    private long status;

    private String error;

    private String message;

    private String path;

    private String trace;

    public OffsetDateTime getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {

        this.timestamp = timestamp;
    }

    public long getStatus() {

        return status;
    }

    public void setStatus(long status) {

        this.status = status;
    }

    public String getError() {

        return error;
    }

    public void setError(String error) {

        this.error = error;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public String getPath() {

        return path;
    }

    public void setPath(String path) {

        this.path = path;
    }

    public String getTrace() {

        return trace;
    }

    public void setTrace(String trace) {

        this.trace = trace;
    }

}
