package com.e3expo.e3.model;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

@Alias("province")
public class Province implements Serializable {

    /**
     * 该省内城市/区列表信息
     */
    private List<City> cityList;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_province.id
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_province.name
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_province.country_id
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    private Integer countryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_province.is_valid
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    private Integer isValid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_province.id
     *
     * @return the value of dic_province.id
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_province.id
     *
     * @param id the value for dic_province.id
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_province.name
     *
     * @return the value of dic_province.name
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_province.name
     *
     * @param name the value for dic_province.name
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_province.country_id
     *
     * @return the value of dic_province.country_id
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_province.country_id
     *
     * @param countryId the value for dic_province.country_id
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_province.is_valid
     *
     * @return the value of dic_province.is_valid
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_province.is_valid
     *
     * @param isValid the value for dic_province.is_valid
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}