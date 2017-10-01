package com.epam.spring.advanced.homework.repository;

import com.epam.spring.advanced.homework.domain.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepositoryImpl extends MapBasedIdentityRepository<Ticket> implements TicketRepository {

}
