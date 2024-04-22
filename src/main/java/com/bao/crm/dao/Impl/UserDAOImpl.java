package com.bao.crm.dao.Impl;

import com.bao.crm.dao.UserDAO;
import com.bao.crm.dto.UserParams;
import com.bao.crm.constant.SortUser;
import com.bao.crm.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<User> getUsers(UserParams params) {
        String search = params.getSearch();
        String sort = params.getSort();

        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);


        List<Predicate> predicates = new ArrayList<>();

        if(search != null && !search.trim().isEmpty()){
            predicates.add(builder.or(
                    builder.like(builder.lower(root.get("username")), "%" + search.toLowerCase() + "%")
            ));
        }

        query.where(predicates.toArray(new Predicate[0]));

        if(sort != null && !sort.isEmpty()){
            switch (sort){
                case SortUser.USER_NAME_ASC:
                    query.orderBy(builder.asc(root.get(SortUser.USER_NAME)));
                    break;
                case SortUser.USER_NAME_DESC:
                    query.orderBy(builder.desc(root.get(SortUser.USER_NAME)));
                    break;
                case SortUser.EMAIL_ASC:
                    query.orderBy(builder.asc(root.get(SortUser.EMAIL)));
                    break;
                case SortUser.EMAIL_DESC:
                    query.orderBy(builder.desc(root.get(SortUser.EMAIL)));
                    break;
                default:
                    query.orderBy(builder.asc(root.get("id")));
                    break;
            }
        }

        int page = params.getPageNumber();
        int pageSize = params.getPageSize();
        int firstResult = (page - 1) * pageSize;

        return sessionFactory.getCurrentSession()
                .createQuery(query)
                .setFirstResult(firstResult)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    @Transactional
    public User findByUserName(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User where username=:username", User.class);
        query.setParameter("username", username);
        User result;
        try{
             result = query.getSingleResult();
        }catch (Exception ex){
            return null;
        }
        return result;
    }

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return user;
    }

    @Override
    public User getUserbyEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Query<User> query = session.createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        if (!users.isEmpty()) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public int getUserCount(UserParams params) {
        String search = params.getSearch();

        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        if(search != null && !search.trim().isEmpty()){
            predicates.add(builder.or(
                    builder.like(builder.lower(root.get("username")), "%" + search.toLowerCase() + "%")
            ));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return Math.toIntExact(sessionFactory.getCurrentSession()
                .createQuery(query)
                .getResultList()
                .size());
    }

    @Override
    public void saveUserDto(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Override
    public void saveUser(User user){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Override
    public void deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        session.delete(user);
    }
}
