package com.gers.gers.service.invoice;
import com.gers.gers.models.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface invoiceService {

    Invoice createInvoice(Invoice invoice);

    List<Invoice> allInvoices();

    Invoice getInvoice(Long id);

    Invoice updateInvoice(Invoice invoice);

    void  deleteInvoice(Long id);
}
