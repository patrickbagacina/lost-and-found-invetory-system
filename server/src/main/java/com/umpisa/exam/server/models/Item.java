package com.umpisa.exam.server.models;

import com.umpisa.exam.server.jpa.Repository;
import com.umpisa.exam.server.utils.ListOpts;
import com.umpisa.exam.server.utils.PagedList;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;
import javax.persistence.criteria.Predicate;

@Entity
@Table(name = "[item]")
public class Item extends Model {
  private static final Repository<Item> repo = new Repository<>(Item.class);

  @Column
  @Enumerated(EnumType.STRING)
  private Category category;

  @Column(nullable = false, columnDefinition = "varchar(255)")
  @NotNull
  @Size(min = 1, max = 255)
  @NotBlank
  private String type;

  @Column(nullable = false, columnDefinition = "varchar(255)")
  @NotNull
  @Size(min = 1, max = 255)
  @NotBlank
  private String brand;

  @Column(nullable = false, columnDefinition = "varchar(255)")
  @NotNull
  @Size(min = 1, max = 1024)
  @NotBlank
  private String description;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn
  private User owner;

  @Column(nullable = false)
  private Boolean isRedeemed = false;

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Item withCategory(Category category) {
    setCategory(category);
    return this;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Item withType(String type) {
    setType(type);
    return this;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public Item withBrand(String brand) {
    setBrand(brand);
    return this;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Item withDescription(String desc) {
    setDescription(desc);
    return this;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public Item withOwner(User owner) {
    setOwner(owner);
    return this;
  }

  public Boolean getRedeemed() {
    return isRedeemed;
  }

  public void setRedeemed(Boolean redeemed) {
    isRedeemed = redeemed;
  }

  public Item withRedeemed(Boolean redeemed) {
    setRedeemed(redeemed);
    return this;
  }

  public static Optional<Item> get(String id) {
    return repo.get(id);
  }

  public static PagedList<Item> list(ListOpts opts) {
    return repo.list(opts);
  }

  public static PagedList<Item> listByFlag(ListOpts opts, Boolean isRedeemed) {
    return repo.filter(opts, (crit, path) -> {
      List<Predicate> criteria = new ArrayList<>();
      criteria.add(
        crit.equal(path.get("isRedeemed"), isRedeemed)
      );

      return crit.or(criteria.toArray(new Predicate[0]));
    });
  }
}
