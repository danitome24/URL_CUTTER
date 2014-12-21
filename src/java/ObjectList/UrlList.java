package ObjectList;

import cat.urv.deim.sob.model.Url;
import java.util.LinkedList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel Tomé <daniel.tome@estudiants.urv.cat>
 */
@XmlRootElement(name = "UrlList")
@XmlAccessorType (XmlAccessType.FIELD)
public class UrlList {
    @XmlElement(name = "url")
    private LinkedList<Url> urlList = null;
    
    public LinkedList<Url> getUrlList(){
        return urlList;
    }
    
    public void setUrlList(LinkedList<Url> urlList){
        this.urlList = urlList;
    }
    
}
