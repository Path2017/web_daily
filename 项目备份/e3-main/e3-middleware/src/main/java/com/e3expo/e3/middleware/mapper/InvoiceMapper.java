package com.e3expo.e3.middleware.mapper;

import com.e3expo.e3.model.Invoice;

public interface InvoiceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table te_invoice
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table te_invoice
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    int insert(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table te_invoice
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    int insertSelective(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table te_invoice
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    Invoice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table te_invoice
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    int updateByPrimaryKeySelective(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table te_invoice
     *
     * @mbggenerated Tue Nov 07 16:26:13 CST 2017
     */
    int updateByPrimaryKey(Invoice record);
}