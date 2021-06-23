package kr.co.greentech.measure.repository;

import kr.co.greentech.measure.domain.Company;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CompanyRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Company company) {
        em.persist(company);
    }

    public Company updateIp(
            String name,
            String ip
    ) {
        Company company = findOne(name);
        if (company != null) {
            company.setIp(ip);
        }
        return company;
    }

    public Company findOne(Long id) {
        return em.find(Company.class, id);
    }

    public Company findOne(String name) {
        Optional<Company> company = em.createQuery(
                "select c from Company c where c.name = :name",
                Company.class
        ).setParameter("name", name)
                .getResultList()
                .stream().findFirst();

        if (company.isPresent()) {
            return company.get();
        } else {
            return null;
        }
    }

    public List<Company> findAll() {
        return em
                .createQuery(
                        "select c from Company c",
                        Company.class
                )
                .getResultList();
    }
}
