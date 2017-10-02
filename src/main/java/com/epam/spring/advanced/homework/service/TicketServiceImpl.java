package com.epam.spring.advanced.homework.service;

import com.epam.spring.advanced.homework.domain.Ticket;
import com.epam.spring.advanced.homework.repository.DomainObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Denes Toth
 */
@Service
public class TicketServiceImpl extends RepositoryBasedDomainObjectService<Ticket> implements TicketService {

    @Autowired
    public TicketServiceImpl(DomainObjectRepository<Ticket> ticketRepository) {
        super(ticketRepository);
    }
}
