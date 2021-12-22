package de.freesoccerhdx;

import net.querz.nbt.tag.CompoundTag;

public class BlockData {

    private CompoundTag tag;
    private String name;

    public BlockData(CompoundTag blockStateAt) {
        this.tag = blockStateAt;
        name = tag.getString("Name").split("minecraft:",2)[1];
    }

    public String getName(){
        return name;
    }

    public boolean isGrassBlock(){
        return getName().equals("grass_block");
    }

    public boolean isWater(){
        return getName().equals("water");
    }

    public boolean isAir(){
        return getName().equals("air") || getName().equals("cave_air") || getName().equals("void_air");
    }

    public boolean isLeave(){
        return getName().contains("leaves");
    }

    public boolean containsWater(){
        return getName().equals("kelp") || getName().equals("seagrass")  || getName().equals("tall_seagrass") || getName().equals("kelp_plant");
    }



}
