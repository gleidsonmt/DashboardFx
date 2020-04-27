/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gn.global.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/04/2020
 */
@SuppressWarnings("unused")
public class Country extends Model {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty abv = new SimpleStringProperty();
    private final StringProperty abv3 = new SimpleStringProperty();
    private final StringProperty abv3_alt = new SimpleStringProperty();
    private final StringProperty code = new SimpleStringProperty();
    private final StringProperty slug = new SimpleStringProperty();

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAbv() {
        return abv.get();
    }

    public StringProperty abvProperty() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv.set(abv);
    }

    public String getAbv3() {
        return abv3.get();
    }

    public StringProperty abv3Property() {
        return abv3;
    }

    public void setAbv3(String abv3) {
        this.abv3.set(abv3);
    }

    public String getAbv3_alt() {
        return abv3_alt.get();
    }

    public StringProperty abv3_altProperty() {
        return abv3_alt;
    }

    public void setAbv3_alt(String abv3_alt) {
        this.abv3_alt.set(abv3_alt);
    }

    public String getCode() {
        return code.get();
    }

    public StringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public String getSlug() {
        return slug.get();
    }

    public StringProperty slugProperty() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug.set(slug);
    }

    public List<Country> getAllinList(){
        List<Country> countries = new ArrayList<>();

        Country country_ad = new Country();
        country_ad.setName("Andorra");
        country_ad.setAbv("AD");
        country_ad.setAbv3("AND");
        country_ad.setAbv3_alt("null");
        country_ad.setCode("20");
        country_ad.setSlug("andorra");
        countries.add(country_ad);

        Country country_ae = new Country();
        country_ae.setName("United Arab Emirates");
        country_ae.setAbv("AE");
        country_ae.setAbv3("ARE");
        country_ae.setAbv3_alt("null");
        country_ae.setCode("784");
        country_ae.setSlug("united-arab-emirates");
        countries.add(country_ae);

        Country country_af = new Country();
        country_af.setName("Afghanistan");
        country_af.setAbv("AF");
        country_af.setAbv3("AFG");
        country_af.setAbv3_alt("null");
        country_af.setCode("4");
        country_af.setSlug("afghanistan");
        countries.add(country_af);

        Country country_ag = new Country();
        country_ag.setName("Antigua and Barbuda");
        country_ag.setAbv("AG");
        country_ag.setAbv3("ATG");
        country_ag.setAbv3_alt("null");
        country_ag.setCode("28");
        country_ag.setSlug("antigua-and-barbuda");
        countries.add(country_ag);

        Country country_ai = new Country();
        country_ai.setName("Anguilla");
        country_ai.setAbv("AI");
        country_ai.setAbv3("AIA");
        country_ai.setAbv3_alt("null");
        country_ai.setCode("660");
        country_ai.setSlug("anguilla");
        countries.add(country_ai);

        Country country_al = new Country();
        country_al.setName("Albania");
        country_al.setAbv("AL");
        country_al.setAbv3("ALB");
        country_al.setAbv3_alt("null");
        country_al.setCode("8");
        country_al.setSlug("albania");
        countries.add(country_al);

        Country country_am = new Country();
        country_am.setName("Armenia");
        country_am.setAbv("AM");
        country_am.setAbv3("ARM");
        country_am.setAbv3_alt("null");
        country_am.setCode("51");
        country_am.setSlug("armenia");
        countries.add(country_am);

        Country country_an = new Country();
        country_an.setName("Netherlands Antilles");
        country_an.setAbv("AN");
        country_an.setAbv3("ANT");
        country_an.setAbv3_alt("null");
        country_an.setCode("530");
        country_an.setSlug("netherlands-antilles");
        countries.add(country_an);

        Country country_ao = new Country();
        country_ao.setName("Angola");
        country_ao.setAbv("AO");
        country_ao.setAbv3("AGO");
        country_ao.setAbv3_alt("null");
        country_ao.setCode("24");
        country_ao.setSlug("angola");
        countries.add(country_ao);

        Country country_ar = new Country();
        country_ar.setName("Argentina");
        country_ar.setAbv("AR");
        country_ar.setAbv3("ARG");
        country_ar.setAbv3_alt("null");
        country_ar.setCode("32");
        country_ar.setSlug("argentina");
        countries.add(country_ar);

        Country country_as = new Country();
        country_as.setName("American Samoa");
        country_as.setAbv("AS");
        country_as.setAbv3("ASM");
        country_as.setAbv3_alt("null");
        country_as.setCode("16");
        country_as.setSlug("american-samoa");
        countries.add(country_as);

        Country country_at = new Country();
        country_at.setName("Austria");
        country_at.setAbv("AT");
        country_at.setAbv3("AUT");
        country_at.setAbv3_alt("null");
        country_at.setCode("40");
        country_at.setSlug("austria");
        countries.add(country_at);

        Country country_au = new Country();
        country_au.setName("Australia");
        country_au.setAbv("AU");
        country_au.setAbv3("AUS");
        country_au.setAbv3_alt("null");
        country_au.setCode("36");
        country_au.setSlug("australia");
        countries.add(country_au);

        Country country_aw = new Country();
        country_aw.setName("Aruba");
        country_aw.setAbv("AW");
        country_aw.setAbv3("ABW");
        country_aw.setAbv3_alt("null");
        country_aw.setCode("533");
        country_aw.setSlug("aruba");
        countries.add(country_aw);

        Country country_ax = new Country();
        country_ax.setName("Aland Islands");
        country_ax.setAbv("AX");
        country_ax.setAbv3("ALA");
        country_ax.setAbv3_alt("null");
        country_ax.setCode("248");
        country_ax.setSlug("aland-islands");
        countries.add(country_ax);

        Country country_az = new Country();
        country_az.setName("Azerbaijan");
        country_az.setAbv("AZ");
        country_az.setAbv3("AZE");
        country_az.setAbv3_alt("null");
        country_az.setCode("31");
        country_az.setSlug("azerbaijan");
        countries.add(country_az);

        Country country_ba = new Country();
        country_ba.setName("Bosnia and Herzegovina");
        country_ba.setAbv("BA");
        country_ba.setAbv3("BIH");
        country_ba.setAbv3_alt("null");
        country_ba.setCode("70");
        country_ba.setSlug("bosnia-and-herzegovina");
        countries.add(country_ba);

        Country country_bb = new Country();
        country_bb.setName("Barbados");
        country_bb.setAbv("BB");
        country_bb.setAbv3("BRB");
        country_bb.setAbv3_alt("null");
        country_bb.setCode("52");
        country_bb.setSlug("barbados");
        countries.add(country_bb);

        Country country_bd = new Country();
        country_bd.setName("Bangladesh");
        country_bd.setAbv("BD");
        country_bd.setAbv3("BGD");
        country_bd.setAbv3_alt("null");
        country_bd.setCode("50");
        country_bd.setSlug("bangladesh");
        countries.add(country_bd);

        Country country_be = new Country();
        country_be.setName("Belgium");
        country_be.setAbv("BE");
        country_be.setAbv3("BEL");
        country_be.setAbv3_alt("null");
        country_be.setCode("56");
        country_be.setSlug("belgium");
        countries.add(country_be);

        Country country_bf = new Country();
        country_bf.setName("Burkina Faso");
        country_bf.setAbv("BF");
        country_bf.setAbv3("BFA");
        country_bf.setAbv3_alt("null");
        country_bf.setCode("854");
        country_bf.setSlug("burkina-faso");
        countries.add(country_bf);

        Country country_bg = new Country();
        country_bg.setName("Bulgaria");
        country_bg.setAbv("BG");
        country_bg.setAbv3("BGR");
        country_bg.setAbv3_alt("null");
        country_bg.setCode("100");
        country_bg.setSlug("bulgaria");
        countries.add(country_bg);

        Country country_bh = new Country();
        country_bh.setName("Bahrain");
        country_bh.setAbv("BH");
        country_bh.setAbv3("BHR");
        country_bh.setAbv3_alt("null");
        country_bh.setCode("48");
        country_bh.setSlug("bahrain");
        countries.add(country_bh);

        Country country_bi = new Country();
        country_bi.setName("Burundi");
        country_bi.setAbv("BI");
        country_bi.setAbv3("BDI");
        country_bi.setAbv3_alt("null");
        country_bi.setCode("108");
        country_bi.setSlug("burundi");
        countries.add(country_bi);

        Country country_bj = new Country();
        country_bj.setName("Benin");
        country_bj.setAbv("BJ");
        country_bj.setAbv3("BEN");
        country_bj.setAbv3_alt("null");
        country_bj.setCode("204");
        country_bj.setSlug("benin");
        countries.add(country_bj);

        Country country_bl = new Country();
        country_bl.setName("Saint-Barthelemy");
        country_bl.setAbv("BL");
        country_bl.setAbv3("BLM");
        country_bl.setAbv3_alt("null");
        country_bl.setCode("652");
        country_bl.setSlug("saint-barthelemy");
        countries.add(country_bl);

        Country country_bm = new Country();
        country_bm.setName("Bermuda");
        country_bm.setAbv("BM");
        country_bm.setAbv3("BMU");
        country_bm.setAbv3_alt("null");
        country_bm.setCode("60");
        country_bm.setSlug("bermuda");
        countries.add(country_bm);

        Country country_bn = new Country();
        country_bn.setName("Brunei Darussalam");
        country_bn.setAbv("BN");
        country_bn.setAbv3("BRN");
        country_bn.setAbv3_alt("null");
        country_bn.setCode("96");
        country_bn.setSlug("brunei-darussalam");
        countries.add(country_bn);

        Country country_bo = new Country();
        country_bo.setName("Bolivia");
        country_bo.setAbv("BO");
        country_bo.setAbv3("BOL");
        country_bo.setAbv3_alt("null");
        country_bo.setCode("68");
        country_bo.setSlug("bolivia");
        countries.add(country_bo);

        Country country_br = new Country();
        country_br.setName("Brazil");
        country_br.setAbv("BR");
        country_br.setAbv3("BRA");
        country_br.setAbv3_alt("null");
        country_br.setCode("76");
        country_br.setSlug("brazil");
        countries.add(country_br);

        Country country_bs = new Country();
        country_bs.setName("Bahamas");
        country_bs.setAbv("BS");
        country_bs.setAbv3("BHS");
        country_bs.setAbv3_alt("null");
        country_bs.setCode("44");
        country_bs.setSlug("bahamas");
        countries.add(country_bs);

        Country country_bt = new Country();
        country_bt.setName("Bhutan");
        country_bt.setAbv("BT");
        country_bt.setAbv3("BTN");
        country_bt.setAbv3_alt("null");
        country_bt.setCode("64");
        country_bt.setSlug("bhutan");
        countries.add(country_bt);

        Country country_bw = new Country();
        country_bw.setName("Botswana");
        country_bw.setAbv("BW");
        country_bw.setAbv3("BWA");
        country_bw.setAbv3_alt("null");
        country_bw.setCode("72");
        country_bw.setSlug("botswana");
        countries.add(country_bw);

        Country country_by = new Country();
        country_by.setName("Belarus");
        country_by.setAbv("BY");
        country_by.setAbv3("BLR");
        country_by.setAbv3_alt("null");
        country_by.setCode("112");
        country_by.setSlug("belarus");
        countries.add(country_by);

        Country country_bz = new Country();
        country_bz.setName("Belize");
        country_bz.setAbv("BZ");
        country_bz.setAbv3("BLZ");
        country_bz.setAbv3_alt("null");
        country_bz.setCode("84");
        country_bz.setSlug("belize");
        countries.add(country_bz);

        Country country_ca = new Country();
        country_ca.setName("Canada");
        country_ca.setAbv("CA");
        country_ca.setAbv3("CAN");
        country_ca.setAbv3_alt("null");
        country_ca.setCode("124");
        country_ca.setSlug("canada");
        countries.add(country_ca);

        Country country_cd = new Country();
        country_cd.setName("Democratic Republic of the Congo");
        country_cd.setAbv("CD");
        country_cd.setAbv3("COD");
        country_cd.setAbv3_alt("null");
        country_cd.setCode("180");
        country_cd.setSlug("democratic-republic-of-congo");
        countries.add(country_cd);

        Country country_cf = new Country();
        country_cf.setName("Central African Republic");
        country_cf.setAbv("CF");
        country_cf.setAbv3("CAF");
        country_cf.setAbv3_alt("null");
        country_cf.setCode("140");
        country_cf.setSlug("central-african-republic");
        countries.add(country_cf);

        Country country_cg = new Country();
        country_cg.setName("Congo");
        country_cg.setAbv("CG");
        country_cg.setAbv3("COG");
        country_cg.setAbv3_alt("null");
        country_cg.setCode("178");
        country_cg.setSlug("congo");
        countries.add(country_cg);

        Country country_ch = new Country();
        country_ch.setName("Switzerland");
        country_ch.setAbv("CH");
        country_ch.setAbv3("CHE");
        country_ch.setAbv3_alt("null");
        country_ch.setCode("756");
        country_ch.setSlug("switzerland");
        countries.add(country_ch);

        Country country_ci = new Country();
        country_ci.setName("Cote d'Ivoire");
        country_ci.setAbv("CI");
        country_ci.setAbv3("CIV");
        country_ci.setAbv3_alt("null");
        country_ci.setCode("384");
        country_ci.setSlug("cote-divoire");
        countries.add(country_ci);

        Country country_ck = new Country();
        country_ck.setName("Cook Islands");
        country_ck.setAbv("CK");
        country_ck.setAbv3("COK");
        country_ck.setAbv3_alt("null");
        country_ck.setCode("184");
        country_ck.setSlug("cook-islands");
        countries.add(country_ck);

        Country country_cl = new Country();
        country_cl.setName("Chile");
        country_cl.setAbv("CL");
        country_cl.setAbv3("CHL");
        country_cl.setAbv3_alt("CHI");
        country_cl.setCode("152");
        country_cl.setSlug("chile");
        countries.add(country_cl);

        Country country_cm = new Country();
        country_cm.setName("Cameroon");
        country_cm.setAbv("CM");
        country_cm.setAbv3("CMR");
        country_cm.setAbv3_alt("null");
        country_cm.setCode("120");
        country_cm.setSlug("cameroon");
        countries.add(country_cm);

        Country country_cn = new Country();
        country_cn.setName("China");
        country_cn.setAbv("CN");
        country_cn.setAbv3("CHN");
        country_cn.setAbv3_alt("null");
        country_cn.setCode("156");
        country_cn.setSlug("china");
        countries.add(country_cn);

        Country country_co = new Country();
        country_co.setName("Colombia");
        country_co.setAbv("CO");
        country_co.setAbv3("COL");
        country_co.setAbv3_alt("null");
        country_co.setCode("170");
        country_co.setSlug("colombia");
        countries.add(country_co);

        Country country_cr = new Country();
        country_cr.setName("Costa Rica");
        country_cr.setAbv("CR");
        country_cr.setAbv3("CRI");
        country_cr.setAbv3_alt("null");
        country_cr.setCode("188");
        country_cr.setSlug("costa-rica");
        countries.add(country_cr);

        Country country_cu = new Country();
        country_cu.setName("Cuba");
        country_cu.setAbv("CU");
        country_cu.setAbv3("CUB");
        country_cu.setAbv3_alt("null");
        country_cu.setCode("192");
        country_cu.setSlug("cuba");
        countries.add(country_cu);

        Country country_cv = new Country();
        country_cv.setName("Cape Verde");
        country_cv.setAbv("CV");
        country_cv.setAbv3("CPV");
        country_cv.setAbv3_alt("null");
        country_cv.setCode("132");
        country_cv.setSlug("cape-verde");
        countries.add(country_cv);

        Country country_cy = new Country();
        country_cy.setName("Cyprus");
        country_cy.setAbv("CY");
        country_cy.setAbv3("CYP");
        country_cy.setAbv3_alt("null");
        country_cy.setCode("196");
        country_cy.setSlug("cyprus");
        countries.add(country_cy);

        Country country_cz = new Country();
        country_cz.setName("Czech Republic");
        country_cz.setAbv("CZ");
        country_cz.setAbv3("CZE");
        country_cz.setAbv3_alt("null");
        country_cz.setCode("203");
        country_cz.setSlug("czech-republic");
        countries.add(country_cz);

        Country country_de = new Country();
        country_de.setName("Germany");
        country_de.setAbv("DE");
        country_de.setAbv3("DEU");
        country_de.setAbv3_alt("null");
        country_de.setCode("276");
        country_de.setSlug("germany");
        countries.add(country_de);

        Country country_dj = new Country();
        country_dj.setName("Djibouti");
        country_dj.setAbv("DJ");
        country_dj.setAbv3("DJI");
        country_dj.setAbv3_alt("null");
        country_dj.setCode("262");
        country_dj.setSlug("djibouti");
        countries.add(country_dj);

        Country country_dk = new Country();
        country_dk.setName("Denmark");
        country_dk.setAbv("DK");
        country_dk.setAbv3("DNK");
        country_dk.setAbv3_alt("null");
        country_dk.setCode("208");
        country_dk.setSlug("denmark");
        countries.add(country_dk);

        Country country_dm = new Country();
        country_dm.setName("Dominica");
        country_dm.setAbv("DM");
        country_dm.setAbv3("DMA");
        country_dm.setAbv3_alt("null");
        country_dm.setCode("212");
        country_dm.setSlug("dominica");
        countries.add(country_dm);

        Country country_do = new Country();
        country_do.setName("Dominican Republic");
        country_do.setAbv("DO");
        country_do.setAbv3("DOM");
        country_do.setAbv3_alt("null");
        country_do.setCode("214");
        country_do.setSlug("dominican-republic");
        countries.add(country_do);

        Country country_dz = new Country();
        country_dz.setName("Algeria");
        country_dz.setAbv("DZ");
        country_dz.setAbv3("DZA");
        country_dz.setAbv3_alt("null");
        country_dz.setCode("12");
        country_dz.setSlug("algeria");
        countries.add(country_dz);

        Country country_ec = new Country();
        country_ec.setName("Ecuador");
        country_ec.setAbv("EC");
        country_ec.setAbv3("ECU");
        country_ec.setAbv3_alt("null");
        country_ec.setCode("218");
        country_ec.setSlug("ecuador");
        countries.add(country_ec);

        Country country_ee = new Country();
        country_ee.setName("Estonia");
        country_ee.setAbv("EE");
        country_ee.setAbv3("EST");
        country_ee.setAbv3_alt("null");
        country_ee.setCode("233");
        country_ee.setSlug("estonia");
        countries.add(country_ee);

        Country country_eg = new Country();
        country_eg.setName("Egypt");
        country_eg.setAbv("EG");
        country_eg.setAbv3("EGY");
        country_eg.setAbv3_alt("null");
        country_eg.setCode("818");
        country_eg.setSlug("egypt");
        countries.add(country_eg);

        Country country_eh = new Country();
        country_eh.setName("Western Sahara");
        country_eh.setAbv("EH");
        country_eh.setAbv3("ESH");
        country_eh.setAbv3_alt("null");
        country_eh.setCode("732");
        country_eh.setSlug("western-sahara");
        countries.add(country_eh);

        Country country_er = new Country();
        country_er.setName("Eritrea");
        country_er.setAbv("ER");
        country_er.setAbv3("ERI");
        country_er.setAbv3_alt("null");
        country_er.setCode("232");
        country_er.setSlug("eritrea");
        countries.add(country_er);

        Country country_es = new Country();
        country_es.setName("Spain");
        country_es.setAbv("ES");
        country_es.setAbv3("ESP");
        country_es.setAbv3_alt("null");
        country_es.setCode("724");
        country_es.setSlug("spain");
        countries.add(country_es);

        Country country_et = new Country();
        country_et.setName("Ethiopia");
        country_et.setAbv("ET");
        country_et.setAbv3("ETH");
        country_et.setAbv3_alt("null");
        country_et.setCode("231");
        country_et.setSlug("ethiopia");
        countries.add(country_et);

        Country country_fi = new Country();
        country_fi.setName("Finland");
        country_fi.setAbv("FI");
        country_fi.setAbv3("FIN");
        country_fi.setAbv3_alt("null");
        country_fi.setCode("246");
        country_fi.setSlug("finland");
        countries.add(country_fi);

        Country country_fj = new Country();
        country_fj.setName("Fiji");
        country_fj.setAbv("FJ");
        country_fj.setAbv3("FJI");
        country_fj.setAbv3_alt("null");
        country_fj.setCode("242");
        country_fj.setSlug("fiji");
        countries.add(country_fj);

        Country country_fk = new Country();
        country_fk.setName("Falkland Islands");
        country_fk.setAbv("FK");
        country_fk.setAbv3("FLK");
        country_fk.setAbv3_alt("null");
        country_fk.setCode("238");
        country_fk.setSlug("falkland-islands");
        countries.add(country_fk);

        Country country_fm = new Country();
        country_fm.setName("Micronesia");
        country_fm.setAbv("FM");
        country_fm.setAbv3("FSM");
        country_fm.setAbv3_alt("null");
        country_fm.setCode("583");
        country_fm.setSlug("micronesia");
        countries.add(country_fm);

        Country country_fo = new Country();
        country_fo.setName("Faeroe Islands");
        country_fo.setAbv("FO");
        country_fo.setAbv3("FRO");
        country_fo.setAbv3_alt("null");
        country_fo.setCode("234");
        country_fo.setSlug("faeroe-islands");
        countries.add(country_fo);

        Country country_fr = new Country();
        country_fr.setName("France");
        country_fr.setAbv("FR");
        country_fr.setAbv3("FRA");
        country_fr.setAbv3_alt("null");
        country_fr.setCode("250");
        country_fr.setSlug("france");
        countries.add(country_fr);

        Country country_ga = new Country();
        country_ga.setName("Gabon");
        country_ga.setAbv("GA");
        country_ga.setAbv3("GAB");
        country_ga.setAbv3_alt("null");
        country_ga.setCode("266");
        country_ga.setSlug("gabon");
        countries.add(country_ga);

        Country country_gd = new Country();
        country_gd.setName("Grenada");
        country_gd.setAbv("GD");
        country_gd.setAbv3("GRD");
        country_gd.setAbv3_alt("null");
        country_gd.setCode("308");
        country_gd.setSlug("grenada");
        countries.add(country_gd);

        Country country_ge = new Country();
        country_ge.setName("Georgia");
        country_ge.setAbv("GE");
        country_ge.setAbv3("GEO");
        country_ge.setAbv3_alt("null");
        country_ge.setCode("268");
        country_ge.setSlug("georgia");
        countries.add(country_ge);

        Country country_gf = new Country();
        country_gf.setName("French Guiana");
        country_gf.setAbv("GF");
        country_gf.setAbv3("GUF");
        country_gf.setAbv3_alt("null");
        country_gf.setCode("254");
        country_gf.setSlug("french-guiana");
        countries.add(country_gf);

        Country country_gg = new Country();
        country_gg.setName("Guernsey");
        country_gg.setAbv("GG");
        country_gg.setAbv3("GGY");
        country_gg.setAbv3_alt("null");
        country_gg.setCode("831");
        country_gg.setSlug("guernsey");
        countries.add(country_gg);

        Country country_gh = new Country();
        country_gh.setName("Ghana");
        country_gh.setAbv("GH");
        country_gh.setAbv3("GHA");
        country_gh.setAbv3_alt("null");
        country_gh.setCode("288");
        country_gh.setSlug("ghana");
        countries.add(country_gh);

        Country country_gi = new Country();
        country_gi.setName("Gibraltar");
        country_gi.setAbv("GI");
        country_gi.setAbv3("GIB");
        country_gi.setAbv3_alt("null");
        country_gi.setCode("292");
        country_gi.setSlug("gibraltar");
        countries.add(country_gi);

        Country country_gl = new Country();
        country_gl.setName("Greenland");
        country_gl.setAbv("GL");
        country_gl.setAbv3("GRL");
        country_gl.setAbv3_alt("null");
        country_gl.setCode("304");
        country_gl.setSlug("greenland");
        countries.add(country_gl);

        Country country_gm = new Country();
        country_gm.setName("Gambia");
        country_gm.setAbv("GM");
        country_gm.setAbv3("GMB");
        country_gm.setAbv3_alt("null");
        country_gm.setCode("270");
        country_gm.setSlug("gambia");
        countries.add(country_gm);

        Country country_gn = new Country();
        country_gn.setName("Guinea");
        country_gn.setAbv("GN");
        country_gn.setAbv3("GIN");
        country_gn.setAbv3_alt("null");
        country_gn.setCode("324");
        country_gn.setSlug("guinea");
        countries.add(country_gn);

        Country country_gp = new Country();
        country_gp.setName("Guadeloupe");
        country_gp.setAbv("GP");
        country_gp.setAbv3("GLP");
        country_gp.setAbv3_alt("null");
        country_gp.setCode("312");
        country_gp.setSlug("guadeloupe");
        countries.add(country_gp);

        Country country_gq = new Country();
        country_gq.setName("Equatorial Guinea");
        country_gq.setAbv("GQ");
        country_gq.setAbv3("GNQ");
        country_gq.setAbv3_alt("null");
        country_gq.setCode("226");
        country_gq.setSlug("equatorial-guinea");
        countries.add(country_gq);

        Country country_gr = new Country();
        country_gr.setName("Greece");
        country_gr.setAbv("GR");
        country_gr.setAbv3("GRC");
        country_gr.setAbv3_alt("null");
        country_gr.setCode("300");
        country_gr.setSlug("greece");
        countries.add(country_gr);

        Country country_gt = new Country();
        country_gt.setName("Guatemala");
        country_gt.setAbv("GT");
        country_gt.setAbv3("GTM");
        country_gt.setAbv3_alt("null");
        country_gt.setCode("320");
        country_gt.setSlug("guatemala");
        countries.add(country_gt);

        Country country_gu = new Country();
        country_gu.setName("Guam");
        country_gu.setAbv("GU");
        country_gu.setAbv3("GUM");
        country_gu.setAbv3_alt("null");
        country_gu.setCode("316");
        country_gu.setSlug("guam");
        countries.add(country_gu);

        Country country_gw = new Country();
        country_gw.setName("Guinea-Bissau");
        country_gw.setAbv("GW");
        country_gw.setAbv3("GNB");
        country_gw.setAbv3_alt("null");
        country_gw.setCode("624");
        country_gw.setSlug("guinea-bissau");
        countries.add(country_gw);

        Country country_gy = new Country();
        country_gy.setName("Guyana");
        country_gy.setAbv("GY");
        country_gy.setAbv3("GUY");
        country_gy.setAbv3_alt("null");
        country_gy.setCode("328");
        country_gy.setSlug("guyana");
        countries.add(country_gy);

        Country country_hk = new Country();
        country_hk.setName("Hong Kong");
        country_hk.setAbv("HK");
        country_hk.setAbv3("HKG");
        country_hk.setAbv3_alt("null");
        country_hk.setCode("344");
        country_hk.setSlug("hong-kong");
        countries.add(country_hk);

        Country country_hn = new Country();
        country_hn.setName("Honduras");
        country_hn.setAbv("HN");
        country_hn.setAbv3("HND");
        country_hn.setAbv3_alt("null");
        country_hn.setCode("340");
        country_hn.setSlug("honduras");
        countries.add(country_hn);

        Country country_hr = new Country();
        country_hr.setName("Croatia");
        country_hr.setAbv("HR");
        country_hr.setAbv3("HRV");
        country_hr.setAbv3_alt("null");
        country_hr.setCode("191");
        country_hr.setSlug("croatia");
        countries.add(country_hr);

        Country country_ht = new Country();
        country_ht.setName("Haiti");
        country_ht.setAbv("HT");
        country_ht.setAbv3("HTI");
        country_ht.setAbv3_alt("null");
        country_ht.setCode("332");
        country_ht.setSlug("haiti");
        countries.add(country_ht);

        Country country_hu = new Country();
        country_hu.setName("Hungary");
        country_hu.setAbv("HU");
        country_hu.setAbv3("HUN");
        country_hu.setAbv3_alt("null");
        country_hu.setCode("348");
        country_hu.setSlug("hungary");
        countries.add(country_hu);

        Country country_id = new Country();
        country_id.setName("Indonesia");
        country_id.setAbv("ID");
        country_id.setAbv3("IDN");
        country_id.setAbv3_alt("null");
        country_id.setCode("360");
        country_id.setSlug("indonesia");
        countries.add(country_id);

        Country country_ie = new Country();
        country_ie.setName("Ireland");
        country_ie.setAbv("IE");
        country_ie.setAbv3("IRL");
        country_ie.setAbv3_alt("null");
        country_ie.setCode("372");
        country_ie.setSlug("ireland");
        countries.add(country_ie);

        Country country_il = new Country();
        country_il.setName("Israel");
        country_il.setAbv("IL");
        country_il.setAbv3("ISR");
        country_il.setAbv3_alt("null");
        country_il.setCode("376");
        country_il.setSlug("israel");
        countries.add(country_il);

        Country country_im = new Country();
        country_im.setName("Isle of Man");
        country_im.setAbv("IM");
        country_im.setAbv3("IMN");
        country_im.setAbv3_alt("null");
        country_im.setCode("833");
        country_im.setSlug("isle-of-man");
        countries.add(country_im);

        Country country_in = new Country();
        country_in.setName("India");
        country_in.setAbv("IN");
        country_in.setAbv3("IND");
        country_in.setAbv3_alt("null");
        country_in.setCode("356");
        country_in.setSlug("india");
        countries.add(country_in);

        Country country_iq = new Country();
        country_iq.setName("Iraq");
        country_iq.setAbv("IQ");
        country_iq.setAbv3("IRQ");
        country_iq.setAbv3_alt("null");
        country_iq.setCode("368");
        country_iq.setSlug("iraq");
        countries.add(country_iq);

        Country country_ir = new Country();
        country_ir.setName("Iran");
        country_ir.setAbv("IR");
        country_ir.setAbv3("IRN");
        country_ir.setAbv3_alt("null");
        country_ir.setCode("364");
        country_ir.setSlug("iran");
        countries.add(country_ir);

        Country country_is = new Country();
        country_is.setName("Iceland");
        country_is.setAbv("IS");
        country_is.setAbv3("ISL");
        country_is.setAbv3_alt("null");
        country_is.setCode("352");
        country_is.setSlug("iceland");
        countries.add(country_is);

        Country country_it = new Country();
        country_it.setName("Italy");
        country_it.setAbv("IT");
        country_it.setAbv3("ITA");
        country_it.setAbv3_alt("null");
        country_it.setCode("380");
        country_it.setSlug("italy");
        countries.add(country_it);

        Country country_je = new Country();
        country_je.setName("Jersey");
        country_je.setAbv("JE");
        country_je.setAbv3("JEY");
        country_je.setAbv3_alt("null");
        country_je.setCode("832");
        country_je.setSlug("jersey");
        countries.add(country_je);

        Country country_jm = new Country();
        country_jm.setName("Jamaica");
        country_jm.setAbv("JM");
        country_jm.setAbv3("JAM");
        country_jm.setAbv3_alt("null");
        country_jm.setCode("388");
        country_jm.setSlug("jamaica");
        countries.add(country_jm);

        Country country_jo = new Country();
        country_jo.setName("Jordan");
        country_jo.setAbv("JO");
        country_jo.setAbv3("JOR");
        country_jo.setAbv3_alt("null");
        country_jo.setCode("400");
        country_jo.setSlug("jordan");
        countries.add(country_jo);

        Country country_jp = new Country();
        country_jp.setName("Japan");
        country_jp.setAbv("JP");
        country_jp.setAbv3("JPN");
        country_jp.setAbv3_alt("null");
        country_jp.setCode("392");
        country_jp.setSlug("japan");
        countries.add(country_jp);

        Country country_ke = new Country();
        country_ke.setName("Kenya");
        country_ke.setAbv("KE");
        country_ke.setAbv3("KEN");
        country_ke.setAbv3_alt("null");
        country_ke.setCode("404");
        country_ke.setSlug("kenya");
        countries.add(country_ke);

        Country country_kg = new Country();
        country_kg.setName("Kyrgyzstan");
        country_kg.setAbv("KG");
        country_kg.setAbv3("KGZ");
        country_kg.setAbv3_alt("null");
        country_kg.setCode("417");
        country_kg.setSlug("kyrgyzstan");
        countries.add(country_kg);

        Country country_kh = new Country();
        country_kh.setName("Cambodia");
        country_kh.setAbv("KH");
        country_kh.setAbv3("KHM");
        country_kh.setAbv3_alt("null");
        country_kh.setCode("116");
        country_kh.setSlug("cambodia");
        countries.add(country_kh);

        Country country_ki = new Country();
        country_ki.setName("Kiribati");
        country_ki.setAbv("KI");
        country_ki.setAbv3("KIR");
        country_ki.setAbv3_alt("null");
        country_ki.setCode("296");
        country_ki.setSlug("kiribati");
        countries.add(country_ki);

        Country country_km = new Country();
        country_km.setName("Comoros");
        country_km.setAbv("KM");
        country_km.setAbv3("COM");
        country_km.setAbv3_alt("null");
        country_km.setCode("174");
        country_km.setSlug("comoros");
        countries.add(country_km);

        Country country_kn = new Country();
        country_kn.setName("Saint Kitts and Nevis");
        country_kn.setAbv("KN");
        country_kn.setAbv3("KNA");
        country_kn.setAbv3_alt("null");
        country_kn.setCode("659");
        country_kn.setSlug("saint-kitts-and-nevis");
        countries.add(country_kn);

        Country country_kp = new Country();
        country_kp.setName("North Korea");
        country_kp.setAbv("KP");
        country_kp.setAbv3("PRK");
        country_kp.setAbv3_alt("null");
        country_kp.setCode("408");
        country_kp.setSlug("north-korea");
        countries.add(country_kp);

        Country country_kr = new Country();
        country_kr.setName("South Korea");
        country_kr.setAbv("KR");
        country_kr.setAbv3("KOR");
        country_kr.setAbv3_alt("null");
        country_kr.setCode("410");
        country_kr.setSlug("south-korea");
        countries.add(country_kr);

        Country country_kw = new Country();
        country_kw.setName("Kuwait");
        country_kw.setAbv("KW");
        country_kw.setAbv3("KWT");
        country_kw.setAbv3_alt("null");
        country_kw.setCode("414");
        country_kw.setSlug("kuwait");
        countries.add(country_kw);

        Country country_ky = new Country();
        country_ky.setName("Cayman Islands");
        country_ky.setAbv("KY");
        country_ky.setAbv3("CYM");
        country_ky.setAbv3_alt("null");
        country_ky.setCode("136");
        country_ky.setSlug("cayman-islands");
        countries.add(country_ky);

        Country country_kz = new Country();
        country_kz.setName("Kazakhstan");
        country_kz.setAbv("KZ");
        country_kz.setAbv3("KAZ");
        country_kz.setAbv3_alt("null");
        country_kz.setCode("398");
        country_kz.setSlug("kazakhstan");
        countries.add(country_kz);

        Country country_la = new Country();
        country_la.setName("Laos");
        country_la.setAbv("LA");
        country_la.setAbv3("LAO");
        country_la.setAbv3_alt("null");
        country_la.setCode("418");
        country_la.setSlug("laos");
        countries.add(country_la);

        Country country_lb = new Country();
        country_lb.setName("Lebanon");
        country_lb.setAbv("LB");
        country_lb.setAbv3("LBN");
        country_lb.setAbv3_alt("null");
        country_lb.setCode("422");
        country_lb.setSlug("lebanon");
        countries.add(country_lb);

        Country country_lc = new Country();
        country_lc.setName("Saint Lucia");
        country_lc.setAbv("LC");
        country_lc.setAbv3("LCA");
        country_lc.setAbv3_alt("null");
        country_lc.setCode("662");
        country_lc.setSlug("saint-lucia");
        countries.add(country_lc);

        Country country_li = new Country();
        country_li.setName("Liechtenstein");
        country_li.setAbv("LI");
        country_li.setAbv3("LIE");
        country_li.setAbv3_alt("null");
        country_li.setCode("438");
        country_li.setSlug("liechtenstein");
        countries.add(country_li);

        Country country_lk = new Country();
        country_lk.setName("Sri Lanka");
        country_lk.setAbv("LK");
        country_lk.setAbv3("LKA");
        country_lk.setAbv3_alt("null");
        country_lk.setCode("144");
        country_lk.setSlug("sri-lanka");
        countries.add(country_lk);

        Country country_lr = new Country();
        country_lr.setName("Liberia");
        country_lr.setAbv("LR");
        country_lr.setAbv3("LBR");
        country_lr.setAbv3_alt("null");
        country_lr.setCode("430");
        country_lr.setSlug("liberia");
        countries.add(country_lr);

        Country country_ls = new Country();
        country_ls.setName("Lesotho");
        country_ls.setAbv("LS");
        country_ls.setAbv3("LSO");
        country_ls.setAbv3_alt("null");
        country_ls.setCode("426");
        country_ls.setSlug("lesotho");
        countries.add(country_ls);

        Country country_lt = new Country();
        country_lt.setName("Lithuania");
        country_lt.setAbv("LT");
        country_lt.setAbv3("LTU");
        country_lt.setAbv3_alt("null");
        country_lt.setCode("440");
        country_lt.setSlug("lithuania");
        countries.add(country_lt);

        Country country_lu = new Country();
        country_lu.setName("Luxembourg");
        country_lu.setAbv("LU");
        country_lu.setAbv3("LUX");
        country_lu.setAbv3_alt("null");
        country_lu.setCode("442");
        country_lu.setSlug("luxembourg");
        countries.add(country_lu);

        Country country_lv = new Country();
        country_lv.setName("Latvia");
        country_lv.setAbv("LV");
        country_lv.setAbv3("LVA");
        country_lv.setAbv3_alt("null");
        country_lv.setCode("428");
        country_lv.setSlug("latvia");
        countries.add(country_lv);

        Country country_ly = new Country();
        country_ly.setName("Libyan Arab Jamahiriya");
        country_ly.setAbv("LY");
        country_ly.setAbv3("LBY");
        country_ly.setAbv3_alt("null");
        country_ly.setCode("434");
        country_ly.setSlug("libyan-arab-jamahiriya");
        countries.add(country_ly);

        Country country_ma = new Country();
        country_ma.setName("Morocco");
        country_ma.setAbv("MA");
        country_ma.setAbv3("MAR");
        country_ma.setAbv3_alt("null");
        country_ma.setCode("504");
        country_ma.setSlug("morocco");
        countries.add(country_ma);

        Country country_mc = new Country();
        country_mc.setName("Monaco");
        country_mc.setAbv("MC");
        country_mc.setAbv3("MCO");
        country_mc.setAbv3_alt("null");
        country_mc.setCode("492");
        country_mc.setSlug("monaco");
        countries.add(country_mc);

        Country country_md = new Country();
        country_md.setName("Moldova");
        country_md.setAbv("MD");
        country_md.setAbv3("MDA");
        country_md.setAbv3_alt("null");
        country_md.setCode("498");
        country_md.setSlug("moldova");
        countries.add(country_md);

        Country country_me = new Country();
        country_me.setName("Montenegro");
        country_me.setAbv("ME");
        country_me.setAbv3("MNE");
        country_me.setAbv3_alt("null");
        country_me.setCode("499");
        country_me.setSlug("montenegro");
        countries.add(country_me);

        Country country_mf = new Country();
        country_mf.setName("Saint-Martin");
        country_mf.setAbv("MF");
        country_mf.setAbv3("MAF");
        country_mf.setAbv3_alt("null");
        country_mf.setCode("663");
        country_mf.setSlug("saint-martin");
        countries.add(country_mf);

        Country country_mg = new Country();
        country_mg.setName("Madagascar");
        country_mg.setAbv("MG");
        country_mg.setAbv3("MDG");
        country_mg.setAbv3_alt("null");
        country_mg.setCode("450");
        country_mg.setSlug("madagascar");
        countries.add(country_mg);

        Country country_mh = new Country();
        country_mh.setName("Marshall Islands");
        country_mh.setAbv("MH");
        country_mh.setAbv3("MHL");
        country_mh.setAbv3_alt("null");
        country_mh.setCode("584");
        country_mh.setSlug("marshall-islands");
        countries.add(country_mh);

        Country country_mk = new Country();
        country_mk.setName("Macedonia");
        country_mk.setAbv("MK");
        country_mk.setAbv3("MKD");
        country_mk.setAbv3_alt("null");
        country_mk.setCode("807");
        country_mk.setSlug("macedonia");
        countries.add(country_mk);

        Country country_ml = new Country();
        country_ml.setName("Mali");
        country_ml.setAbv("ML");
        country_ml.setAbv3("MLI");
        country_ml.setAbv3_alt("null");
        country_ml.setCode("466");
        country_ml.setSlug("mali");
        countries.add(country_ml);

        Country country_mm = new Country();
        country_mm.setName("Myanmar");
        country_mm.setAbv("MM");
        country_mm.setAbv3("MMR");
        country_mm.setAbv3_alt("BUR");
        country_mm.setCode("104");
        country_mm.setSlug("myanmar");
        countries.add(country_mm);

        Country country_mn = new Country();
        country_mn.setName("Mongolia");
        country_mn.setAbv("MN");
        country_mn.setAbv3("MNG");
        country_mn.setAbv3_alt("null");
        country_mn.setCode("496");
        country_mn.setSlug("mongolia");
        countries.add(country_mn);

        Country country_mo = new Country();
        country_mo.setName("Macao");
        country_mo.setAbv("MO");
        country_mo.setAbv3("MAC");
        country_mo.setAbv3_alt("null");
        country_mo.setCode("446");
        country_mo.setSlug("macao");
        countries.add(country_mo);

        Country country_mp = new Country();
        country_mp.setName("Northern Mariana Islands");
        country_mp.setAbv("MP");
        country_mp.setAbv3("MNP");
        country_mp.setAbv3_alt("null");
        country_mp.setCode("580");
        country_mp.setSlug("northern-mariana-islands");
        countries.add(country_mp);

        Country country_mq = new Country();
        country_mq.setName("Martinique");
        country_mq.setAbv("MQ");
        country_mq.setAbv3("MTQ");
        country_mq.setAbv3_alt("null");
        country_mq.setCode("474");
        country_mq.setSlug("martinique");
        countries.add(country_mq);

        Country country_mr = new Country();
        country_mr.setName("Mauritania");
        country_mr.setAbv("MR");
        country_mr.setAbv3("MRT");
        country_mr.setAbv3_alt("null");
        country_mr.setCode("478");
        country_mr.setSlug("mauritania");
        countries.add(country_mr);

        Country country_ms = new Country();
        country_ms.setName("Montserrat");
        country_ms.setAbv("MS");
        country_ms.setAbv3("MSR");
        country_ms.setAbv3_alt("null");
        country_ms.setCode("500");
        country_ms.setSlug("montserrat");
        countries.add(country_ms);

        Country country_mt = new Country();
        country_mt.setName("Malta");
        country_mt.setAbv("MT");
        country_mt.setAbv3("MLT");
        country_mt.setAbv3_alt("null");
        country_mt.setCode("470");
        country_mt.setSlug("malta");
        countries.add(country_mt);

        Country country_mu = new Country();
        country_mu.setName("Mauritius");
        country_mu.setAbv("MU");
        country_mu.setAbv3("MUS");
        country_mu.setAbv3_alt("null");
        country_mu.setCode("480");
        country_mu.setSlug("mauritius");
        countries.add(country_mu);

        Country country_mv = new Country();
        country_mv.setName("Maldives");
        country_mv.setAbv("MV");
        country_mv.setAbv3("MDV");
        country_mv.setAbv3_alt("null");
        country_mv.setCode("462");
        country_mv.setSlug("maldives");
        countries.add(country_mv);

        Country country_mw = new Country();
        country_mw.setName("Malawi");
        country_mw.setAbv("MW");
        country_mw.setAbv3("MWI");
        country_mw.setAbv3_alt("null");
        country_mw.setCode("454");
        country_mw.setSlug("malawi");
        countries.add(country_mw);

        Country country_mx = new Country();
        country_mx.setName("Mexico");
        country_mx.setAbv("MX");
        country_mx.setAbv3("MEX");
        country_mx.setAbv3_alt("null");
        country_mx.setCode("484");
        country_mx.setSlug("mexico");
        countries.add(country_mx);

        Country country_my = new Country();
        country_my.setName("Malaysia");
        country_my.setAbv("MY");
        country_my.setAbv3("MYS");
        country_my.setAbv3_alt("null");
        country_my.setCode("458");
        country_my.setSlug("malaysia");
        countries.add(country_my);

        Country country_mz = new Country();
        country_mz.setName("Mozambique");
        country_mz.setAbv("MZ");
        country_mz.setAbv3("MOZ");
        country_mz.setAbv3_alt("null");
        country_mz.setCode("508");
        country_mz.setSlug("mozambique");
        countries.add(country_mz);

        Country country_na = new Country();
        country_na.setName("Namibia");
        country_na.setAbv("NA");
        country_na.setAbv3("NAM");
        country_na.setAbv3_alt("null");
        country_na.setCode("516");
        country_na.setSlug("namibia");
        countries.add(country_na);

        Country country_nc = new Country();
        country_nc.setName("New Caledonia");
        country_nc.setAbv("NC");
        country_nc.setAbv3("NCL");
        country_nc.setAbv3_alt("null");
        country_nc.setCode("540");
        country_nc.setSlug("new-caledonia");
        countries.add(country_nc);

        Country country_ne = new Country();
        country_ne.setName("Niger");
        country_ne.setAbv("NE");
        country_ne.setAbv3("NER");
        country_ne.setAbv3_alt("null");
        country_ne.setCode("562");
        country_ne.setSlug("niger");
        countries.add(country_ne);

        Country country_nf = new Country();
        country_nf.setName("Norfolk Island");
        country_nf.setAbv("NF");
        country_nf.setAbv3("NFK");
        country_nf.setAbv3_alt("null");
        country_nf.setCode("574");
        country_nf.setSlug("norfolk-island");
        countries.add(country_nf);

        Country country_ng = new Country();
        country_ng.setName("Nigeria");
        country_ng.setAbv("NG");
        country_ng.setAbv3("NGA");
        country_ng.setAbv3_alt("null");
        country_ng.setCode("566");
        country_ng.setSlug("nigeria");
        countries.add(country_ng);

        Country country_ni = new Country();
        country_ni.setName("Nicaragua");
        country_ni.setAbv("NI");
        country_ni.setAbv3("NIC");
        country_ni.setAbv3_alt("null");
        country_ni.setCode("558");
        country_ni.setSlug("nicaragua");
        countries.add(country_ni);

        Country country_nl = new Country();
        country_nl.setName("Netherlands");
        country_nl.setAbv("NL");
        country_nl.setAbv3("NLD");
        country_nl.setAbv3_alt("null");
        country_nl.setCode("528");
        country_nl.setSlug("netherlands");
        countries.add(country_nl);

        Country country_no = new Country();
        country_no.setName("Norway");
        country_no.setAbv("NO");
        country_no.setAbv3("NOR");
        country_no.setAbv3_alt("null");
        country_no.setCode("578");
        country_no.setSlug("norway");
        countries.add(country_no);

        Country country_np = new Country();
        country_np.setName("Nepal");
        country_np.setAbv("NP");
        country_np.setAbv3("NPL");
        country_np.setAbv3_alt("null");
        country_np.setCode("524");
        country_np.setSlug("nepal");
        countries.add(country_np);

        Country country_nr = new Country();
        country_nr.setName("Nauru");
        country_nr.setAbv("NR");
        country_nr.setAbv3("NRU");
        country_nr.setAbv3_alt("null");
        country_nr.setCode("520");
        country_nr.setSlug("nauru");
        countries.add(country_nr);

        Country country_nu = new Country();
        country_nu.setName("Niue");
        country_nu.setAbv("NU");
        country_nu.setAbv3("NIU");
        country_nu.setAbv3_alt("null");
        country_nu.setCode("570");
        country_nu.setSlug("niue");
        countries.add(country_nu);

        Country country_nz = new Country();
        country_nz.setName("New Zealand");
        country_nz.setAbv("NZ");
        country_nz.setAbv3("NZL");
        country_nz.setAbv3_alt("null");
        country_nz.setCode("554");
        country_nz.setSlug("new-zealand");
        countries.add(country_nz);

        Country country_om = new Country();
        country_om.setName("Oman");
        country_om.setAbv("OM");
        country_om.setAbv3("OMN");
        country_om.setAbv3_alt("null");
        country_om.setCode("512");
        country_om.setSlug("oman");
        countries.add(country_om);

        Country country_pa = new Country();
        country_pa.setName("Panama");
        country_pa.setAbv("PA");
        country_pa.setAbv3("PAN");
        country_pa.setAbv3_alt("null");
        country_pa.setCode("591");
        country_pa.setSlug("panama");
        countries.add(country_pa);

        Country country_pe = new Country();
        country_pe.setName("Peru");
        country_pe.setAbv("PE");
        country_pe.setAbv3("PER");
        country_pe.setAbv3_alt("null");
        country_pe.setCode("604");
        country_pe.setSlug("peru");
        countries.add(country_pe);

        Country country_pf = new Country();
        country_pf.setName("French Polynesia");
        country_pf.setAbv("PF");
        country_pf.setAbv3("PYF");
        country_pf.setAbv3_alt("null");
        country_pf.setCode("258");
        country_pf.setSlug("french-polynesia");
        countries.add(country_pf);

        Country country_pg = new Country();
        country_pg.setName("Papua New Guinea");
        country_pg.setAbv("PG");
        country_pg.setAbv3("PNG");
        country_pg.setAbv3_alt("null");
        country_pg.setCode("598");
        country_pg.setSlug("papua-new-guinea");
        countries.add(country_pg);

        Country country_ph = new Country();
        country_ph.setName("Philippines");
        country_ph.setAbv("PH");
        country_ph.setAbv3("PHL");
        country_ph.setAbv3_alt("null");
        country_ph.setCode("608");
        country_ph.setSlug("philippines");
        countries.add(country_ph);

        Country country_pk = new Country();
        country_pk.setName("Pakistan");
        country_pk.setAbv("PK");
        country_pk.setAbv3("PAK");
        country_pk.setAbv3_alt("null");
        country_pk.setCode("586");
        country_pk.setSlug("pakistan");
        countries.add(country_pk);

        Country country_pl = new Country();
        country_pl.setName("Poland");
        country_pl.setAbv("PL");
        country_pl.setAbv3("POL");
        country_pl.setAbv3_alt("null");
        country_pl.setCode("616");
        country_pl.setSlug("poland");
        countries.add(country_pl);

        Country country_pm = new Country();
        country_pm.setName("Saint Pierre and Miquelon");
        country_pm.setAbv("PM");
        country_pm.setAbv3("SPM");
        country_pm.setAbv3_alt("null");
        country_pm.setCode("666");
        country_pm.setSlug("saint-pierre-and-miquelon");
        countries.add(country_pm);

        Country country_pn = new Country();
        country_pn.setName("Pitcairn");
        country_pn.setAbv("PN");
        country_pn.setAbv3("PCN");
        country_pn.setAbv3_alt("null");
        country_pn.setCode("612");
        country_pn.setSlug("pitcairn");
        countries.add(country_pn);

        Country country_pr = new Country();
        country_pr.setName("Puerto Rico");
        country_pr.setAbv("PR");
        country_pr.setAbv3("PRI");
        country_pr.setAbv3_alt("null");
        country_pr.setCode("630");
        country_pr.setSlug("puerto-rico");
        countries.add(country_pr);

        Country country_ps = new Country();
        country_ps.setName("Palestine");
        country_ps.setAbv("PS");
        country_ps.setAbv3("PSE");
        country_ps.setAbv3_alt("null");
        country_ps.setCode("275");
        country_ps.setSlug("palestine");
        countries.add(country_ps);

        Country country_pt = new Country();
        country_pt.setName("Portugal");
        country_pt.setAbv("PT");
        country_pt.setAbv3("PRT");
        country_pt.setAbv3_alt("null");
        country_pt.setCode("620");
        country_pt.setSlug("portugal");
        countries.add(country_pt);

        Country country_pw = new Country();
        country_pw.setName("Palau");
        country_pw.setAbv("PW");
        country_pw.setAbv3("PLW");
        country_pw.setAbv3_alt("null");
        country_pw.setCode("585");
        country_pw.setSlug("palau");
        countries.add(country_pw);

        Country country_py = new Country();
        country_py.setName("Paraguay");
        country_py.setAbv("PY");
        country_py.setAbv3("PRY");
        country_py.setAbv3_alt("null");
        country_py.setCode("600");
        country_py.setSlug("paraguay");
        countries.add(country_py);

        Country country_qa = new Country();
        country_qa.setName("Qatar");
        country_qa.setAbv("QA");
        country_qa.setAbv3("QAT");
        country_qa.setAbv3_alt("null");
        country_qa.setCode("634");
        country_qa.setSlug("qatar");
        countries.add(country_qa);

        Country country_re = new Country();
        country_re.setName("Reunion");
        country_re.setAbv("RE");
        country_re.setAbv3("REU");
        country_re.setAbv3_alt("null");
        country_re.setCode("638");
        country_re.setSlug("reunion");
        countries.add(country_re);

        Country country_ro = new Country();
        country_ro.setName("Romania");
        country_ro.setAbv("RO");
        country_ro.setAbv3("ROU");
        country_ro.setAbv3_alt("ROM");
        country_ro.setCode("642");
        country_ro.setSlug("romania");
        countries.add(country_ro);

        Country country_rs = new Country();
        country_rs.setName("Serbia");
        country_rs.setAbv("RS");
        country_rs.setAbv3("SRB");
        country_rs.setAbv3_alt("null");
        country_rs.setCode("688");
        country_rs.setSlug("serbia");
        countries.add(country_rs);

        Country country_ru = new Country();
        country_ru.setName("Russian Federation");
        country_ru.setAbv("RU");
        country_ru.setAbv3("RUS");
        country_ru.setAbv3_alt("null");
        country_ru.setCode("643");
        country_ru.setSlug("russian-federation");
        countries.add(country_ru);

        Country country_rw = new Country();
        country_rw.setName("Rwanda");
        country_rw.setAbv("RW");
        country_rw.setAbv3("RWA");
        country_rw.setAbv3_alt("null");
        country_rw.setCode("646");
        country_rw.setSlug("rwanda");
        countries.add(country_rw);

        Country country_sa = new Country();
        country_sa.setName("Saudi Arabia");
        country_sa.setAbv("SA");
        country_sa.setAbv3("SAU");
        country_sa.setAbv3_alt("null");
        country_sa.setCode("682");
        country_sa.setSlug("saudi-arabia");
        countries.add(country_sa);

        Country country_sb = new Country();
        country_sb.setName("Solomon Islands");
        country_sb.setAbv("SB");
        country_sb.setAbv3("SLB");
        country_sb.setAbv3_alt("null");
        country_sb.setCode("90");
        country_sb.setSlug("solomon-islands");
        countries.add(country_sb);

        Country country_sc = new Country();
        country_sc.setName("Seychelles");
        country_sc.setAbv("SC");
        country_sc.setAbv3("SYC");
        country_sc.setAbv3_alt("null");
        country_sc.setCode("690");
        country_sc.setSlug("seychelles");
        countries.add(country_sc);

        Country country_sd = new Country();
        country_sd.setName("Sudan");
        country_sd.setAbv("SD");
        country_sd.setAbv3("SDN");
        country_sd.setAbv3_alt("null");
        country_sd.setCode("729");
        country_sd.setSlug("sudan");
        countries.add(country_sd);

        Country country_se = new Country();
        country_se.setName("Sweden");
        country_se.setAbv("SE");
        country_se.setAbv3("SWE");
        country_se.setAbv3_alt("null");
        country_se.setCode("752");
        country_se.setSlug("sweden");
        countries.add(country_se);

        Country country_sg = new Country();
        country_sg.setName("Singapore");
        country_sg.setAbv("SG");
        country_sg.setAbv3("SGP");
        country_sg.setAbv3_alt("null");
        country_sg.setCode("702");
        country_sg.setSlug("singapore");
        countries.add(country_sg);

        Country country_sh = new Country();
        country_sh.setName("Saint Helena");
        country_sh.setAbv("SH");
        country_sh.setAbv3("SHN");
        country_sh.setAbv3_alt("null");
        country_sh.setCode("654");
        country_sh.setSlug("saint-helena");
        countries.add(country_sh);

        Country country_si = new Country();
        country_si.setName("Slovenia");
        country_si.setAbv("SI");
        country_si.setAbv3("SVN");
        country_si.setAbv3_alt("null");
        country_si.setCode("705");
        country_si.setSlug("slovenia");
        countries.add(country_si);

        Country country_sj = new Country();
        country_sj.setName("Svalbard and Jan Mayen Islands");
        country_sj.setAbv("SJ");
        country_sj.setAbv3("SJM");
        country_sj.setAbv3_alt("null");
        country_sj.setCode("744");
        country_sj.setSlug("svalbard-and-jan-mayen-islands");
        countries.add(country_sj);

        Country country_sk = new Country();
        country_sk.setName("Slovakia");
        country_sk.setAbv("SK");
        country_sk.setAbv3("SVK");
        country_sk.setAbv3_alt("null");
        country_sk.setCode("703");
        country_sk.setSlug("slovakia");
        countries.add(country_sk);

        Country country_sl = new Country();
        country_sl.setName("Sierra Leone");
        country_sl.setAbv("SL");
        country_sl.setAbv3("SLE");
        country_sl.setAbv3_alt("null");
        country_sl.setCode("694");
        country_sl.setSlug("sierra-leone");
        countries.add(country_sl);

        Country country_sm = new Country();
        country_sm.setName("San Marino");
        country_sm.setAbv("SM");
        country_sm.setAbv3("SMR");
        country_sm.setAbv3_alt("null");
        country_sm.setCode("674");
        country_sm.setSlug("san-marino");
        countries.add(country_sm);

        Country country_sn = new Country();
        country_sn.setName("Senegal");
        country_sn.setAbv("SN");
        country_sn.setAbv3("SEN");
        country_sn.setAbv3_alt("null");
        country_sn.setCode("686");
        country_sn.setSlug("senegal");
        countries.add(country_sn);

        Country country_so = new Country();
        country_so.setName("Somalia");
        country_so.setAbv("SO");
        country_so.setAbv3("SOM");
        country_so.setAbv3_alt("null");
        country_so.setCode("706");
        country_so.setSlug("somalia");
        countries.add(country_so);

        Country country_sr = new Country();
        country_sr.setName("Suriname");
        country_sr.setAbv("SR");
        country_sr.setAbv3("SUR");
        country_sr.setAbv3_alt("null");
        country_sr.setCode("740");
        country_sr.setSlug("suriname");
        countries.add(country_sr);

        Country country_ss = new Country();
        country_ss.setName("South Sudan");
        country_ss.setAbv("SS");
        country_ss.setAbv3("SSD");
        country_ss.setAbv3_alt("null");
        country_ss.setCode("728");
        country_ss.setSlug("south-sudan");
        countries.add(country_ss);

        Country country_st = new Country();
        country_st.setName("Sao Tome and Principe");
        country_st.setAbv("ST");
        country_st.setAbv3("STP");
        country_st.setAbv3_alt("null");
        country_st.setCode("678");
        country_st.setSlug("sao-tome-and-principe");
        countries.add(country_st);

        Country country_sv = new Country();
        country_sv.setName("El Salvador");
        country_sv.setAbv("SV");
        country_sv.setAbv3("SLV");
        country_sv.setAbv3_alt("null");
        country_sv.setCode("222");
        country_sv.setSlug("el-salvador");
        countries.add(country_sv);

        Country country_sy = new Country();
        country_sy.setName("Syrian Arab Republic");
        country_sy.setAbv("SY");
        country_sy.setAbv3("SYR");
        country_sy.setAbv3_alt("null");
        country_sy.setCode("760");
        country_sy.setSlug("syrian-arab-republic");
        countries.add(country_sy);

        Country country_sz = new Country();
        country_sz.setName("Swaziland");
        country_sz.setAbv("SZ");
        country_sz.setAbv3("SWZ");
        country_sz.setAbv3_alt("null");
        country_sz.setCode("748");
        country_sz.setSlug("swaziland");
        countries.add(country_sz);

        Country country_tc = new Country();
        country_tc.setName("Turks and Caicos Islands");
        country_tc.setAbv("TC");
        country_tc.setAbv3("TCA");
        country_tc.setAbv3_alt("null");
        country_tc.setCode("796");
        country_tc.setSlug("turks-and-caicos-islands");
        countries.add(country_tc);

        Country country_td = new Country();
        country_td.setName("Chad");
        country_td.setAbv("TD");
        country_td.setAbv3("TCD");
        country_td.setAbv3_alt("null");
        country_td.setCode("148");
        country_td.setSlug("chad");
        countries.add(country_td);

        Country country_tg = new Country();
        country_tg.setName("Togo");
        country_tg.setAbv("TG");
        country_tg.setAbv3("TGO");
        country_tg.setAbv3_alt("null");
        country_tg.setCode("768");
        country_tg.setSlug("togo");
        countries.add(country_tg);

        Country country_th = new Country();
        country_th.setName("Thailand");
        country_th.setAbv("TH");
        country_th.setAbv3("THA");
        country_th.setAbv3_alt("null");
        country_th.setCode("764");
        country_th.setSlug("thailand");
        countries.add(country_th);

        Country country_tj = new Country();
        country_tj.setName("Tajikistan");
        country_tj.setAbv("TJ");
        country_tj.setAbv3("TJK");
        country_tj.setAbv3_alt("null");
        country_tj.setCode("762");
        country_tj.setSlug("tajikistan");
        countries.add(country_tj);

        Country country_tk = new Country();
        country_tk.setName("Tokelau");
        country_tk.setAbv("TK");
        country_tk.setAbv3("TKL");
        country_tk.setAbv3_alt("null");
        country_tk.setCode("772");
        country_tk.setSlug("tokelau");
        countries.add(country_tk);

        Country country_tm = new Country();
        country_tm.setName("Turkmenistan");
        country_tm.setAbv("TM");
        country_tm.setAbv3("TKM");
        country_tm.setAbv3_alt("null");
        country_tm.setCode("795");
        country_tm.setSlug("turkmenistan");
        countries.add(country_tm);

        Country country_tn = new Country();
        country_tn.setName("Tunisia");
        country_tn.setAbv("TN");
        country_tn.setAbv3("TUN");
        country_tn.setAbv3_alt("null");
        country_tn.setCode("788");
        country_tn.setSlug("tunisia");
        countries.add(country_tn);

        Country country_to = new Country();
        country_to.setName("Tonga");
        country_to.setAbv("TO");
        country_to.setAbv3("TON");
        country_to.setAbv3_alt("null");
        country_to.setCode("776");
        country_to.setSlug("tonga");
        countries.add(country_to);

        Country country_tp = new Country();
        country_tp.setName("Timor-Leste");
        country_tp.setAbv("TP");
        country_tp.setAbv3("TLS");
        country_tp.setAbv3_alt("null");
        country_tp.setCode("626");
        country_tp.setSlug("timor-leste");
        countries.add(country_tp);

        Country country_tr = new Country();
        country_tr.setName("Turkey");
        country_tr.setAbv("TR");
        country_tr.setAbv3("TUR");
        country_tr.setAbv3_alt("null");
        country_tr.setCode("792");
        country_tr.setSlug("turkey");
        countries.add(country_tr);

        Country country_tt = new Country();
        country_tt.setName("Trinidad and Tobago");
        country_tt.setAbv("TT");
        country_tt.setAbv3("TTO");
        country_tt.setAbv3_alt("null");
        country_tt.setCode("780");
        country_tt.setSlug("trinidad-and-tobago");
        countries.add(country_tt);

        Country country_tv = new Country();
        country_tv.setName("Tuvalu");
        country_tv.setAbv("TV");
        country_tv.setAbv3("TUV");
        country_tv.setAbv3_alt("null");
        country_tv.setCode("798");
        country_tv.setSlug("tuvalu");
        countries.add(country_tv);

        Country country_tz = new Country();
        country_tz.setName("Tanzania");
        country_tz.setAbv("TZ");
        country_tz.setAbv3("TZA");
        country_tz.setAbv3_alt("null");
        country_tz.setCode("834");
        country_tz.setSlug("tanzania");
        countries.add(country_tz);

        Country country_ua = new Country();
        country_ua.setName("Ukraine");
        country_ua.setAbv("UA");
        country_ua.setAbv3("UKR");
        country_ua.setAbv3_alt("null");
        country_ua.setCode("804");
        country_ua.setSlug("ukraine");
        countries.add(country_ua);

        Country country_ug = new Country();
        country_ug.setName("Uganda");
        country_ug.setAbv("UG");
        country_ug.setAbv3("UGA");
        country_ug.setAbv3_alt("null");
        country_ug.setCode("800");
        country_ug.setSlug("uganda");
        countries.add(country_ug);

        Country country_uk = new Country();
        country_uk.setName("United Kingdom");
        country_uk.setAbv("UK");
        country_uk.setAbv3("GBR");
        country_uk.setAbv3_alt("null");
        country_uk.setCode("826");
        country_uk.setSlug("united-kingdom");
        countries.add(country_uk);

        Country country_us = new Country();
        country_us.setName("United States");
        country_us.setAbv("US");
        country_us.setAbv3("USA");
        country_us.setAbv3_alt("null");
        country_us.setCode("840");
        country_us.setSlug("united-states");
        countries.add(country_us);

        Country country_uy = new Country();
        country_uy.setName("Uruguay");
        country_uy.setAbv("UY");
        country_uy.setAbv3("URY");
        country_uy.setAbv3_alt("null");
        country_uy.setCode("858");
        country_uy.setSlug("uruguay");
        countries.add(country_uy);

        Country country_uz = new Country();
        country_uz.setName("Uzbekistan");
        country_uz.setAbv("UZ");
        country_uz.setAbv3("UZB");
        country_uz.setAbv3_alt("null");
        country_uz.setCode("860");
        country_uz.setSlug("uzbekistan");
        countries.add(country_uz);

        Country country_va = new Country();
        country_va.setName("Holy See");
        country_va.setAbv("VA");
        country_va.setAbv3("VAT");
        country_va.setAbv3_alt("null");
        country_va.setCode("336");
        country_va.setSlug("holy-see");
        countries.add(country_va);

        Country country_vc = new Country();
        country_vc.setName("Saint Vincent and the Grenadines");
        country_vc.setAbv("VC");
        country_vc.setAbv3("VCT");
        country_vc.setAbv3_alt("null");
        country_vc.setCode("670");
        country_vc.setSlug("saint-vincent-and-grenadines");
        countries.add(country_vc);

        Country country_ve = new Country();
        country_ve.setName("Venezuela");
        country_ve.setAbv("VE");
        country_ve.setAbv3("VEN");
        country_ve.setAbv3_alt("null");
        country_ve.setCode("862");
        country_ve.setSlug("venezuela");
        countries.add(country_ve);

        Country country_vg = new Country();
        country_vg.setName("British Virgin Islands");
        country_vg.setAbv("VG");
        country_vg.setAbv3("VGB");
        country_vg.setAbv3_alt("null");
        country_vg.setCode("92");
        country_vg.setSlug("british-virgin-islands");
        countries.add(country_vg);

        Country country_vi = new Country();
        country_vi.setName("U.S. Virgin Islands");
        country_vi.setAbv("VI");
        country_vi.setAbv3("VIR");
        country_vi.setAbv3_alt("null");
        country_vi.setCode("850");
        country_vi.setSlug("us-virgin-islands");
        countries.add(country_vi);

        Country country_vn = new Country();
        country_vn.setName("Viet Nam");
        country_vn.setAbv("VN");
        country_vn.setAbv3("VNM");
        country_vn.setAbv3_alt("null");
        country_vn.setCode("704");
        country_vn.setSlug("viet-nam");
        countries.add(country_vn);

        Country country_vu = new Country();
        country_vu.setName("Vanuatu");
        country_vu.setAbv("VU");
        country_vu.setAbv3("VUT");
        country_vu.setAbv3_alt("null");
        country_vu.setCode("548");
        country_vu.setSlug("vanuatu");
        countries.add(country_vu);

        Country country_wf = new Country();
        country_wf.setName("Wallis and Futuna Islands");
        country_wf.setAbv("WF");
        country_wf.setAbv3("WLF");
        country_wf.setAbv3_alt("null");
        country_wf.setCode("876");
        country_wf.setSlug("wallis-and-futuna-islands");
        countries.add(country_wf);

        Country country_ws = new Country();
        country_ws.setName("Samoa");
        country_ws.setAbv("WS");
        country_ws.setAbv3("WSM");
        country_ws.setAbv3_alt("null");
        country_ws.setCode("882");
        country_ws.setSlug("samoa");
        countries.add(country_ws);

        Country country_ye = new Country();
        country_ye.setName("Yemen");
        country_ye.setAbv("YE");
        country_ye.setAbv3("YEM");
        country_ye.setAbv3_alt("null");
        country_ye.setCode("887");
        country_ye.setSlug("yemen");
        countries.add(country_ye);

        Country country_yt = new Country();
        country_yt.setName("Mayotte");
        country_yt.setAbv("YT");
        country_yt.setAbv3("MYT");
        country_yt.setAbv3_alt("null");
        country_yt.setCode("175");
        country_yt.setSlug("mayotte");
        countries.add(country_yt);

        Country country_za = new Country();
        country_za.setName("South Africa");
        country_za.setAbv("ZA");
        country_za.setAbv3("ZAF");
        country_za.setAbv3_alt("null");
        country_za.setCode("710");
        country_za.setSlug("south-africa");
        countries.add(country_za);

        Country country_zm = new Country();
        country_zm.setName("Zambia");
        country_zm.setAbv("ZM");
        country_zm.setAbv3("ZMB");
        country_zm.setAbv3_alt("null");
        country_zm.setCode("894");
        country_zm.setSlug("zambia");
        countries.add(country_zm);

        Country country_zw = new Country();
        country_zw.setName("Zimbabwe");
        country_zw.setAbv("ZW");
        country_zw.setAbv3("ZWE");
        country_zw.setAbv3_alt("null");
        country_zw.setCode("716");
        country_zw.setSlug("zimbabwe");
        countries.add(country_zw);

        return countries;
    }
}
