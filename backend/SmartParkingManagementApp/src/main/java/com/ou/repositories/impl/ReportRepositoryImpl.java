package com.ou.repositories.Impl;

import com.ou.pojo.Report;
import com.ou.repositories.ReportRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ReportRepositoryImpl implements ReportRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Report save(Report report) {
        Session session = this.factory.getObject().getCurrentSession();
        session.saveOrUpdate(report);
        return report;
    }

}
