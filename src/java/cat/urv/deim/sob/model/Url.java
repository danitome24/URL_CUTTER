/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danie_000
 */
@XmlRootElement(name = "url")
@XmlAccessorType (XmlAccessType.FIELD)
public class Url {

    private String url;
    private int idUrl;
    private String urlShort;
    private int numVisits;

    public Url(String url, int idUrl, String urlShort, int num) {
        this.url = url;
        this.idUrl = idUrl;
        this.urlShort = urlShort;
        this.numVisits = num;
    }

    //Empty constructor

    public Url() {

    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdUrl() {
        return this.idUrl;
    }

    public void setIdUrl(int id) {
        this.idUrl = id;
    }

    public int getNumVisits() {
        return this.numVisits;
    }

    public void setNumVisits(int n) {
        this.numVisits = n;
    }

    public String getUrlShort() {
        return this.urlShort;
    }

    public void setUrlShort(String ushort) {
        this.urlShort = ushort;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();

        sb.append("  <URL>\n");
        sb.append("  \t<ID>").append(idUrl).append("</ID>\n");
        sb.append("  \t<NAME>").append(url).append("</NAME>\n");
        sb.append("  \t<URL_SHORT>$").append(urlShort).append("</URL_SHORT>\n");
        sb.append("  \t<NUM_VISITS>$").append(numVisits).append("</NUM_VISITS>\n");
        sb.append("  </URL>\n");

        return sb.toString();
    }
}
