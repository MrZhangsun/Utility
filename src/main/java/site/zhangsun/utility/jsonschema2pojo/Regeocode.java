
package site.zhangsun.utility.jsonschema2pojo;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Regeocode {

    private AddressComponent addressComponent;
    private String formattedAddress;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public AddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
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
        return new HashCodeBuilder().append(addressComponent).append(formattedAddress).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Regeocode) == false) {
            return false;
        }
        Regeocode rhs = ((Regeocode) other);
        return new EqualsBuilder().append(addressComponent, rhs.addressComponent).append(formattedAddress, rhs.formattedAddress).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
