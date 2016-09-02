package com.example.workflow;

import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RequestService {
    @Autowired
    RequestRepository repository;
    @Autowired
    RequestNumberRepository requestNumberRepository;

    public Request findById(Long id){
        return repository.findById(id);
    }
    public List<Request> findAll(){
        return repository.findAll();
    }
    public Request save(Request request){
        if(request.getReqId() == null) {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            RequestNumber currentRequestNumber = requestNumberRepository.findByYear(year);
            String reqId;
            if(currentRequestNumber == null) {
                RequestNumber requestNumber = new RequestNumber(year);
                requestNumberRepository.save(requestNumber);
                reqId = this.generateReqId(requestNumber);
            } else {
                currentRequestNumber.setNumber(currentRequestNumber.getNumber() + 1);
                reqId = this.generateReqId(currentRequestNumber);
            }
            request.setReqId(reqId);
        }
        return repository.save(request);
    }
    public void delete(Long id){
        repository.delete(id);
    }

    private String  generateReqId(RequestNumber requestNumber) {
        return Integer.toString(requestNumber.getYear()) + "-" + String.format("%05d", requestNumber.getNumber());
    }
}
