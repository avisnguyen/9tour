package xyz.nhatbao.ninetour.service;

import org.springframework.data.domain.Pageable;
import xyz.nhatbao.ninetour.model.request.RegionRequestModel;
import xyz.nhatbao.ninetour.model.response.RegionResponseModel;

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

public interface RegionService {

    RegionResponseModel create(RegionRequestModel regionRequestModel);

    Long count();

    RegionResponseModel getAll(Pageable pageable);

    RegionResponseModel getAll();

    RegionResponseModel getAllWithPlaces();

    RegionResponseModel findById(Long id);

    RegionResponseModel update(Long id, RegionRequestModel regionRequestModel);

    RegionResponseModel delete(RegionRequestModel regionRequestModel);

}
