package demo.java.project.demo.Eneity;

import java.io.Serializable;

public class DemoTest implements Serializable {

    private  Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        DemoTest demo=(DemoTest) obj;
        if(id!=demo.getId())
            return false;
        return true;
    }
}
