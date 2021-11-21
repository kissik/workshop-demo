package edu.kpi.iasa.mmsa.workshop.service;

import edu.kpi.iasa.mmsa.workshop.exception.StatusNotFoundException;
import edu.kpi.iasa.mmsa.workshop.model.Status;
import edu.kpi.iasa.mmsa.workshop.repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getStatuses() {
        return statusRepository.findAll();
    }

    public Status saveStatus(Status newStatus) {
        return statusRepository.save(newStatus);
    }

    public Status getStatusById(Long id) {
        Optional<Status> status = statusRepository.findById(id);
        if (status.isPresent()) {
            log.info("status: {}", status.get());
            return status.get();
        }
        throw new StatusNotFoundException();
    }

    public Status updateStatusById(Long id, Status updatedStatus) {
        Optional<Status> status = statusRepository.findById(id);
        if (status.isPresent()) {
            Status oldStatus = status.get();
            log.info("status: {}", oldStatus);
            updateStatus(oldStatus, updatedStatus);
            return statusRepository.save(oldStatus);
        }
        throw new StatusNotFoundException();
    }

    private void updateStatus(Status oldStatus, Status updatedStatus) {
        oldStatus.setName(updatedStatus.getName());
        oldStatus.setCode(updatedStatus.getCode());
        oldStatus.setClosed(updatedStatus.getClosed());
    }

    public String deleteStatusById(Long id) {
        statusRepository.deleteById(id);
        return "Status was successfully deleted!";
    }
}
