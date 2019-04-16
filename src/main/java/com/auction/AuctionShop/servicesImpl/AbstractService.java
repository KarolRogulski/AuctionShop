package com.auction.AuctionShop.servicesImpl;

import com.auction.AuctionShop.entities.AbstractEntity;
import com.auction.AuctionShop.exceptions.NotFoundException;
import com.auction.AuctionShop.repositories.Repository;
import com.auction.AuctionShop.services.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public abstract class AbstractService<T extends AbstractEntity> implements Service<T> {

    @Autowired
    private Repository<T> repository;
    public static final Logger log = LogManager.getLogger();

    //TODO should throw exception
    @Override
    public void save(T entity){
        repository.save(entity);
    }

    @Override
    public List<T> getAll(){
        log.info("Returning all " + repository.getClazz());
        return repository.getAll();
    }

    @Override
    public T findById(long id)throws NotFoundException {
       try{
           return repository.findById(id);
       }
       catch (EmptyResultDataAccessException e){
           throw getException(id);
       }
    }

    @Override
    public void update(T entity) throws NotFoundException{
        log.info("Updated " + repository.getClazz() + " with id: " + entity.getId());
        try{
            repository.update(entity);
        }
        catch(EmptyResultDataAccessException e){
            throw getException(entity.getId());
        }
    }

    @Override
    public int delete(T entity){
        int result = repository.delete(entity);
        log.info("Deleting ended with result: " + result);
        return result;
    }

    //id parameter is inserted into exception message
    //Returning NotFoundException
    private NotFoundException getException(long id){
        return new NotFoundException(repository.getClazz() + " with id: " + id + " can't be found");
    }
}
