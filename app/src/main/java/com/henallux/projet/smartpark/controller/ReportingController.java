package com.henallux.projet.smartpark.controller;

import com.henallux.projet.smartpark.DAO.ReportingDAO;
import com.henallux.projet.smartpark.modele.Reporting;

/**
 * Created by Lucas on 17/12/2016.
 */

public class ReportingController {

    private ReportingDAO reportingDAO;

    public ReportingController()
    {
        reportingDAO = new ReportingDAO();
    }

    public void postReporting(Reporting reporting) throws Exception
    {
        reportingDAO.postReporting(reporting);
    }
}
