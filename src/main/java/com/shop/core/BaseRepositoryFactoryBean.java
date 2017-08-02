package com.shop.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by meg on 7/25/17.
 */

/**
 * The RepositoryFactoryBean is a component that is responsible of providing implementations for Spring Data JPA repository interfaces. Because we want to replace the default implementation (SimpleJpaRepository) with our custom implementation (BaseRepositoryImpl), we have to create a custom RepositoryFactoryBean.
 * Create a BaseRepositoryFactoryBean class that has three type parameters:
 * @param <R> The R type parameter is the type of the repository. This type parameter must extend the JpaRepository interface.
 * @param <T> The T type parameter is the type of the managed entity.
 * @param <I> The I type parameter is the type of the entity’s private key. Note that this type parameter must extend the Serializable interface.
 *Extend the JpaRepositoryFactoryBean class and provide the required type parameters.
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new BaseRepositoryFactory(em);
    }

    private static class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

        private final EntityManager em;

        public BaseRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }

//        //@Override
//        protected Object getTargetRepository(RepositoryMetadata metadata) {
//            //return new BaseRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), em);
//        }
//
//        @Override
//        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
//
//            return BaseRepositoryImpl.class;
//        }

        @Override

        protected BaseRepositoryImpl getTargetRepository(RepositoryInformation information) {

            JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());

            return new BaseRepositoryImpl<>(entityInformation , em);

        }



        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

            //return MyRepositoryImpl.class;

            return BaseRepositoryImpl.class;

        }

    }
    }




