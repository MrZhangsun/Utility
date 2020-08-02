
package site.zhangsun.utility.pojo.vo;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeVO<T> {
    protected Object id;
    protected Object parentId;
    List<T> children;

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getParentId() {
        return parentId;
    }

    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    public void add(T node) {
        if (null == children) {
            children = new ArrayList<T>();
        }
        children.add(node);
    }

}
