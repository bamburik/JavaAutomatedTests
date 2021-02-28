package Configuration;

public enum ConfigItem {
    URL("Url");

    private final String name;

    private ConfigItem(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
