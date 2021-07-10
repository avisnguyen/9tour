package xyz.nhatbao.ninetour.service;

import org.springframework.data.domain.Pageable;
import xyz.nhatbao.ninetour.entity.Ticket;
import xyz.nhatbao.ninetour.model.request.TicketRequestModel;
import xyz.nhatbao.ninetour.model.response.TicketResponseModel;

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

public interface TicketService {
    TicketResponseModel create(TicketRequestModel ticketRequestModel);

    Long count();

    TicketResponseModel getAll(Pageable pageable);

    TicketResponseModel findById(Long id);

    Long[] delete(Long[] ids);

    Ticket save(TicketResponseModel model);
}
