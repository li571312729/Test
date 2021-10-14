package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lxq
 * @date 2021年09月06日 15:23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CR6Series_2_Time_Series {
    /* "TOA5";"CR6Series_2";"CR6";"15361";"CR6.Std.10.02";"CPU:W20058__CR6CP_EErDuoSi.CR6";"35657";"Time_Series" */

    private String TIMESTAMP;
    private String RECORD;
    private String Ux;
    private String Uy;
    private String Uz;
    private String T_SONIC;
    private String diag_sonic;
    private String CO2;
    private String H2O;
    private String diag_irga;
    private String TA_1_1_1;
    private String T_cell;
    private String PA_cell;
    private String CO2_sig_strgth;
    private String H2O_sig_strgth;
    private String PA_diff;
    private String PA;
    private String pump_flow;
    private String sampling_regime;

    
    /* "TS";"RN";"m s-1";"m s-1";"m s-1";"deg C";"adimensional";"umolCO2 mol-1";"mmolH2O mol-1";"adimensional";"deg C";"deg C";"kPa";"fraction";"fraction";"kPa";"kPa";"L min-1";"adimensional" */
     /*  "";"";"Smp";"Smp";"Smp";"Smp";"Smp";"Smp";"Smp";"Smp";"Smp";"Smp";"Smp";"Smp";"Smp";"Smp";"Smp";"Smp";"Smp" */



}
