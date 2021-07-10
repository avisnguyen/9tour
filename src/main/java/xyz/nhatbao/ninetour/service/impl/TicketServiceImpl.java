package xyz.nhatbao.ninetour.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.nhatbao.ninetour.converter.TicketConverter;
import xyz.nhatbao.ninetour.entity.Ticket;
import xyz.nhatbao.ninetour.entity.User;
import xyz.nhatbao.ninetour.model.request.TicketRequestModel;
import xyz.nhatbao.ninetour.model.response.TicketResponseModel;
import xyz.nhatbao.ninetour.repository.TicketRepository;
import xyz.nhatbao.ninetour.repository.UserRepository;
import xyz.nhatbao.ninetour.service.TicketService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*******************************************************************************
 <pre>

 Copyright (c) 2021 Nguyen Nhat Bao
 This project is licensed under the terms of the MIT license.

 Author: Nguyen Nhat Bao (Kian Nguyen)
 Website: https://kiandev.xyz
 Contact for work: kiannguyen.work@gmail.com
 Feedback to me: kiannguyen.dev@gmail.com
 Github: https://github.com/kian-nguyen

 Please do not remove.

 </pre>
 ******************************************************************************/

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketConverter ticketConverter;

    @Autowired
    ModelMapper mapper;

    @Override
    public TicketResponseModel create(TicketRequestModel ticketRequestModel) {
        TicketResponseModel result = new TicketResponseModel();
        Ticket ticket = mapper.map(ticketRequestModel, Ticket.class);
        result = mapper.map(ticketRepository.save(ticket), TicketResponseModel.class);
        return result;
    }

    @Override
    public Long count() {
        return ticketRepository.count();
    }

    @Override
    public TicketResponseModel getAll(Pageable pageable) {
        TicketResponseModel result = new TicketResponseModel();
        List<Ticket> tickets = ticketRepository.findAll(pageable).getContent();
        List<TicketResponseModel> listResponse = new ArrayList<>();
        for (Ticket ticket :
                tickets) {
            listResponse.add(mapper.map(ticket, TicketResponseModel.class));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public TicketResponseModel findById(Long id) {
        TicketResponseModel result = new TicketResponseModel();
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            result = mapper.map(ticket.get(), TicketResponseModel.class);
        }
        return result;
    }

    @Override
    public Long[] delete(Long[] ids) {
        for (Long id :
                ids) {
            ticketRepository.deleteById(id);
        }
        return ids;
    }

    @Override
    public Ticket save(TicketResponseModel model) {
        Ticket ticket = ticketConverter.toEntity(model);
        if (model.getUserId() != 0 && model.getUserId() != null) {
            User user = userRepository.getOne(model.getUserId());
            ticket.setUser(user);
        }
        ticketRepository.save(ticket);
        return ticket;
    }
}