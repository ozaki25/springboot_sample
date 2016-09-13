package com.example.workflow;

import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RequestService {
    @Autowired
    RequestRepository repository;
    @Autowired
    RequestNumberRepository requestNumberRepository;
    @Autowired
    DivisionRepository divisionRepository;

    public Request findById(Long id) {
        return repository.findById(id);
    }

    public List<Request> findAll() {
        return repository.findAll();
    }

    public Page<Request> findAll(int page, int size) {
        return repository.findAll(new PageRequest(page, size));
    }

    public Request save(Request request) {
        if(request.getReqId() == null) request.setReqId(this.getReqId(request));
        request.setCategory(this.getCategory(request));
        return repository.save(request);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    private String getReqId(Request request) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        RequestNumber currentRequestNumber = requestNumberRepository.findByYear(year);
        if(currentRequestNumber == null) {
            RequestNumber requestNumber = new RequestNumber(year);
            requestNumberRepository.save(requestNumber);
            return  this.generateReqId(requestNumber);
        } else {
            currentRequestNumber.setNumber(currentRequestNumber.getNumber() + 1);
            requestNumberRepository.save(currentRequestNumber);
            return this.generateReqId(currentRequestNumber);
        }
    }

    private String generateReqId(RequestNumber requestNumber) {
        return Integer.toString(requestNumber.getYear()) + "-" + String.format("%05d", requestNumber.getNumber());
    }

    private Category getCategory(Request request) {
        Long id = request.getDivision().getId();
        return divisionRepository.findById(id).getCategory();
    }
}
