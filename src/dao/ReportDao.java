package dao;

import entities.Report;

import java.util.Date;
import java.util.List;

/**
 * Created by rick- on 2016/12/17.
 */
public interface ReportDao {
    public List<Report> getReportList();
    public void uploadReport(Report report);
}
