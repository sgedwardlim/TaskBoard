package Model;

/// A wrapper that encapsulates the name of a column
public class Column {
    private String name;

    Column(String name) {
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