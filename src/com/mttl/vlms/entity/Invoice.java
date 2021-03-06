package com.mttl.vlms.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Invoice {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.subject
     *
     * @mbggenerated
     */
    private String subject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.date
     *
     * @mbggenerated
     */
    private Date date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.payment_due_date
     *
     * @mbggenerated
     */
    private Date paymentDueDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.invoice_no
     *
     * @mbggenerated
     */
    private String invoiceNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.sub_total
     *
     * @mbggenerated
     */
    private BigDecimal subTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.tax_amount
     *
     * @mbggenerated
     */
    private BigDecimal taxAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.grand_total
     *
     * @mbggenerated
     */
    private BigDecimal grandTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.currency
     *
     * @mbggenerated
     */
    private String currency;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.customer_dealer_name
     *
     * @mbggenerated
     */
    private String customerDealerName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.contact_name
     *
     * @mbggenerated
     */
    private String contactName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.created_by
     *
     * @mbggenerated
     */
    private Integer createdBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.updated_by
     *
     * @mbggenerated
     */
    private Integer updatedBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.delete_flg
     *
     * @mbggenerated
     */
    private Boolean deleteFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.dealer_id
     *
     * @mbggenerated
     */
    private Integer dealerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.created_date
     *
     * @mbggenerated
     */
    private Date createdDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.updated_date
     *
     * @mbggenerated
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.remark_1
     *
     * @mbggenerated
     */
    private String remark1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.remark_2
     *
     * @mbggenerated
     */
    private String remark2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.tax_included
     *
     * @mbggenerated
     */
    private Boolean taxIncluded;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.tax_percentage
     *
     * @mbggenerated
     */
    private BigDecimal taxPercentage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.prepared_by
     *
     * @mbggenerated
     */
    private String preparedBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.approved_by
     *
     * @mbggenerated
     */
    private String approvedBy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.id
     *
     * @return the value of invoice.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.id
     *
     * @param id the value for invoice.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.subject
     *
     * @return the value of invoice.subject
     *
     * @mbggenerated
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.subject
     *
     * @param subject the value for invoice.subject
     *
     * @mbggenerated
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.date
     *
     * @return the value of invoice.date
     *
     * @mbggenerated
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.date
     *
     * @param date the value for invoice.date
     *
     * @mbggenerated
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.payment_due_date
     *
     * @return the value of invoice.payment_due_date
     *
     * @mbggenerated
     */
    public Date getPaymentDueDate() {
        return paymentDueDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.payment_due_date
     *
     * @param paymentDueDate the value for invoice.payment_due_date
     *
     * @mbggenerated
     */
    public void setPaymentDueDate(Date paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.invoice_no
     *
     * @return the value of invoice.invoice_no
     *
     * @mbggenerated
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.invoice_no
     *
     * @param invoiceNo the value for invoice.invoice_no
     *
     * @mbggenerated
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.sub_total
     *
     * @return the value of invoice.sub_total
     *
     * @mbggenerated
     */
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.sub_total
     *
     * @param subTotal the value for invoice.sub_total
     *
     * @mbggenerated
     */
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.tax_amount
     *
     * @return the value of invoice.tax_amount
     *
     * @mbggenerated
     */
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.tax_amount
     *
     * @param taxAmount the value for invoice.tax_amount
     *
     * @mbggenerated
     */
    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.grand_total
     *
     * @return the value of invoice.grand_total
     *
     * @mbggenerated
     */
    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.grand_total
     *
     * @param grandTotal the value for invoice.grand_total
     *
     * @mbggenerated
     */
    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.currency
     *
     * @return the value of invoice.currency
     *
     * @mbggenerated
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.currency
     *
     * @param currency the value for invoice.currency
     *
     * @mbggenerated
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.type
     *
     * @return the value of invoice.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.type
     *
     * @param type the value for invoice.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.customer_dealer_name
     *
     * @return the value of invoice.customer_dealer_name
     *
     * @mbggenerated
     */
    public String getCustomerDealerName() {
        return customerDealerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.customer_dealer_name
     *
     * @param customerDealerName the value for invoice.customer_dealer_name
     *
     * @mbggenerated
     */
    public void setCustomerDealerName(String customerDealerName) {
        this.customerDealerName = customerDealerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.contact_name
     *
     * @return the value of invoice.contact_name
     *
     * @mbggenerated
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.contact_name
     *
     * @param contactName the value for invoice.contact_name
     *
     * @mbggenerated
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.created_by
     *
     * @return the value of invoice.created_by
     *
     * @mbggenerated
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.created_by
     *
     * @param createdBy the value for invoice.created_by
     *
     * @mbggenerated
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.updated_by
     *
     * @return the value of invoice.updated_by
     *
     * @mbggenerated
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.updated_by
     *
     * @param updatedBy the value for invoice.updated_by
     *
     * @mbggenerated
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.delete_flg
     *
     * @return the value of invoice.delete_flg
     *
     * @mbggenerated
     */
    public Boolean getDeleteFlg() {
        return deleteFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.delete_flg
     *
     * @param deleteFlg the value for invoice.delete_flg
     *
     * @mbggenerated
     */
    public void setDeleteFlg(Boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.dealer_id
     *
     * @return the value of invoice.dealer_id
     *
     * @mbggenerated
     */
    public Integer getDealerId() {
        return dealerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.dealer_id
     *
     * @param dealerId the value for invoice.dealer_id
     *
     * @mbggenerated
     */
    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.created_date
     *
     * @return the value of invoice.created_date
     *
     * @mbggenerated
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.created_date
     *
     * @param createdDate the value for invoice.created_date
     *
     * @mbggenerated
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.updated_date
     *
     * @return the value of invoice.updated_date
     *
     * @mbggenerated
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.updated_date
     *
     * @param updatedDate the value for invoice.updated_date
     *
     * @mbggenerated
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.remark_1
     *
     * @return the value of invoice.remark_1
     *
     * @mbggenerated
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.remark_1
     *
     * @param remark1 the value for invoice.remark_1
     *
     * @mbggenerated
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.remark_2
     *
     * @return the value of invoice.remark_2
     *
     * @mbggenerated
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.remark_2
     *
     * @param remark2 the value for invoice.remark_2
     *
     * @mbggenerated
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.tax_included
     *
     * @return the value of invoice.tax_included
     *
     * @mbggenerated
     */
    public Boolean getTaxIncluded() {
        return taxIncluded;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.tax_included
     *
     * @param taxIncluded the value for invoice.tax_included
     *
     * @mbggenerated
     */
    public void setTaxIncluded(Boolean taxIncluded) {
        this.taxIncluded = taxIncluded;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.tax_percentage
     *
     * @return the value of invoice.tax_percentage
     *
     * @mbggenerated
     */
    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.tax_percentage
     *
     * @param taxPercentage the value for invoice.tax_percentage
     *
     * @mbggenerated
     */
    public void setTaxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.prepared_by
     *
     * @return the value of invoice.prepared_by
     *
     * @mbggenerated
     */
    public String getPreparedBy() {
        return preparedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.prepared_by
     *
     * @param preparedBy the value for invoice.prepared_by
     *
     * @mbggenerated
     */
    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.approved_by
     *
     * @return the value of invoice.approved_by
     *
     * @mbggenerated
     */
    public String getApprovedBy() {
        return approvedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.approved_by
     *
     * @param approvedBy the value for invoice.approved_by
     *
     * @mbggenerated
     */
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invoice
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", subject=").append(subject);
        sb.append(", date=").append(date);
        sb.append(", paymentDueDate=").append(paymentDueDate);
        sb.append(", invoiceNo=").append(invoiceNo);
        sb.append(", subTotal=").append(subTotal);
        sb.append(", taxAmount=").append(taxAmount);
        sb.append(", grandTotal=").append(grandTotal);
        sb.append(", currency=").append(currency);
        sb.append(", type=").append(type);
        sb.append(", customerDealerName=").append(customerDealerName);
        sb.append(", contactName=").append(contactName);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", deleteFlg=").append(deleteFlg);
        sb.append(", dealerId=").append(dealerId);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", remark1=").append(remark1);
        sb.append(", remark2=").append(remark2);
        sb.append(", taxIncluded=").append(taxIncluded);
        sb.append(", taxPercentage=").append(taxPercentage);
        sb.append(", preparedBy=").append(preparedBy);
        sb.append(", approvedBy=").append(approvedBy);
        sb.append("]");
        return sb.toString();
    }
}