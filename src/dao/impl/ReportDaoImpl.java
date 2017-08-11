package dao.impl;

import dao.ReportDao;
import db.DBUtil;
import entities.Report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rick- on 2016/12/17.
 */
public class ReportDaoImpl implements ReportDao {

    /**
     * 获取已上传的报告列表
     * @return
     */
    @Override
    public List<Report> getReportList() {
        List<Report> reportList = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM report");
            while (resultSet.next()){
                Report report = new Report();
                report.setTitle(resultSet.getString("ReportTitle"));
                report.setTime(resultSet.getDate("ReportTime").toString());
                report.setUrl(resultSet.getString("ReportUrl"));
                report.setGroupNum(resultSet.getString("GroupNum"));

                reportList.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return reportList;
    }

    /**
     * 处理报告上传的相关事件
     */
    @Override
    public void uploadReport(Report report) {
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO report VALUE ('"+ report.getTitle() +"', '"+ report.getTime() +"', '"+ report.getUrl() +"', '"+ report.getGroupNum() +"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

    }
}
