
package com.example.test.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * version 1.0
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "serviceName",
    "managerLogin",
    "serviceTimeStart",
    "serviceTimeEnd"
})
public class ServiceRequest {

    @JsonProperty("serviceName")
    private String serviceName;
    @JsonProperty("managerLogin")
    private String managerLogin;
    @JsonProperty("serviceTimeStart")
    private Date serviceTimeStart;
    @JsonProperty("serviceTimeEnd")
    private String serviceTimeEnd;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ServiceRequest() {
    }

    /**
     * 
     * @param serviceTimeStart
     * @param serviceTimeEnd
     * @param serviceName
     * @param managerLogin
     */
    public ServiceRequest(String serviceName, String managerLogin, Date serviceTimeStart, String serviceTimeEnd) {
        super();
        this.serviceName = serviceName;
        this.managerLogin = managerLogin;
        this.serviceTimeStart = serviceTimeStart;
        this.serviceTimeEnd = serviceTimeEnd;
    }

    @JsonProperty("serviceName")
    public String getServiceName() {
        return serviceName;
    }

    @JsonProperty("serviceName")
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @JsonProperty("managerLogin")
    public String getManagerLogin() {
        return managerLogin;
    }

    @JsonProperty("managerLogin")
    public void setManagerLogin(String managerLogin) {
        this.managerLogin = managerLogin;
    }

    @JsonProperty("serviceTimeStart")
    public Date getServiceTimeStart() {
        return serviceTimeStart;
    }

    @JsonProperty("serviceTimeStart")
    public void setServiceTimeStart(Date serviceTimeStart) {
        this.serviceTimeStart = serviceTimeStart;
    }

    @JsonProperty("serviceTimeEnd")
    public String getServiceTimeEnd() {
        return serviceTimeEnd;
    }

    @JsonProperty("serviceTimeEnd")
    public void setServiceTimeEnd(String serviceTimeEnd) {
        this.serviceTimeEnd = serviceTimeEnd;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("serviceName", serviceName).append("managerLogin", managerLogin).append("serviceTimeStart", serviceTimeStart).append("serviceTimeEnd", serviceTimeEnd).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(serviceTimeStart).append(serviceName).append(serviceTimeEnd).append(managerLogin).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ServiceRequest) == false) {
            return false;
        }
        ServiceRequest rhs = ((ServiceRequest) other);
        return new EqualsBuilder().append(serviceTimeStart, rhs.serviceTimeStart).append(serviceName, rhs.serviceName).append(serviceTimeEnd, rhs.serviceTimeEnd).append(managerLogin, rhs.managerLogin).isEquals();
    }

}
