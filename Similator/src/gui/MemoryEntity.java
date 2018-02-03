package gui;

public class MemoryEntity {
    private int address;
    private int content;

    public MemoryEntity(int address, int content) {
        this.address = address;
        this.content = content;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }


}
