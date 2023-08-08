package com.gers.gers.service.invoice;

import com.gers.gers.models.Invoice;
import com.gers.gers.repository.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class invoiceServImpl implements invoiceService{

    @Autowired
    private InvoiceRepo invoiceRepo;
    @Override
    public Invoice createInvoice(Invoice invoice) {
        return  invoiceRepo.save(invoice);
    }

    @Override
    public List<Invoice> allInvoices() {
        return invoiceRepo.findAll();
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceRepo.findById(id).get();
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return invoiceRepo.save(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepo.deleteById(id);
    }
}
