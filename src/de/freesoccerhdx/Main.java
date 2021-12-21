package de.freesoccerhdx;

import net.querz.mca.Chunk;
import net.querz.mca.LoadFlags;
import net.querz.mca.MCAFile;
import net.querz.mca.MCAUtil;
import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.io.NamedTag;
import net.querz.nbt.tag.CompoundTag;

import javax.imageio.ImageIO;
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

        long b = System.currentTimeMillis()-a;
        System.out.println("Finished loading Resources! ("+getZeit(b)+")");


        try {
            File regiondir = new File(args[0]);

            File[] files = regiondir.listFiles();

            int maximalchunks = 32*32*files.length;
            int chunks = 0;

            for (File mcaFile : files) {
                if (mcaFile.getName().endsWith(".mca")) {
                    System.out.println(">>>" + mcaFile.getName());
                    //NamedTag namedTag = NBTUtil.read(mcaFile);
                    //System.out.println(">>>" + namedTag.toString());

                    MCAFile mcaData = MCAUtil.read(mcaFile, LoadFlags.BLOCK_STATES);

                    Iterator<Chunk> chunkIterator = mcaData.iterator();

                    BufferedImage totalImage = new BufferedImage(16*32,16*32,BufferedImage.TYPE_INT_RGB);
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

                            BufferedImage image = new BufferedImage(16,16,BufferedImage.TYPE_INT_RGB);

                            for(int x = 0; x < 16; x++){
                                for(int z = 0; z < 16; z++) {
                                    for(int y = chunk.getMaxY()-1; y > chunk.getMinY(); y--) {
                                      //  System.out.println("Y: " + y);
                                        CompoundTag tag = chunk.getBlockStateAt(x, y, z);
                                       //

                                      //  System.out.println(biomeTag);

                                        if(tag != null) {
                                            String name = tag.getString("Name");
                                            if (!name.equals("minecraft:air")) {
                                                BiomeData biomeData = chunk.getBiomeData(x,y,z);

                                                int rgb = textureManager.getTexturePixel(name);
                                                if(y%2 == 0){
                                                    rgb = new Color(rgb).darker().getRGB();
                                                }
                                                if(name.equals("minecraft:water")){
                                                    if(biomeData != null){
                                                        rgb = biomeData.getWaterColor();
                                                    }

                                                    int deep = 0;
                                                    for(int y1 = y; y1 > chunk.getMinY(); y1--){
                                                        CompoundTag tag1 = chunk.getBlockStateAt(x, y1, z);
                                                        if(tag1.getString("Name").equals("minecraft:water")){
                                                            deep++;
                                                        }else{
                                                            break;
                                                        }
                                                    }
                                                  //  System.out.println("darkerWater: " + deep);
                                                    Color darkerWater = new Color(rgb);

                                                    while(deep > 5){
                                                        deep -= 5;
                                                        darkerWater = darkerWater.darker();
                                                    }
                                                    rgb = darkerWater.getRGB();

                                                }

                                                if(biomeData != null) {
                                                    String biomename = biomeData.getName();
                                                    System.out.println("biomename> " + biomename);

                                                    if(biomeData.getFoliageOverride() != null){
                                                        // if name(of block) == leave
                                                    }
                                                    if (name.equals("minecraft:grass_block")) {
                                                        if(biomeData.getGrassOverride() != null){
                                                            rgb += biomeData.getGrassOverride();

                                                        }
                                                    }else{
                                                        // modify to default grass color
                                                    }



                                                    /*
                                                    if (name.equals("minecraft:grass_block")) {
                                                        if(biomename.equals("birch_forest")){
                                                            rgb = new Color(255,120,0).getRGB();
                                                        }
                                                    }
                                                    */

                                                    rgb = biomename.hashCode();
                                                }

                                                image.setRGB(x, z, rgb);
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
                            ImageIO.write(image, "jpg", outputfile);

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
                                totalImage.setRGB(xp,zp,16,16,image.getRGB(0,0,16,16,new int[16*16],0,16),0,16);
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
                    File outputfile = new File(dir,"region_"+mcaData.regionX+"."+mcaData.regionZ+".png");
                    ImageIO.write(totalImage, "jpg", outputfile);


                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        long c = System.currentTimeMillis()-a;
        System.out.println("Finished creating Images! ("+getZeit(c)+")");

    }
}
