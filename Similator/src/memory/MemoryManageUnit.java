package memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MemoryManageUnit {
    private ArrayList<Integer> memory;

    public MemoryManageUnit() {
        this.memory = new ArrayList<>();
        for (int i = 0; i < 2048; i++) memory.add(0);

    }

    public ArrayList<Integer> getMemory() {
        return memory;
    }

    public void setMemory(ArrayList<Integer> memory) {
        this.memory = memory;
    }

    public int readMemo(int slot) {
        return memory.get(slot);
    }

    public void writeMemo(int slot, int value) {
        this.memory.set(slot, value);
    }

    public void load(HashMap<String, Integer> program) {
        if (program != null) {
            for (Map.Entry<String, Integer> temp : program.entrySet()) {
                int slot = Integer.parseInt(temp.getKey());
                int value = temp.getValue();
                writeMemo(slot, value);
            }
        }
    }

}
