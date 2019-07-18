
package site.zhangsun.utility.jsonschema2pojo;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Service;

@Slf4j
public class StreetNumber {

    public String id;
    private String number;
    private String location;
    private String direction;
    private String distance;
    private String street;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
        return new HashCodeBuilder().append(number).append(location).append(direction).append(distance).append(street).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StreetNumber) == false) {
            return false;
        }
        StreetNumber rhs = ((StreetNumber) other);
        return new EqualsBuilder().append(number, rhs.number).append(location, rhs.location).append(direction, rhs.direction).append(distance, rhs.distance).append(street, rhs.street).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
