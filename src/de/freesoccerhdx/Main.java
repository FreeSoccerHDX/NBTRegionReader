package de.freesoccerhdx;

import net.querz.mca.Chunk;
import net.querz.mca.LoadFlags;
import net.querz.mca.MCAFile;
import net.querz.mca.MCAUtil;
import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.io.NamedTag;
import net.querz.nbt.tag.CompoundTag;

import javax.imageio.ImageIO;
import javax.management.InstanceNotFoundException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Time;
import java.util.Iterator;

public class Main {

    public static String getZeit(Long totaltime){

        int s = (int) (totaltime/1000);
        int min = s/60;
        s = s-min*60;

        int hour = min/60;
        min = min-hour*60;

        if(hour == 0){
            if(min == 0){
                return s+"s";
            }
            return min+"min " + s + "s";
        }

        return hour+ "h " + min+"min " + s + "s";
    }

    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public static void main(String[] args) {
        System.out.println("\n\nRunning RegionReader...\n\n");

        if(args.length != 1){
            System.out.println("Usage: 'java RegionFileReader-1.0-SNAPSHOT.jar <Region-Dir>' ");
            return;
        }
/*

        System.out.println("Test:");
        Long longData = -4611474908973580288L;
        byte[] byteData = longToBytes(longData);
        for(byte b : byteData){
            System.out.println(" > " + b);
        }
*/
        long a = System.currentTimeMillis();
        System.out.println("Loading Resources...");

        TextureManager textureManager = TextureManager.create();
        int biomedefaultcolor = new Color(0,0,0).getRGB();

        long b = System.currentTimeMillis()-a;
        System.out.println("Finished loading Resources! ("+getZeit(b)+")");


        try {
            File regiondir = new File(args[0]);

            File[] files = regiondir.listFiles();

            int maximalchunks = 0;
            int chunks = 0;
            int maximalregions = 0;
            int regions = 0;

            for (File mcaFile : files) {
                if (mcaFile.getName().endsWith(".mca")) {
                    maximalregions++;
                    maximalchunks += 32*32;
                }
            }

            for (File mcaFile : files) {
                if (mcaFile.getName().endsWith(".mca")) {
                    regions++;
                    System.out.println(">>>" + mcaFile.getName() +"  "+(Math.round(((1.0*regions)/(1.0*maximalregions))*100.0*100.0)/100.0)+ "% of Region-Files");
                    //NamedTag namedTag = NBTUtil.read(mcaFile);
                    //System.out.println(">>>" + namedTag.toString());

                    MCAFile mcaData = MCAUtil.read(mcaFile, LoadFlags.BLOCK_STATES);

                    Iterator<Chunk> chunkIterator = mcaData.iterator();

                    BufferedImage totalImage_high = new BufferedImage(16*32*16,16*32*16,BufferedImage.TYPE_INT_RGB);
                    BufferedImage totalImage = new BufferedImage(16*32,16*32,BufferedImage.TYPE_INT_RGB);
                    BufferedImage totalImage_biome = new BufferedImage(16*32,16*32,BufferedImage.TYPE_INT_RGB);
                    BufferedImage totalImage_height = new BufferedImage(16*32,16*32,BufferedImage.TYPE_INT_RGB);

                    int i = 0;
                    System.out.println("    Work Done["+chunks+" out of "+maximalchunks+"]: " + (Math.round(((1.0*chunks)/(1.0*maximalchunks))*100.0*100.0)/100.0) + "%");
                    while (chunkIterator.hasNext()) {
                        chunks++;
                        i++;
                        Chunk chunk = chunkIterator.next();

                        if(i%256 == 0){
                            long c = System.currentTimeMillis()-a;
                            System.out.println("    Work Done["+chunks+" out of "+maximalchunks+"]: " + (Math.round(((1.0*chunks)/(1.0*maximalchunks))*100.0*100.0)/100.0) + "% ("+getZeit(c)+")");
                        }


                        if(chunk == null) {
                           // System.out.println("    Chunk["+i+"]: No Data");
                        }else{
                            int cX = chunk.posX;
                            int cZ = chunk.posZ;
                           // System.out.println("    Chunk["+i+"]: X/Z="+cX+"/"+cZ);

                            BufferedImage chunk_surface_img_high = new BufferedImage(16*16,16*16,BufferedImage.TYPE_INT_RGB);

                            BufferedImage chunk_surface_img = new BufferedImage(16,16,BufferedImage.TYPE_INT_RGB);
                            BufferedImage chunk_biome_img = new BufferedImage(16,16,BufferedImage.TYPE_INT_RGB);
                            BufferedImage chunk_levelheight_img = new BufferedImage(16,16,BufferedImage.TYPE_INT_RGB);


                            for(int x = 0; x < 16; x++){
                                for(int z = 0; z < 16; z++) {
                                    for(int y = chunk.getMaxY()-1; y > chunk.getMinY(); y--) {
                                      //  System.out.println("Y: " + y);
                                        BlockData blockData = chunk.getBlockData(x, y, z);
                                       //

                                      //  System.out.println(biomeTag);

                                        if(blockData != null) {
                                            //String name = tag.getString("Name");
                                            if (!blockData.isAir()) {
                                                BiomeData biomeData = chunk.getBiomeData(x,y,z);

                                                String biomename = null;
                                                Integer foliageOverride = null;
                                                Integer grassOverride = null;
                                                Integer waterColor = null;
                                                int waterdepth = 0;

                                                if(biomeData != null) {
                                                    biomename = biomeData.getName();
                                                    if(blockData.isLeave())
                                                        foliageOverride = biomeData.calculateFoliageColor(y); //biomeData.getFoliageOverride();
                                                    if(blockData.isGrassBlock())
                                                        grassOverride = biomeData.calculateGrassColor(y); //biomeData.getGrassOverride();
                                                    if(blockData.isWater() || blockData.containsWater())
                                                        waterColor = biomeData.getWaterColor();
                                                }

                                                int rgb = textureManager.getTexturePixel(blockData.getName());
                                                int rgb_biome = 0;
                                                int rgb_height = 0;

                                                if(y%2 == 0){
                                                    rgb = new Color(rgb).brighter().getRGB();
                                                }
                                                if(blockData.isWater()){
                                                    if(waterColor != null){
                                                        rgb = waterColor;
                                                    }

                                                    for(int y1 = y; y1 > chunk.getMinY(); y1--){
                                                        BlockData tag1 = chunk.getBlockData(x, y1, z);

                                                        if(tag1 != null && (tag1.isWater() || tag1.containsWater())){
                                                            waterdepth++;
                                                        }else{
                                                          //  if(!tag1.getName().equals("dirt") && !tag1.getName().equals("clay") && !tag1.getName().equals("sand") && !tag1.getName().equals("gravel"))
                                                            //     System.out.println("break water with: " + tag1.getName());
                                                            break;
                                                        }
                                                    }

                                                    Color darkerWater = new Color(rgb);

                                                    int deep = waterdepth;
                                                    while(deep > 5){
                                                        deep -= 5;
                                                        darkerWater = darkerWater.darker();

                                                    }
                                                    rgb = darkerWater.getRGB();

                                                }

                                                // TODO: modify default grass color
                                                if(blockData.isLeave()) {
                                                    if (foliageOverride != null) {
                                                        rgb += foliageOverride;
                                                    }
                                                }
                                                if (blockData.isGrassBlock()) {
                                                    if(grassOverride != null){
                                                        rgb = grassOverride;
                                                    }
                                                }

                                                // TODO: BIOMEMAP
                                                if(biomename != null) {
                                                    rgb_biome = biomename.hashCode();
                                                }

                                                // TODO: HEIGHTMAP
                                                double perc = ((y*1.0)/((chunk.getMaxY()-chunk.getMinY())*1.0));
                                                int colorA = (int) (255.0*perc);
                                                Color color = new Color(colorA,colorA,colorA);
                                                rgb_height = (int) (((color).getRGB()*1.0) * perc);


                                                // TODO: high resolution
                                                BufferedImage resolution = null;
                                                if((blockData.containsWater() || blockData.isWater()) && waterColor != null && waterColor != 0){
                                                    resolution = textureManager.getTexture(blockData.getName(),waterColor,TextureManager.COLORTYPE_WATER);
                                                }else{
                                                  resolution = textureManager.getTexture(blockData.getName());
                                                }



                                                if(resolution != null) {
                                                    int[] colors = resolution.getRGB(0, 0, 16, 16, new int[16 * 16], 0, 16);


                                                    if(grassOverride != null && blockData.isGrassBlock()){
                                                        for(int i1 = 0; i1 < colors.length; i1++){
                                                            //Color before = new Color(colors[i1]);
                                                            //Color override = new Color(grassOverride);
                                                            //TextureManager.subtractColor(before,override).getRGB();
                                                            colors[i1] = grassOverride;
                                                        }
                                                    }

                                                    if(y%2 == 0){
                                                        for(int i1 = 0; i1 < colors.length; i1++){
                                                            colors[i1] = new Color(colors[i1]).brighter().getRGB();
                                                        }
                                                    }
                                                    if(waterdepth > 0){
                                                        for(int i1 = 0; i1 < colors.length; i1++){
                                                            Color darkerC = new Color(colors[i1]);
                                                            int deep = waterdepth;
                                                            while(deep > 5){
                                                                deep -= 5;
                                                                darkerC = darkerC.darker();
                                                            }
                                                            colors[i1] = darkerC.getRGB();


                                                        }

                                                    }
                                                    chunk_surface_img_high.setRGB(x*16, z*16, 16, 16, colors, 0, 16);
                                                }

                                                chunk_biome_img.setRGB(x,z, rgb_biome);
                                                chunk_levelheight_img.setRGB(x,z, rgb_height);
                                                chunk_surface_img.setRGB(x, z, rgb);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            File dir = new File(regiondir+"/imgdata/"+"region_"+mcaData.regionX+"."+mcaData.regionZ+"/chunks");
                            if(!dir.exists()){
                                dir.mkdirs();
                            }

                            File outputfile = new File(dir,cX+"."+cZ+".png");
                            File outputfile_biome = new File(dir,"biome_"+cX+"."+cZ+".png");
                            File outputfile_height = new File(dir,"height_"+cX+"."+cZ+".png");

                            ImageIO.write(chunk_surface_img, "jpg", outputfile);
                            ImageIO.write(chunk_biome_img, "jpg", outputfile_biome);
                            ImageIO.write(chunk_levelheight_img, "jpg", outputfile_height);

                            int xp = (cX%32)*16;
                            if(xp < 0){
                            //    System.out.println("xp<="+xp);
                                xp = xp+32*16;
                            }

                            int zp = (cZ%32)*16;
                            if(zp < 0){
                            ///    System.out.println("zp<="+zp);
                                zp = zp+32*16;
                            }
                            try{
                                totalImage_high.setRGB(xp*16,zp*16,16*16,16*16,chunk_surface_img_high.getRGB(0,0,16*16,16*16,new int[16*16*16*16],0,16*16),0,16*16);

                                totalImage.setRGB(xp,zp,16,16,chunk_surface_img.getRGB(0,0,16,16,new int[16*16],0,16),0,16);
                                totalImage_biome.setRGB(xp,zp,16,16,chunk_biome_img.getRGB(0,0,16,16,new int[16*16],0,16),0,16);
                                totalImage_height.setRGB(xp,zp,16,16,chunk_levelheight_img.getRGB(0,0,16,16,new int[16*16],0,16),0,16);

                            }catch (ArrayIndexOutOfBoundsException exception){
                                exception.printStackTrace();
                                System.out.println("\n\n");
                                System.out.println("xp=" + xp + ", zp="+zp);

                                System.exit(1);
                            }


                        }
                    }
                    File dir = new File(regiondir+"/imgdata/"+"region_"+mcaData.regionX+"."+mcaData.regionZ+"/");
                    if(!dir.exists()){
                        dir.mkdirs();
                    }

                    File outputfile_high = new File(dir,"high_region_"+mcaData.regionX+"."+mcaData.regionZ+".png");
                    File outputfile = new File(dir,"region_"+mcaData.regionX+"."+mcaData.regionZ+".png");
                    File outputfile_biome = new File(dir,"biome_region_"+mcaData.regionX+"."+mcaData.regionZ+".png");
                    File outputfile_height = new File(dir,"height_region_"+mcaData.regionX+"."+mcaData.regionZ+".png");

                    ImageIO.write(totalImage_high, "jpg", outputfile_high);
                    ImageIO.write(totalImage, "jpg", outputfile);
                    ImageIO.write(totalImage_biome, "jpg", outputfile_biome);
                    ImageIO.write(totalImage_height, "jpg", outputfile_height);


                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        long c = System.currentTimeMillis()-a;
        System.out.println("Finished creating Images! ("+getZeit(c)+")");

    }
}
