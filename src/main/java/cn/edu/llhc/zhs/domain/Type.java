package cn.edu.llhc.zhs.domain;

import java.io.Serializable;

public class Type implements Serializable {

    private Integer type_id;
    private String type_name;
    private Integer father_type;
    private byte is_leaf;

    public Type(Integer type_id, String type_name, Integer father_type, byte is_leaf) {
        this.type_id = type_id;
        this.type_name = type_name;
        this.father_type = father_type;
        this.is_leaf = is_leaf;
    }

    public Type() {
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Integer getFather_type() {
        return father_type;
    }

    public void setFather_type(Integer father_type) {
        this.father_type = father_type;
    }

    @Override
    public String toString() {
        return "Type{" +
                "type_id=" + type_id +
                ", type_name='" + type_name + '\'' +
                ", father_type=" + father_type +
                '}';
    }

    public byte getIs_leaf() {
        return is_leaf;
    }

    public void setIs_leaf(byte is_leaf) {
        this.is_leaf = is_leaf;
    }
}
