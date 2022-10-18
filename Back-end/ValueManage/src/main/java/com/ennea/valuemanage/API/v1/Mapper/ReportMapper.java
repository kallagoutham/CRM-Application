package com.ennea.valuemanage.API.v1.Mapper;

import com.ennea.valuemanage.API.v1.DTO.ReportDTO;
import com.ennea.valuemanage.Model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);
    Report reportDTOToReport(ReportDTO reportDTO);
    ReportDTO reportToReportDTO(Report report);
}
