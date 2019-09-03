package com.umpisa.exam.server.jpa;

import com.umpisa.exam.server.models.Model;
import com.umpisa.exam.server.utils.*;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static com.umpisa.exam.server.jpa.JPAUtil.*;

public class Repository<T extends Model> {
  private final Class<T> clazz;

  public Repository(Class<T> clazz) {
    this.clazz = clazz;
  }

  public Optional<T> get(String id) {
    return Optional.ofNullable(getEntityManager().find(clazz, id));
  }

  public Optional<T> get(BiFunction<CriteriaBuilder, Root, Expression<Boolean>> finder) {
    EntityManager em = getEntityManager();
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<T> crit = builder.createQuery(clazz);
    Root<T> path = crit.from(clazz);

    crit.where(finder.apply(builder, path));
    return em.createQuery(crit)
      .setMaxResults(1)
      .getResultList()
      .stream()
      .findFirst();
  }

  public List<Order> getOrder(ListOpts opts, CriteriaBuilder builder, Root<T> path) {
    return opts.getSort().stream().map(sort ->
      sort.getSeq() == SortSeq.ASC ?
        builder.asc(path.get(sort.getName())) :
        builder.desc(path.get(sort.getName()))
    ).collect(Collectors.toList());
  }

  public PagedList<T> filter(ListOpts opts, BiFunction<CriteriaBuilder, Root<T>, Expression<Boolean>> finder) {
    EntityManager em = getEntityManager();
    CriteriaBuilder builder = em.getCriteriaBuilder();
    // Get total
    CriteriaQuery<Long> countCrit = builder.createQuery(Long.class);
    Root<T> countPath = countCrit.from(clazz);
    countCrit
      .select(builder.count(countPath))
      .where(finder.apply(builder, countPath));
    Long total = em.createQuery(countCrit).getSingleResult();

    // Get list
    CriteriaQuery<T> crit = builder.createQuery(clazz);
    Root<T> path = crit.from(clazz);
    crit
      .where(finder.apply(builder, path))
      .orderBy(getOrder(opts, builder, path));
    TypedQuery<T> query = em.createQuery(crit);

    opts.getMax().ifPresent(query::setMaxResults);
    opts.getOffset().ifPresent(query::setFirstResult);

    return new PagedList<>(query.getResultList(), total);
  }

  public PagedList<T> list(ListOpts opts) {
    EntityManager em = getEntityManager();
    CriteriaBuilder builder = em.getCriteriaBuilder();

    // Get total
    CriteriaQuery<Long> count = builder.createQuery(Long.class);
    count.select(builder.count(count.from(clazz)));
    Long total = em.createQuery(count).getSingleResult();

    // Get list
    CriteriaQuery<T> crit = builder.createQuery(clazz);
    Root<T> path = crit.from(clazz);
    crit.orderBy(getOrder(opts, builder, path));

    TypedQuery query = em.createQuery(crit);

    opts.getMax().ifPresent(query::setMaxResults);
    opts.getOffset().ifPresent(query::setFirstResult);

    return new PagedList<>(query.getResultList(), total);
  }
}
