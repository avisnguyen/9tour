package xyz.nhatbao.ninetour.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

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

@Component
public class PageUtil {
    public Pageable createPageable(Integer page, Integer limit, String sortBy, Boolean desc) {
        Sort sort = Sort.unsorted();
        if (sortBy != null) {
            if (desc) sort = Sort.by(sortBy).descending();
            else sort = Sort.by(sortBy);
        }
        return PageRequest.of(page - 1, limit, sort);
    }

    public Pageable createPageable(Integer page, Integer limit, Boolean desc) {
        Sort sort = Sort.unsorted();
        if (desc) sort = Sort.by(Sort.Direction.DESC, "id");
        else sort = Sort.by(Sort.Direction.ASC, "id");
        return PageRequest.of(page - 1, limit, sort);
    }

    public Pageable createPageable(Integer page, Integer limit, String sortBy) {
        Sort sort = Sort.unsorted();
        if (sortBy != null) {
            sort = Sort.by(sortBy).ascending();
        }
        return PageRequest.of(page - 1, limit, sort);
    }
}
