package xyz.nhatbao.ninetour.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.nhatbao.ninetour.converter.ExtraBillConverter;
import xyz.nhatbao.ninetour.entity.ExtraBill;
import xyz.nhatbao.ninetour.model.request.ExtraBillRequestModel;
import xyz.nhatbao.ninetour.model.response.ExtraBillResponseModel;
import xyz.nhatbao.ninetour.repository.ExtraBillRepository;
import xyz.nhatbao.ninetour.service.ExtraBillService;

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
public class ExtraBillServiceImpl implements ExtraBillService {
    @Autowired
    ExtraBillRepository extraBillRepository;

    @Autowired
    ExtraBillConverter extraBillConverter;

    @Override
    public ExtraBillResponseModel create(ExtraBillRequestModel extraBillRequestModel) {
        ExtraBillResponseModel result = new ExtraBillResponseModel();
        ExtraBill extraBill = extraBillConverter.toEntity(extraBillRequestModel);
        result = extraBillConverter.toModel(extraBillRepository.save(extraBill));
        return result;
    }

    @Override
    public Long count() {
        return extraBillRepository.count();
    }

    @Override
    public ExtraBillResponseModel getAll(Pageable pageable) {
        ExtraBillResponseModel result = new ExtraBillResponseModel();
        List<ExtraBill> extraBills = extraBillRepository.findAll(pageable).getContent();
        List<ExtraBillResponseModel> listResponse = new ArrayList<>();
        for (ExtraBill extrabill :
                extraBills) {
            listResponse.add(extraBillConverter.toModel(extrabill));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public ExtraBillResponseModel findById(Long id) {
        ExtraBillResponseModel result = new ExtraBillResponseModel();
        Optional<ExtraBill> extraBill = extraBillRepository.findById(id);
        if (extraBill.isPresent()) {
            result = extraBillConverter.toModel(extraBill.get());
        }
        return result;
    }

    @Override
    public ExtraBillResponseModel delete(ExtraBillRequestModel extraBillRequestModel) {
        ExtraBillResponseModel result = new ExtraBillResponseModel();
        boolean canNotDelExist = false;
        List<Long> deleteIds = new ArrayList<>();
        for (Long id :
                extraBillRequestModel.getIds()) {
            Optional<ExtraBill> willDelExtraBill = extraBillRepository.findById(id);
            if (willDelExtraBill.isPresent()) {
                extraBillRepository.deleteById(willDelExtraBill.get().getId());
                deleteIds.add(id);
            } else canNotDelExist = true;
        }
        result.setIds(deleteIds);
        if (canNotDelExist) result.setMessage("delete_fail_some");
        else result.setMessage("delete_success");
        return result;
    }
}
