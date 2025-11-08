package Object;

import java.io.Serializable;

public class Page implements Serializable {
    // Serializable ID
    private static final long serialVersionUID = 1;

    private String head;
    private String body;
    public Page(String head, String body) {
        this.head = head;
        this.body = body;
    }
    public String getHead() {
        return head;
    }
    public void setHead(String head) {
        this.head = head;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    
}
