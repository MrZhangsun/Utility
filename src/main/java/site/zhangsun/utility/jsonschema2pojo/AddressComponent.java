
package site.zhangsun.utility.jsonschema2pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class AddressComponent {

    private List<Object> city = new ArrayList<Object>();
    private String province;
    private String adcode;
    private String district;
    private String towncode;
    private StreetNumber streetNumber;
    private String country;
    private String township;
    private List<BusinessArea> businessAreas = new ArrayList<BusinessArea>();
    private Building building;
    private Neighborhood neighborhood;
    private String citycode;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Object> getCity() {
        return city;
    }

    public void setCity(List<Object> city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTowncode() {
        return towncode;
    }

    public void setTowncode(String towncode) {
        this.towncode = towncode;
    }

    public StreetNumber getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(StreetNumber streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public List<BusinessArea> getBusinessAreas() {
        return businessAreas;
    }

    public void setBusinessAreas(List<BusinessArea> businessAreas) {
        this.businessAreas = businessAreas;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
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
        return new HashCodeBuilder().append(city).append(province).append(adcode).append(district).append(towncode).append(streetNumber).append(country).append(township).append(businessAreas).append(building).append(neighborhood).append(citycode).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AddressComponent) == false) {
            return false;
        }
        AddressComponent rhs = ((AddressComponent) other);
        return new EqualsBuilder().append(city, rhs.city).append(province, rhs.province).append(adcode, rhs.adcode).append(district, rhs.district).append(towncode, rhs.towncode).append(streetNumber, rhs.streetNumber).append(country, rhs.country).append(township, rhs.township).append(businessAreas, rhs.businessAreas).append(building, rhs.building).append(neighborhood, rhs.neighborhood).append(citycode, rhs.citycode).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
