/*
package com.example.warehouseinventoryservice;
p
import com.example.sessionservice.dto.ErrorDetails;
import com.example.sessionservice.dto.Summary;
import com.example.sessionservice.exception.NotFoundException;
import com.example.sessionservice.model.ChargingSession;
import com.example.sessionservice.service.impl.DefaultSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = {"chargingSessions", "/charging/sessions"})
@Tag(name = "Session", description = "Charging Session API")
@Slf4j
public class SessionController {

    private final DefaultSessionService sessionService;

    @Autowired
    public SessionController(final DefaultSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Operation(summary = "Get all charging sessions")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ChargingSession> getAllChargingSessions() {
        return sessionService.fetchAllSessions();
    }

    @Operation(summary = "Start a new charging session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Charging session successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ChargingSession createNewSession(
            @Parameter(description = "Station Id for new session. Cannot be null or empty.", required = true)
            @RequestBody @Valid ChargingSession session) {
        log.debug("Request body :: " + session);
        return sessionService.startNewSession(session.getStationId());
    }

    @Operation(summary = "End a charging session by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Session not found",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    })
    @PutMapping(path = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ChargingSession endChargingSession(
            @Parameter(description = "Id of the charging session to be obtained. Cannot be empty.", required = true)
            @PathVariable UUID id) throws NotFoundException {
        return sessionService.endChargingSessionById(id);
    }

    @Operation(summary = "Get a charging session by id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ChargingSession findSessionById(
            @Parameter(description = "Id of the charging session to be obtained. Cannot be empty.", required = true)
            @PathVariable UUID id) throws NotFoundException {
        return sessionService.fetchSessionById(id);
    }

    @Operation(summary = "Get summary of operations")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping(path = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
    public Summary getSummary() {
        return sessionService.getSessionSummary();
    }
}
*/
