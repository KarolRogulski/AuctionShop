package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@Repository
@Transactional
public class UserDaoImpl extends AbstractRepository<User> implements UserDao {

    private SessionFactory sessionFactory;
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        super.setClazz(User.class);
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    //Return user by given login
    @Override
    public User findByLogin(String login) {
        log.info("Get user with login: " + login);
        CriteriaBuilder builder = getSession().getCriteriaBuilder();

        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);

        ParameterExpression<String> loginParameter = builder.parameter(String.class);
        criteria.where(builder.equal(root.get("login"), loginParameter));

        Query<User> query = getSession().createQuery(criteria);
        query.setParameter(loginParameter, login);
        return query.getSingleResult();
    }

    @Override
    public void update(User userUpdated) {
        log.info("Update user with id= " + userUpdated.getId());
        User userFromDB = this.findById(userUpdated.getId());
        userFromDB.setLogin(userUpdated.getLogin());
        userFromDB.setEmail(userUpdated.getEmail());
        userFromDB.setPassword(userUpdated.getPassword());
        userFromDB.setDateOfBirth(userUpdated.getDateOfBirth());
    }

}
