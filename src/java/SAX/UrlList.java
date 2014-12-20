package SAX;

/**
 *
 * @author Daniel Tomé <daniel.tome@estudiants.urv.cat>
 */
import cat.urv.deim.sob.model.Url;
import java.util.ArrayList;
import java.util.List;

public class UrlList {

    private List<Url> urls = new ArrayList();

    public void addItem(Url item) {
        urls.add(item);
    }

    public int getSize() {
        return urls.size();
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        sb.append("<UrlList>\n");
        sb.append(" <Manifest>\n");

        for (int i = 0; i < urls.size(); i++) {
            sb.append(urls.get(i).toXML());
        }

        sb.append(" </Manifest>\n");
        sb.append("</UrlList>");

        return sb.toString();
    }
}
