
package com.example.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "serviceName",
    "serviceTimeStart",
    "serviceTimeEnd"
})
public class ServiceList {

    @JsonProperty("serviceName")
    private String serviceName;
    @JsonProperty("serviceTimeStart")
    private String serviceTimeStart;
    @JsonProperty("serviceTimeEnd")
    private String serviceTimeEnd;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ServiceList() {
    }

    /**
     * 
     * @param serviceTimeStart
     * @param serviceTimeEnd
     * @param serviceName
     */
    public ServiceList(String serviceName, String serviceTimeStart, String serviceTimeEnd) {
        super();
        this.serviceName = serviceName;
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

    @JsonProperty("serviceTimeStart")
    public String getServiceTimeStart() {
        return serviceTimeStart;
    }

    @JsonProperty("serviceTimeStart")
    public void setServiceTimeStart(String serviceTimeStart) {
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
        return new ToStringBuilder(this).append("serviceName", serviceName).append("serviceTimeStart", serviceTimeStart).append("serviceTimeEnd", serviceTimeEnd).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(serviceTimeStart).append(serviceName).append(serviceTimeEnd).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ServiceList) == false) {
            return false;
        }
        ServiceList rhs = ((ServiceList) other);
        return new EqualsBuilder().append(serviceTimeStart, rhs.serviceTimeStart).append(serviceName, rhs.serviceName).append(serviceTimeEnd, rhs.serviceTimeEnd).isEquals();
    }

}
