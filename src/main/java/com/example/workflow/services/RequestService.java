package com.example.workflow;

import java.lang.Math;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RequestService {
    private final int PAGE_SIZE = 3;

    @Autowired
    RequestRepository repository;
    @Autowired
    RequestNumberRepository requestNumberRepository;
    @Autowired
    ApplicantRepository applicantRepository;
    @Autowired
    AuthorizerRepository authorizerRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    DivisionRepository divisionRepository;
    @PersistenceContext
    private EntityManager em;

    public Request findById(Long id) {
        return repository.findById(id);
    }

    public List<Request> search(RequestSearch requestSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Request> cq = cb.createQuery(Request.class);
        Root<Request> r = cq.from(Request.class);
        cq.select(r);
        List<Predicate> criteria = this.setCriteria(cb, r, requestSearch);
        cq.where(cb.and(criteria.toArray(new Predicate[] { }))).orderBy(cb.desc(r.get("id")));
        return requestSearch.page == 0 ?
            em.createQuery(cq).getResultList() :
            em.createQuery(cq).setFirstResult((requestSearch.page - 1) * PAGE_SIZE).setMaxResults(PAGE_SIZE).getResultList();
    }

    public int getTotalPage(RequestSearch requestSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Request> r = cq.from(Request.class);
        cq.select(cb.count(r));
        List<Predicate> criteria = this.setCriteria(cb, r, requestSearch);
        cq.where(cb.and(criteria.toArray(new Predicate[] { })));
        return (int)Math.ceil(em.createQuery(cq).getSingleResult().doubleValue() / (double)PAGE_SIZE);
    }

    private List<Predicate> setCriteria(CriteriaBuilder cb, Root<Request> r, RequestSearch requestSearch) {
        Applicant applicant   = requestSearch.applicantId  == null ? null : applicantRepository.findById(requestSearch.applicantId);
        Authorizer authorizer = requestSearch.authorizerId == null ? null : authorizerRepository.findById(requestSearch.authorizerId);
        Status status         = requestSearch.statusId     == null ? null : statusRepository.findById(requestSearch.statusId);
        Category category     = requestSearch.categoryId   == null ? null : categoryRepository.findById(requestSearch.categoryId);
        Division division     = requestSearch.divisionId   == null ? null : divisionRepository.findById(requestSearch.divisionId);

        List<Predicate> criteria = new ArrayList<Predicate>();
        if(requestSearch.reqId != null) criteria.add(cb.equal(r.get("reqId"), requestSearch.reqId));
        if(requestSearch.title != null) criteria.add(cb.like(r.get("title"), "%" + requestSearch.title + "%"));
        if(applicant != null)           criteria.add(cb.equal(r.get("applicant"), applicant));
        if(authorizer != null)          criteria.add(cb.equal(r.get("authorizer"), authorizer));
        if(status != null)              criteria.add(cb.equal(r.get("status"), status));
        if(category != null)            criteria.add(cb.equal(r.get("category"), category));
        if(division != null)            criteria.add(cb.equal(r.get("division"), division));

        return criteria;
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
