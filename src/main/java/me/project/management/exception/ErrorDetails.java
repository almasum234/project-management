package me.project.management.exception;

import lombok.Data;

import java.util.Date;

/**
 * @author Abdullah Al Masum
 * @version 1.0
 * @since 13-09-2022
 */
@Data
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

}
