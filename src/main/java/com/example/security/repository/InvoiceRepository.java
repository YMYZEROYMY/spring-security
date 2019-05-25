package com.example.security.repository;

import com.example.security.entity.Invoice;
import com.example.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
    List<Invoice> findInvoicesByUser_UsernameOrderByIdDesc(String username);
}
