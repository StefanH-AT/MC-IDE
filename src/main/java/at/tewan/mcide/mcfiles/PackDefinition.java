package at.tewan.mcide.mcfiles;

public class PackDefinition {

    private Pack pack;

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    private class Pack {
        private int pack_format;
        private String description;

        public int getPack_format() {
            return pack_format;
        }

        public void setPack_format(int pack_format) {
            this.pack_format = pack_format;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
