package com.tsystems.ecare;

import com.google.gson.Gson;
import com.tsystems.ecare.dto.PlanReport;
import com.tsystems.ecare.dto.Plans;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Provides methods for sending requests to REST web service.
 */
public class WebServiceUtils {

    public static Plans getPlans() {

        String url = "http://asusbook:8080/javaschool/plan/";

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String json = response.toString();

            Gson gson = new Gson();

            Plans plans = gson.fromJson(json, Plans.class);

            return plans;
        } catch (Exception ex) {
            return null;
        }
    }

    public static PlanReport getReport(long id) {

        String url = "http://asusbook:8080/javaschool/report/" + id;

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String json = response.toString();

            Gson gson = new Gson();

            PlanReport report = gson.fromJson(json, PlanReport.class);

            return report;
        } catch (Exception ex) {
            return null;
        }
    }
}
