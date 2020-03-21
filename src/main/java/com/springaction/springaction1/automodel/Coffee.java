package com.springaction.springaction1.automodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Coffee implements Serializable {
    private Long id;

    private String name;

    private BigDecimal price;

    private Date updatetime;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public Coffee withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Coffee withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Coffee withPrice(BigDecimal price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public Coffee withUpdatetime(Date updatetime) {
        this.setUpdatetime(updatetime);
        return this;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Coffee withCreatetime(Date createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}