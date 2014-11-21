/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.model;

/**
 *
 * @author danie_000
 */
public class Url {
    private String url;
    private int idUrl;
    private String urlShort;
    private int num_visits;
    
    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url=url;
    }
    public int getIdUrl(){
        return this.idUrl;
    }
    public void setIdUrl(int id){
        this.idUrl = id;
    }
    public int getNumVisits(){
        return this.num_visits;
    }
    public void setNumVisits(int n){
        this.num_visits=n;
    }
    public String getUrlShort(){
        return this.urlShort;
    }
    public void setUrlShort(String ushort){
        this.urlShort=ushort;
    }
}
