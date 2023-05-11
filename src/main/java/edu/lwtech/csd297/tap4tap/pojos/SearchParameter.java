package edu.lwtech.csd297.tap4tap.pojos;

public class SearchParameter {
    private String name;
    private String value;
    private boolean exact;

    public SearchParameter(String name, String value){
        this(name, value, true);
    }

    /**
     * 
     * @param name
     * @param value
     * @param exact whether the value must exactly match
     */
    public SearchParameter(String name, String value, boolean exact) {
        this.name = name;
        this.value = value;
        this.exact = exact;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public boolean isExact() {
        return exact;
    }
}
