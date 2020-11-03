package laplichcpu;

public class OptionCPU {
    public int value;
    public String lable;
    
    public OptionCPU(int value, String lable) {
        this.value = value;
        this.lable = lable;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    
}
