
package site.zhangsun.utility.jsonschema2pojo;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Test {

    private String status;
    private Regeocode regeocode;
    private String info;
    private String infocode;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Regeocode getRegeocode() {
        return regeocode;
    }

    public void setRegeocode(Regeocode regeocode) {
        this.regeocode = regeocode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(status).append(regeocode).append(info).append(infocode).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Test) == false) {
            return false;
        }
        Test rhs = ((Test) other);
        return new EqualsBuilder().append(status, rhs.status).append(regeocode, rhs.regeocode).append(info, rhs.info).append(infocode, rhs.infocode).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
