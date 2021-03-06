package com.e3expo.e3.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;@Alias("orderNode")
public class OrderNode implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_order_node.id
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_order_node.name
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_order_node.pid
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    private Integer pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_order_node.next_node
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    private Integer nextNode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dic_order_node.is_valid
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    private Integer isValid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_order_node.id
     *
     * @return the value of dic_order_node.id
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_order_node.id
     *
     * @param id the value for dic_order_node.id
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_order_node.name
     *
     * @return the value of dic_order_node.name
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_order_node.name
     *
     * @param name the value for dic_order_node.name
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_order_node.pid
     *
     * @return the value of dic_order_node.pid
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_order_node.pid
     *
     * @param pid the value for dic_order_node.pid
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_order_node.next_node
     *
     * @return the value of dic_order_node.next_node
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public Integer getNextNode() {
        return nextNode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_order_node.next_node
     *
     * @param nextNode the value for dic_order_node.next_node
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public void setNextNode(Integer nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dic_order_node.is_valid
     *
     * @return the value of dic_order_node.is_valid
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dic_order_node.is_valid
     *
     * @param isValid the value for dic_order_node.is_valid
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}