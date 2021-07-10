package xyz.nhatbao.ninetour.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.nhatbao.ninetour.converter.RegionConverter;
import xyz.nhatbao.ninetour.entity.Region;
import xyz.nhatbao.ninetour.model.request.RegionRequestModel;
import xyz.nhatbao.ninetour.model.response.RegionResponseModel;
import xyz.nhatbao.ninetour.repository.RegionRepository;
import xyz.nhatbao.ninetour.service.RegionService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
public class RegionServiceImpl implements RegionService {
    @Autowired
    RegionRepository regionRepository;

    @Autowired
    RegionConverter regionConverter;

    @Autowired
    ModelMapper mapper;

    @Override
    public RegionResponseModel create(RegionRequestModel regionRequestModel) {
        RegionResponseModel result = new RegionResponseModel();
        Region region = mapper.map(regionRequestModel, Region.class);
        result = mapper.map(regionRepository.save(region), RegionResponseModel.class);
        result.setMessage("insert_success");
        return result;
    }

    @Override
    public Long count() {
        return regionRepository.count();
    }

    @Override
    public RegionResponseModel getAll(Pageable pageable) {
        RegionResponseModel result = new RegionResponseModel();
        List<RegionResponseModel> listResponse = new ArrayList<>();
        List<Region> regions = regionRepository.findAll(pageable).getContent();
        for (Region region :
                regions) {
            listResponse.add(mapper.map(region, RegionResponseModel.class));
        }
        result.setResults(listResponse);
        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public RegionResponseModel getAll() {
        RegionResponseModel result = new RegionResponseModel();
        List<RegionResponseModel> listResponse = new ArrayList<>();
        List<Region> regions = regionRepository.findAll();
        System.out.println("test");
        listResponse = mapper.map(regions, new TypeToken<List<RegionResponseModel>>() {
        }.getType());
        result.setResults(listResponse);

        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public RegionResponseModel getAllWithPlaces() {
        RegionResponseModel result = new RegionResponseModel();
        List<RegionResponseModel> listResponse = new ArrayList<>();
        List<Region> regions = regionRepository.findAllWithPlaces();
        listResponse = regions.stream().map(r -> regionConverter.toModel(r)).collect(Collectors.toList());
        result.setResults(listResponse);

        result.setTotalItems(this.count());
        return result;
    }

    @Override
    public RegionResponseModel findById(Long id) {
        RegionResponseModel result = new RegionResponseModel();
        Optional<Region> region = regionRepository.findById(id);
        if (region.isPresent()) {
            result = mapper.map(region.get(), RegionResponseModel.class);
        }
        return result;
    }

    @Override
    public RegionResponseModel update(Long id, RegionRequestModel regionRequestModel) {
        RegionResponseModel result = new RegionResponseModel();
        Optional<Region> regionOptional = regionRepository.findById(id);
        Region newRegion = new Region();

        //Region not found
        if (!regionOptional.isPresent()) {
            result = mapper.map(regionRequestModel, RegionResponseModel.class);
            result.setMessage("region_not_found");
            return result;
        }

        // Normal
        Region oldRegion = regionOptional.get();
        newRegion = mapper.map(regionRequestModel, Region.class);
        newRegion.setCreatedBy(oldRegion.getCreatedBy());
        newRegion.setCreatedDate(oldRegion.getCreatedDate());
        result = mapper.map(regionRepository.save(newRegion), RegionResponseModel.class);
        result.setMessage("update_success");
        return result;
    }

    @Override
    public RegionResponseModel delete(RegionRequestModel regionRequestModel) {
        RegionResponseModel result = new RegionResponseModel();
        boolean canNotDelExist = false;
        List<Long> deleteIds = new ArrayList<>();
        for (Long id :
                regionRequestModel.getIds()) {
            Optional<Region> willDelRegion = regionRepository.findById(id);
            if (willDelRegion.isPresent()) {
                regionRepository.deleteById(willDelRegion.get().getId());
                deleteIds.add(id);
            } else canNotDelExist = true;
        }
        result.setIds(deleteIds);
        if (canNotDelExist) result.setMessage("delete_fail_some");
        else result.setMessage("delete_success");
        return result;
    }
}
