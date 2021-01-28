
package com.example.test.model;

import java.util.List;

import com.example.test.ServiceList;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "login",
    "serviceList"
})
public class ManagerList_ {

    @JsonProperty("login")
    private String login;
    @JsonProperty("serviceList")
    private List<com.example.test.ServiceList> serviceList = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ManagerList_() {
    }

    /**
     * 
     * @param serviceList
     * @param login
     */
    public ManagerList_(String login, List<ServiceList> serviceList) {
        super();
        this.login = login;
        this.serviceList = serviceList;
    }

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty("serviceList")
    public List<ServiceList> getServiceList() {
        return serviceList;
    }

    @JsonProperty("serviceList")
    public void setServiceList(List<ServiceList> serviceList) {
        this.serviceList = serviceList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("login", login).append("serviceList", serviceList).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(login).append(serviceList).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ManagerList_) == false) {
            return false;
        }
        ManagerList_ rhs = ((ManagerList_) other);
        return new EqualsBuilder().append(login, rhs.login).append(serviceList, rhs.serviceList).isEquals();
    }

}
