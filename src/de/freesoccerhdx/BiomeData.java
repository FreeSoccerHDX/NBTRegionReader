package de.freesoccerhdx;

import java.util.HashMap;

public enum BiomeData {

    THE_VOID(0.5,0.5,0,12638463,8103167,4159204,329011,null,0,"none"),
    PLAINS(0.8,0.4,0,12638463,7907327,4159204,329011,null,0,"none"),
    SUNFLOWER_PLAINS(0.8,0.4,0,12638463,7907327,4159204,329011,null,0,"none"),
    SNOWY_PLAINS(0.0,0.5,0,12638463,8364543,4159204,329011,null,0,"none"),
    ICE_SPIKES(0.0,0.5,0,12638463,8364543,4159204,329011,null,0,"none"),
    DESERT(2.0,0.0,0,12638463,7254527,4159204,329011,null,0,"none"),
    SWAMP(0.8,0.9,6975545,12638463,7907327,6388580,2302743,6975545,0,"swamp"),
    FOREST(0.7,0.8,0,12638463,7972607,4159204,329011,null,0,"none"),
    FLOWER_FOREST(0.7,0.8,0,12638463,7972607,4159204,329011,null,0,"none"),
    BIRCH_FOREST(0.6,0.6,0,12638463,8037887,4159204,329011,null,0,"none"),
    DARK_FOREST(0.7,0.8,0,12638463,7972607,4159204,329011,null,0,"dark_forest"),
    OLD_GROWTH_BIRCH_FOREST(0.6,0.6,0,12638463,8037887,4159204,329011,null,0,"none"),
    OLD_GROWTH_PINE_TAIGA(0.3,0.8,0,12638463,8168447,4159204,329011,null,0,"none"),
    OLD_GROWTH_SPRUCE_TAIGA(0.25,0.8,0,12638463,8233983,4159204,329011,null,0,"none"),
    TAIGA(0.25,0.8,0,12638463,8233983,4159204,329011,null,0,"none"),
    SNOWY_TAIGA(-0.5,0.4,0,12638463,8625919,4020182,329011,null,0,"none"),
    SAVANNA(2.0,0.0,0,12638463,7254527,4159204,329011,null,0,"none"),
    SAVANNA_PLATEAU(2.0,0.0,0,12638463,7254527,4159204,329011,null,0,"none"),
    WINDSWEPT_HILLS(0.2,0.3,0,12638463,8233727,4159204,329011,null,0,"none"),
    WINDSWEPT_GRAVELLY_HILLS(0.2,0.3,0,12638463,8233727,4159204,329011,null,0,"none"),
    WINDSWEPT_FOREST(0.2,0.3,0,12638463,8233727,4159204,329011,null,0,"none"),
    WINDSWEPT_SAVANNA(2.0,0.0,0,12638463,7254527,4159204,329011,null,0,"none"),
    JUNGLE(0.95,0.9,0,12638463,7842047,4159204,329011,null,0,"none"),
    SPARSE_JUNGLE(0.95,0.8,0,12638463,7842047,4159204,329011,null,0,"none"),
    BAMBOO_JUNGLE(0.95,0.9,0,12638463,7842047,4159204,329011,null,0,"none"),
    BADLANDS(2.0,0.0,10387789,12638463,7254527,4159204,329011,10387789,9470285,"none"),
    ERODED_BADLANDS(2.0,0.0,10387789,12638463,7254527,4159204,329011,10387789,9470285,"none"),
    WOODED_BADLANDS(2.0,0.0,10387789,12638463,7254527,4159204,329011,10387789,9470285,"none"),
    MEADOW(0.5,0.8,0,12638463,8103167,937679,329011,null,0,"none"),
    GROVE(-0.2,0.8,0,12638463,8495359,4159204,329011,null,0,"none"),
    SNOWY_SLOPES(-0.3,0.9,0,12638463,8560639,4159204,329011,null,0,"none"),
    FROZEN_PEAKS(-0.7,0.9,0,12638463,8756735,4159204,329011,null,0,"none"),
    JAGGED_PEAKS(-0.7,0.9,0,12638463,8756735,4159204,329011,null,0,"none"),
    STONY_PEAKS(1.0,0.3,0,12638463,7776511,4159204,329011,null,0,"none"),
    RIVER(0.5,0.5,0,12638463,8103167,4159204,329011,null,0,"none"),
    FROZEN_RIVER(0.0,0.5,0,12638463,8364543,3750089,329011,null,0,"none"),
    BEACH(0.8,0.4,0,12638463,7907327,4159204,329011,null,0,"none"),
    SNOWY_BEACH(0.05,0.3,0,12638463,8364543,4020182,329011,null,0,"none"),
    STONY_SHORE(0.2,0.3,0,12638463,8233727,4159204,329011,null,0,"none"),
    WARM_OCEAN(0.5,0.5,0,12638463,8103167,4445678,270131,null,0,"none"),
    LUKEWARM_OCEAN(0.5,0.5,0,12638463,8103167,4566514,267827,null,0,"none"),
    DEEP_LUKEWARM_OCEAN(0.5,0.5,0,12638463,8103167,4566514,267827,null,0,"none"),
    OCEAN(0.5,0.5,0,12638463,8103167,4159204,329011,null,0,"none"),
    DEEP_OCEAN(0.5,0.5,0,12638463,8103167,4159204,329011,null,0,"none"),
    COLD_OCEAN(0.5,0.5,0,12638463,8103167,4020182,329011,null,0,"none"),
    DEEP_COLD_OCEAN(0.5,0.5,0,12638463,8103167,4020182,329011,null,0,"none"),
    FROZEN_OCEAN(0.0,0.5,0,12638463,8364543,3750089,329011,null,0,"none"),
    DEEP_FROZEN_OCEAN(0.5,0.5,0,12638463,8103167,3750089,329011,null,0,"none"),
    MUSHROOM_FIELDS(0.9,1.0,0,12638463,7842047,4159204,329011,null,0,"none"),
    DRIPSTONE_CAVES(0.8,0.4,0,12638463,7907327,4159204,329011,null,0,"none"),
    LUSH_CAVES(0.5,0.5,0,12638463,8103167,4159204,329011,null,0,"none"),
    NETHER_WASTES(2.0,0.0,0,3344392,7254527,4159204,329011,null,0,"none"),
    WARPED_FOREST(2.0,0.0,0,1705242,7254527,4159204,329011,null,0,"none"),
    CRIMSON_FOREST(2.0,0.0,0,3343107,7254527,4159204,329011,null,0,"none"),
    SOUL_SAND_VALLEY(2.0,0.0,0,1787717,7254527,4159204,329011,null,0,"none"),
    BASALT_DELTAS(2.0,0.0,0,6840176,7254527,4159204,329011,null,0,"none"),
    THE_END(0.5,0.5,0,10518688,0,4159204,329011,null,0,"none"),
    END_HIGHLANDS(0.5,0.5,0,10518688,0,4159204,329011,null,0,"none"),
    END_MIDLANDS(0.5,0.5,0,10518688,0,4159204,329011,null,0,"none"),
    SMALL_END_ISLANDS(0.5,0.5,0,10518688,0,4159204,329011,null,0,"none"),
    END_BARRENS(0.5,0.5,0,10518688,0,4159204,329011,null,0,"none")
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
    private String modiType;

    BiomeData(double temp, double downfall, int foliagecolor, int fogcolor, int skycolor, int watercolor, int waterfogcolor, Integer foliageOverride, Integer grassOverride, String modiType) {
        this.temperature = (float) temp;
        this.downfall = (float) downfall;
        this.foliageColor = foliagecolor;
        this.fogColor = fogcolor;
        this.skyColor = skycolor;
        this.waterColor = watercolor;
        this.waterFogColor = waterfogcolor;
        this.foliageOverride = foliageOverride;
        this.grassOverride = grassOverride;
        this.modiType = modiType;

    }

    public int calculateGrassColor(int worldY){

        if(grassOverride == null || grassOverride == 0) {

            int elevation = Math.max(0, worldY - 64);

            return calcBiomeColor(temperature, downfall, elevation, grassCorners);
        }
        return grassOverride;

    }
    public int calculateFoliageColor(int worldY){
        if(foliageOverride == null) {
            int elevation = Math.max(0, worldY - 64);

            return calcBiomeColor(temperature, downfall, elevation, foliageCorners);
        }
        return foliageOverride;
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

    static int[][] grassCorners = new int[][]{
        { 191, 183,  85 },	// lower left, temperature starts at 1.0 on left
        { 128, 180, 151 },	// lower right
        {  71, 205,  51 }	// upper left
    };

    static int[][] foliageCorners = new int[][]{
        { 174, 164,  42 },	// lower left, temperature starts at 1.0 on left
        {  96, 161, 123 },	// lower right
        {  26, 191,  0 }	// upper left
    };

    private static float clamp(float value, float down, float upper){
        if(value > upper)
            return upper;
        if(value < down)
            return down;

        return value;
    }

    private static int calcBiomeColor(float temperature, float rainfall, int elevation, int[][] corners){
        // get UVs


        temperature = clamp(temperature - (float)elevation * 0.00166667f, 0.0f, 1.0f);
        rainfall = clamp(rainfall, 0.0f, 1.0f);
        rainfall *= temperature;

        // UV is essentially temperature, rainfall

        // lambda values for barycentric coordinates
        float[] lambda = new float[3];
        lambda[0] = temperature - rainfall;
        lambda[1] = 1.0f - temperature;
        lambda[2] = rainfall;

        float red = 0.0f, green = 0.0f, blue = 0.0f;
        for (int i = 0; i < 3; i++)
        {
            red += lambda[i] * corners[i][0];
            green += lambda[i] * corners[i][1];
            blue += lambda[i] * corners[i][2];
        }

        int r = (int)clamp(red, 0.0f, 255.0f);
        int g = (int)clamp(green, 0.0f, 255.0f);
        int b = (int)clamp(blue, 0.0f, 255.0f);

        return (r << 16) | (g << 8) | b;
    }
}


