package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lxq
 * @date 2021年09月06日 15:28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CR6Series_2_Flux_CSFormat {

    /* "TOA5","CR6Series_2","CR6","15361","CR6.Std.10.02","CPU:W20058__CR6CP_EErDuoSi.CR6","35657","Flux_CSFormat" */

    private String TIMESTAMP;
    private String RECORD;
    private String FC;
    private String FC_mass;
    private String FC_QC;
    private String FC_samples;
    private String LE;
    private String LE_QC;
    private String LE_samples;
    private String H;
    private String H_QC;
    private String H_samples;
    private String Bowen_ratio;
    private String TAU;
    private String TAU_QC;
    private String USTAR;
    private String TSTAR;
    private String TKE;
    private String TA_1_1_1;
    private String TA_SIGMA_1_1_1;
    private String RH_1_1_1;
    private String T_DP_1_1_1;
    private String e;
    private String e_sat;
    private String PA;
    private String PA_SIGMA;
    private String VPD;
    private String U;
    private String U_SIGMA;
    private String V;
    private String V_SIGMA;
    private String W;
    private String W_SIGMA;
    private String T_SONIC;
    private String T_SONIC_SIGMA;
    private String sonic_azimuth;
    private String WS;
    private String WS_RSLT;
    private String WD_SONIC;
    private String WD_SIGMA;
    private String WD;
    private String WS_MAX;
    private String CO2;
    private String CO2_SIGMA;
    private String CO2_density;
    private String CO2_density_SIGMA;
    private String H2O;
    private String H2O_SIGMA;
    private String H2O_density;
    private String H2O_density_SIGMA;
    private String CO2_sig_strgth_Min;
    private String H2O_sig_strgth_Min;
    private String sun_azimuth;
    private String sun_elevation;
    private String hour_angle;
    private String sun_declination;
    private String air_mass_coeff;
    private String daytime;
    private String FETCH_MAX;
    private String FETCH_90;
    private String FETCH_55;
    private String FETCH_40;
    private String UPWND_DIST_INTRST;
    private String FTPRNT_DIST_INTRST;
    private String FTPRNT_EQUATION;

    /* "TS","RN","umolCO2 m-2 s-1","mg m-2 s-1","grade","samples","W m-2","grade","samples","W m-2","grade","samples","fraction","kg m-1 s-2","grade","m s-1","deg C","m2 s-2","deg C","deg C","%","deg C","kPa","kPa","kPa","kPa","hPa","m s-1","m s-1","m s-1","m s-1","m s-1","m s-1","deg C","deg C","decimal degrees","m s-1","m s-1","decimal degrees","decimal degrees","decimal degrees","m s-1","umolCO2 mol-1","umolCO2 mol-1","mg m-3","mg m-3","mmolH2O mol-1","mmolH2O mol-1","g m-3","g m-3","fraction","fraction","decimal degrees","decimal degrees","decimal degrees","decimal degrees","adimensional","fraction","m","m","m","m","m","%","authors" */
    /*  "","","Smp","Smp","Smp","Tot","Smp","Smp","Tot","Smp","Smp","Tot","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Min","Min","Avg","Avg","Avg","Avg","Avg","Tot","Smp","Smp","Smp","Smp","Smp","Smp","Smp" */
}
