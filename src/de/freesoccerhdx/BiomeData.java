package de.freesoccerhdx;

import java.util.HashMap;

public enum BiomeData {

    THE_VOID(0.5,0.5,0,12638463,8103167,4159204,329011,null,0),
    PLAINS(0.8,0.4,0,12638463,7907327,4159204,329011,null,0),
    SUNFLOWER_PLAINS(0.8,0.4,0,12638463,7907327,4159204,329011,null,0),
    SNOWY_PLAINS(0.0,0.5,0,12638463,8364543,4159204,329011,null,0),
    ICE_SPIKES(0.0,0.5,0,12638463,8364543,4159204,329011,null,0),
    DESERT(2.0,0.0,0,12638463,7254527,4159204,329011,null,0),
    SWAMP(0.8,0.9,6975545,12638463,7907327,6388580,2302743,6975545,6975545),
    FOREST(0.7,0.8,0,12638463,7972607,4159204,329011,null,0),
    FLOWER_FOREST(0.7,0.8,0,12638463,7972607,4159204,329011,null,0),
    BIRCH_FOREST(0.6,0.6,0,12638463,8037887,4159204,329011,null,0),
    DARK_FOREST(0.7,0.8,0,12638463,7972607,4159204,329011,null,1317381),
    OLD_GROWTH_BIRCH_FOREST(0.6,0.6,0,12638463,8037887,4159204,329011,null,0),
    OLD_GROWTH_PINE_TAIGA(0.3,0.8,0,12638463,8168447,4159204,329011,null,0),
    OLD_GROWTH_SPRUCE_TAIGA(0.25,0.8,0,12638463,8233983,4159204,329011,null,0),
    TAIGA(0.25,0.8,0,12638463,8233983,4159204,329011,null,0),
    SNOWY_TAIGA(-0.5,0.4,0,12638463,8625919,4020182,329011,null,0),
    SAVANNA(2.0,0.0,0,12638463,7254527,4159204,329011,null,0),
    SAVANNA_PLATEAU(2.0,0.0,0,12638463,7254527,4159204,329011,null,0),
    WINDSWEPT_HILLS(0.2,0.3,0,12638463,8233727,4159204,329011,null,0),
    WINDSWEPT_GRAVELLY_HILLS(0.2,0.3,0,12638463,8233727,4159204,329011,null,0),
    WINDSWEPT_FOREST(0.2,0.3,0,12638463,8233727,4159204,329011,null,0),
    WINDSWEPT_SAVANNA(2.0,0.0,0,12638463,7254527,4159204,329011,null,0),
    JUNGLE(0.95,0.9,0,12638463,7842047,4159204,329011,null,0),
    SPARSE_JUNGLE(0.95,0.8,0,12638463,7842047,4159204,329011,null,0),
    BAMBOO_JUNGLE(0.95,0.9,0,12638463,7842047,4159204,329011,null,0),
    BADLANDS(2.0,0.0,10387789,12638463,7254527,4159204,329011,10387789,9470285),
    ERODED_BADLANDS(2.0,0.0,10387789,12638463,7254527,4159204,329011,10387789,9470285),
    WOODED_BADLANDS(2.0,0.0,10387789,12638463,7254527,4159204,329011,10387789,9470285),
    MEADOW(0.5,0.8,0,12638463,8103167,937679,329011,null,0),
    GROVE(-0.2,0.8,0,12638463,8495359,4159204,329011,null,0),
    SNOWY_SLOPES(-0.3,0.9,0,12638463,8560639,4159204,329011,null,0),
    FROZEN_PEAKS(-0.7,0.9,0,12638463,8756735,4159204,329011,null,0),
    JAGGED_PEAKS(-0.7,0.9,0,12638463,8756735,4159204,329011,null,0),
    STONY_PEAKS(1.0,0.3,0,12638463,7776511,4159204,329011,null,0),
    RIVER(0.5,0.5,0,12638463,8103167,4159204,329011,null,0),
    FROZEN_RIVER(0.0,0.5,0,12638463,8364543,3750089,329011,null,0),
    BEACH(0.8,0.4,0,12638463,7907327,4159204,329011,null,0),
    SNOWY_BEACH(0.05,0.3,0,12638463,8364543,4020182,329011,null,0),
    STONY_SHORE(0.2,0.3,0,12638463,8233727,4159204,329011,null,0),
    WARM_OCEAN(0.5,0.5,0,12638463,8103167,4445678,270131,null,0),
    LUKEWARM_OCEAN(0.5,0.5,0,12638463,8103167,4566514,267827,null,0),
    DEEP_LUKEWARM_OCEAN(0.5,0.5,0,12638463,8103167,4566514,267827,null,0),
    OCEAN(0.5,0.5,0,12638463,8103167,4159204,329011,null,0),
    DEEP_OCEAN(0.5,0.5,0,12638463,8103167,4159204,329011,null,0),
    COLD_OCEAN(0.5,0.5,0,12638463,8103167,4020182,329011,null,0),
    DEEP_COLD_OCEAN(0.5,0.5,0,12638463,8103167,4020182,329011,null,0),
    FROZEN_OCEAN(0.0,0.5,0,12638463,8364543,3750089,329011,null,0),
    DEEP_FROZEN_OCEAN(0.5,0.5,0,12638463,8103167,3750089,329011,null,0),
    MUSHROOM_FIELDS(0.9,1.0,0,12638463,7842047,4159204,329011,null,0),
    DRIPSTONE_CAVES(0.8,0.4,0,12638463,7907327,4159204,329011,null,0),
    LUSH_CAVES(0.5,0.5,0,12638463,8103167,4159204,329011,null,0),
    NETHER_WASTES(2.0,0.0,0,3344392,7254527,4159204,329011,null,0),
    WARPED_FOREST(2.0,0.0,0,1705242,7254527,4159204,329011,null,0),
    CRIMSON_FOREST(2.0,0.0,0,3343107,7254527,4159204,329011,null,0),
    SOUL_SAND_VALLEY(2.0,0.0,0,1787717,7254527,4159204,329011,null,0),
    BASALT_DELTAS(2.0,0.0,0,6840176,7254527,4159204,329011,null,0),
    THE_END(0.5,0.5,0,10518688,0,4159204,329011,null,0),
    END_HIGHLANDS(0.5,0.5,0,10518688,0,4159204,329011,null,0),
    END_MIDLANDS(0.5,0.5,0,10518688,0,4159204,329011,null,0),
    SMALL_END_ISLANDS(0.5,0.5,0,10518688,0,4159204,329011,null,0),
    END_BARRENS(0.5,0.5,0,10518688,0,4159204,329011,null,0),
    ;


    private float temperature;
    private float downfall;
    private int foliageColor;
    private int fogColor;
    private int skyColor;
    private int waterColor;
    private int waterFogColor;
    private Integer foliageOverride;
    private Integer grassOverride;


    BiomeData(double temp, double downfall, int foliagecolor, int fogcolor, int skycolor, int watercolor, int waterfogcolor, Integer foliageOverride, Integer grassOverride) {
        this.temperature = (float) temp;
        this.downfall = (float) downfall;
        this.foliageColor = foliagecolor;
        this.fogColor = fogcolor;
        this.skyColor = skycolor;
        this.waterColor = watercolor;
        this.waterFogColor = waterfogcolor;
        this.foliageOverride = foliageOverride;
        this.grassOverride = grassOverride;

    }

    public float getDownfall() {
        return downfall;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getFogColor() {
        return fogColor;
    }

    public int getFoliageColor() {
        return foliageColor;
    }

    public int getSkyColor() {
        return skyColor;
    }

    public int getWaterColor() {
        return waterColor;
    }

    public int getWaterFogColor() {
        return waterFogColor;
    }

    public Integer getFoliageOverride() {
        return foliageOverride;
    }

    public Integer getGrassOverride() {
        return grassOverride;
    }

    public String getResourcename(){
        return "minecraft:"+getName();
    }

    public String getName(){
        return name().toLowerCase();
    }


    private static HashMap<String,BiomeData> biomeDataMap = new HashMap<>();

    static {

        for(BiomeData biomeData : BiomeData.values()){
            biomeDataMap.put(biomeData.name().toLowerCase(),biomeData);
        }

    }

    public static BiomeData getBiomeDataByName(String name){
        if(name.startsWith("minecraft:")){
            name = name.split("minecraft:",2)[1];
        }

        return biomeDataMap.get(name);
    }

}


