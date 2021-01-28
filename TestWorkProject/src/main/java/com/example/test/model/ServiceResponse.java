
package com.example.test;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "success",
    "message"
})
public class ServiceResponse {

    @JsonProperty("success")
    private ServiceResponse.Success success;
    @JsonProperty("message")
    private String message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ServiceResponse() {
    }

    /**
     * 
     * @param success
     * @param message
     */
    public ServiceResponse(ServiceResponse.Success success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    @JsonProperty("success")
    public ServiceResponse.Success getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(ServiceResponse.Success success) {
        this.success = success;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("success", success).append("message", message).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(success).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ServiceResponse) == false) {
            return false;
        }
        ServiceResponse rhs = ((ServiceResponse) other);
        return new EqualsBuilder().append(message, rhs.message).append(success, rhs.success).isEquals();
    }

    public enum Success {

        OK("ok"),
        ERROR("error");
        private final String value;
        private final static Map<String, ServiceResponse.Success> CONSTANTS = new HashMap<String, ServiceResponse.Success>();

        static {
            for (ServiceResponse.Success c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Success(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static ServiceResponse.Success fromValue(String value) {
            ServiceResponse.Success constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
