package com.ennea.valuemanage.Services;

import com.ennea.valuemanage.Model.Report;
import com.ennea.valuemanage.Repositories.CommentRepository;
import com.ennea.valuemanage.Repositories.ReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService{
    ReportRepository reportRepository;
    CommentRepository commentRepository;

    public ReportServiceImpl(ReportRepository reportRepository,CommentRepository commentRepository) {
        this.reportRepository = reportRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Report save(Report report) {
//        report.setDate(LocalDate.now());
        if(report.getComment()!=null) {
            report.getComment().setDate(report.getDate());
            commentRepository.save(report.getComment());
        }
        return reportRepository.save(report);
    }
}
