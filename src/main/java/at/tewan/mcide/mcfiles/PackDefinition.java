package at.tewan.mcide.mcfiles;

public class PackDefinition {

    private Pack pack;

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public PackDefinition(String description, int format) {
        pack = new Pack();

        pack.setDescription(description);
        pack.setPackFormat(format);
    }

    public class Pack {
        private int pack_format;
        private String description;

        public int getPack_format() {
            return pack_format;
        }

        public void setPackFormat(int packFormat) {
            this.pack_format = packFormat;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
