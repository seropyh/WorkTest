
package com.example.test.model;

import java.util.List;

import com.example.test.model.ManagerList_;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "managerList"
})
public class ManagerList {

    @JsonProperty("managerList")
    private List<ManagerList_> managerList = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ManagerList() {
    }

    /**
     * 
     * @param managerList
     */
    public ManagerList(List<ManagerList_> managerList) {
        super();
        this.managerList = managerList;
    }

    @JsonProperty("managerList")
    public List<ManagerList_> getManagerList() {
        return managerList;
    }

    @JsonProperty("managerList")
    public void setManagerList(List<ManagerList_> managerList) {
        this.managerList = managerList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("managerList", managerList).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(managerList).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ManagerList) == false) {
            return false;
        }
        ManagerList rhs = ((ManagerList) other);
        return new EqualsBuilder().append(managerList, rhs.managerList).isEquals();
    }

}
