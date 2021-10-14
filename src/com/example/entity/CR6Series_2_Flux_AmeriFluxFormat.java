package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lxq
 * @date 2021年09月06日 15:34
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CR6Series_2_Flux_AmeriFluxFormat {
    /* "TOA5","CR6Series_2","CR6","15361","CR6.Std.10.02","CPU:W20058__CR6CP_EErDuoSi.CR6","35657","Flux_AmeriFluxFormat" */

    private String TIMESTAMP;
    private String RECORD;
    private String TIMESTAMP_START;
    private String TIMESTAMP_END;
    private String CO2;
    private String CO2_SIGMA;
    private String H2O;
    private String H2O_SIGMA;
    private String FC;
    private String FC_SSITC_TEST;
    private String LE;
    private String LE_SSITC_TEST;
    private String ET;
    private String ET_SSITC_TEST;
    private String H;
    private String H_SSITC_TEST;
    private String FETCH_MAX;
    private String FETCH_90;
    private String FETCH_55;
    private String FETCH_40;
    private String WD;
    private String WS;
    private String WS_MAX;
    private String USTAR;
    private String ZL;
    private String TAU;
    private String TAU_SSITC_TEST;
    private String MO_LENGTH;
    private String U;
    private String U_SIGMA;
    private String V;
    private String V_SIGMA;
    private String W;
    private String W_SIGMA;
    private String PA;
    private String PA_SIGMA;
    private String TA_1_1_1;
    private String TA_SIGMA_1_1_1;
    private String RH_1_1_1;
    private String T_DP_1_1_1;
    private String VPD;
    private String T_SONIC;
    private String T_SONIC_SIGMA;
    private String PBLH;

    /* "TS","RN","YYYYMMDDHHMM","YYYYMMDDHHMM","umolCO2 mol-1","umolCO2 mol-1","mmolH2O mol-1","mmolH2O mol-1","umolCO2 m-2 s-1","adimensional","W m-2","adimensional","mm hour-1","adimensional","W m-2","adimensional","m","m","m","m","decimal degrees","m s-1","m s-1","m s-1","adimensional","kg m-1 s-2","adimensional","m","m s-1","m s-1","m s-1","m s-1","m s-1","m s-1","kPa","kPa","deg C","deg C","%","deg C","hPa","deg C","deg C","m" */
    /* "","","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp","Smp" */

}
