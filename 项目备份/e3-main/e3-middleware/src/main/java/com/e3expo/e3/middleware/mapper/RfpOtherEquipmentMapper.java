package com.e3expo.e3.middleware.mapper;

import com.e3expo.e3.model.RfpOtherEquipment;

public interface RfpOtherEquipmentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_rfp_other_equipment
     *
     * @mbggenerated Fri Nov 17 11:34:27 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_rfp_other_equipment
     *
     * @mbggenerated Fri Nov 17 11:34:27 CST 2017
     */
    int insert(RfpOtherEquipment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_rfp_other_equipment
     *
     * @mbggenerated Fri Nov 17 11:34:27 CST 2017
     */
    int insertSelective(RfpOtherEquipment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_rfp_other_equipment
     *
     * @mbggenerated Fri Nov 17 11:34:27 CST 2017
     */
    RfpOtherEquipment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_rfp_other_equipment
     *
     * @mbggenerated Fri Nov 17 11:34:27 CST 2017
     */
    int updateByPrimaryKeySelective(RfpOtherEquipment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dic_rfp_other_equipment
     *
     * @mbggenerated Fri Nov 17 11:34:27 CST 2017
     */
    int updateByPrimaryKey(RfpOtherEquipment record);
}