package com.example.springapp.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentViewRequest {
    private Long paymentId;
    private Double amountPaid;
    @JsonFormat(pattern="yyyy-MM-dd")
	private Date paymentDate;
	private String modeOfPayement;
    private Long organizerId;
    private Double totalAmount;
    
    public PaymentViewRequest() {
    }
    public PaymentViewRequest(Long paymentId, Double amountPaid, Date paymentDate, String modeOfPayement,
            Long organizerId, Double totalAmount) {
        this.paymentId = paymentId;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.modeOfPayement = modeOfPayement;
        this.organizerId = organizerId;
        this.totalAmount = totalAmount;
    }
    public Long getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
    public Double getAmountPaid() {
        return amountPaid;
    }
    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }
    public Date getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    public String getModeOfPayement() {
        return modeOfPayement;
    }
    public void setModeOfPayement(String modeOfPayement) {
        this.modeOfPayement = modeOfPayement;
    }
    public Long getOrganizerId() {
        return organizerId;
    }
    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    @Override
    public String toString() {
        return "PaymentViewRequest [amountPaid=" + amountPaid + ", modeOfPayement=" + modeOfPayement + ", organizerId="
                + organizerId + ", paymentDate=" + paymentDate + ", paymentId=" + paymentId + ", totalAmount="
                + totalAmount + "]";
    }
    

    
}
