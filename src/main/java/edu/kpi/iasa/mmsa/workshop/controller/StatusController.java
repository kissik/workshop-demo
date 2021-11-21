package edu.kpi.iasa.mmsa.workshop.controller;

import edu.kpi.iasa.mmsa.workshop.model.Status;
import edu.kpi.iasa.mmsa.workshop.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping(value = "/status")
    public ResponseEntity<List<Status>> getStatuses() {
        return ResponseEntity.ok(statusService.getStatuses());
    }

    @PostMapping(value = "/status")
    public ResponseEntity<Status> postStatuses(@Valid @RequestBody Status newStatus) {
        return ResponseEntity.ok(statusService.saveStatus(newStatus));
    }

    @GetMapping(value = "/status/{id}")
    public ResponseEntity<Status> getStatus(@PathVariable Long id) {
        return ResponseEntity.ok(statusService.getStatusById(id));
    }

    @PutMapping(value = "/status/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable Long id, @Valid @RequestBody Status updatedStatus) {
        return ResponseEntity.ok(statusService.updateStatusById(id, updatedStatus));
    }

    @DeleteMapping(value = "/status/{id}")
    public ResponseEntity<String> deleteStatus(@PathVariable Long id) {
        return ResponseEntity.ok(statusService.deleteStatusById(id));
    }

}
