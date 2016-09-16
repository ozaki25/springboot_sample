package com.example.workflow;

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
public class ApplicantService {
    private final int PAGE_SIZE = 3;

    @Autowired
    ApplicantRepository repository;
    @PersistenceContext
    private EntityManager em;

    public Applicant findById(Long id) {
        return repository.findById(id);
    }

    public List<Applicant> findAll() {
        return repository.findAll();
    }

    public List<Applicant> findDistinct(String param) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Applicant> cq = cb.createQuery(Applicant.class);
        Root<Applicant> r = cq.from(Applicant.class);
        try {
            cq.select(r.get(param)).distinct(true).orderBy(cb.asc(r.get(param)));
        } catch(IllegalArgumentException e) {
            return new ArrayList<Applicant>();
        }
        return em.createQuery(cq).getResultList();
    }

    public Applicant save(Applicant applicant) {
        return repository.save(applicant);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
