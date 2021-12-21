package de.freesoccerhdx;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.security.CodeSource;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TextureManager {

    private HashMap<String,Integer> textureMap = new HashMap<>();

    private TextureManager(){

    }

    public Set<String> getKeys(){
        return textureMap.keySet();
    }

    public int getTexturePixel(String name){
        if(name.startsWith("minecraft:")){
            name = name.split("minecraft:",2)[1];
        }


        if(textureMap.containsKey(name+".png")){
            return textureMap.get(name+".png");
        }
        if(textureMap.containsKey(name+"_top.png")){
            return textureMap.get(name+"_top.png");
        }

        return 0;
    }

    private int getTexturePixel(BufferedImage img){

        int size = img.getHeight()*img.getWidth();

        int[] rgb = new int[3];

        for(int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                Color color = new Color(img.getRGB(x,y));
                rgb[0] += color.getRed();
                rgb[1] += color.getGreen();
                rgb[2] += color.getBlue();
            }
        }

        /*
        long allpixel = 0;
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                int rgb = img.getRGB(x,y);
                allpixel += rgb;
            }
        }

        return (int) (allpixel/size);
        */

        return new Color(rgb[0]/size,rgb[1]/size,rgb[2]/size).getRGB();
    }

    private void loadData(){
        try {
            CodeSource src = Main.class.getProtectionDomain().getCodeSource();
            if (src != null) {
                URL jar = src.getLocation();
                ZipInputStream zip = new ZipInputStream(jar.openStream());
                try {
                    while (true) {
                        ZipEntry e = zip.getNextEntry();
                        if (e == null)
                            break;
                        String name = e.getName();

                        if(name.startsWith("textures") && name.endsWith(".png")){
                            BufferedImage img = null;
                            try {
                                InputStream stream = Main.class.getResourceAsStream("/"+name);
                                //System.out.println("Path: " + jar.toString()+"/"+name + " stream="+stream);
                                img = ImageIO.read(stream);
                                int texturePixel = getTexturePixel(img);
                                if(texturePixel != -1){
                                    textureMap.put(name.split("/")[1],texturePixel);
                                }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }finally {
                    zip.close();
                }

            }
        }catch (Exception exception){
            exception.printStackTrace();
        }

        // loading some defaults:
        textureMap.put("water.png",new Color(	63, 118, 228).getRGB());
        textureMap.put("lava.png",new Color(	217, 98, 0).getRGB());

        //textureMap.put("grass_block.png",new Color(	9470285).getRGB());
    }


    public static TextureManager create(){
        TextureManager textureManager = new TextureManager();
        textureManager.loadData();
        return textureManager;
    }





}
