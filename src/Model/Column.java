package Model;

import java.io.Serializable;

/// A wrapper that encapsulates the name of a column
public class Column implements Serializable {
    private String name;

    public Column(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        Column column = (Column) obj;
        return name.equals(column.name);
    }
}