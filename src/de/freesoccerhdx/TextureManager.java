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

    public static final int COLORTYPE_WATER = 1;

    private HashMap<String,Integer> texturePixelMap = new HashMap<>();
    private HashMap<String,BufferedImage> textureMap = new HashMap<>();

    private TextureManager(){

    }

    public Set<String> getKeys(){
        return textureMap.keySet();
    }


    public static Color addColor(Color ac, Color bc){
        int r = ac.getRed() + bc.getRed();
        int g = ac.getGreen() + bc.getGreen();
        int b = ac.getBlue() + bc.getBlue();

        return new Color(Math.min(255,r),Math.min(255,g),Math.min(255,b));
    }

    public static Color subtractColor(Color ac, Color bc){
        int r = ac.getRed() - bc.getRed();
        int g = ac.getGreen() - bc.getGreen();
        int b = ac.getBlue() - bc.getBlue();

        return new Color(Math.max(0,r),Math.max(0,g),Math.max(0,b));
    }

    public BufferedImage getTexture(String name, int colormodification, int colortype){

        BufferedImage image = getTexture(name);
        int pixel = getTexturePixel(name);
        Color pixelC = new Color(pixel);

        if(image != null){
            if(colortype == COLORTYPE_WATER) {
                for (int x = 0; x < image.getHeight(); x++) {
                    for (int z = 0; z < image.getWidth(); z++) {
                        /*
                        Color before = new Color(image.getRGB(x,z));
                        Color modi = new Color(colormodification);

                        Color newcolor = subtractColor(before,pixelC);
                        newcolor = addColor(newcolor,modi);
                        */

                        //Color before = new Color(image.getRGB(x,z));
                        //Color modi = new Color(colormodification);
                        //addColor(before,modi).getRGB()

                        image.setRGB(x, z, colormodification);
                    }
                }
            }
        }

        return image;
    }

    public BufferedImage getTexture(String name){
        name = reduceName(name);

        if(textureMap.containsKey(name+".png")){
            return textureMap.get(name+".png");
        }
        if(textureMap.containsKey(name+"_top.png")){
            return textureMap.get(name+"_top.png");
        }

        return null;
    }

    public int getTexturePixel(String name){
        name = reduceName(name);

        if(texturePixelMap.containsKey(name+".png")) {
            return texturePixelMap.get(name + ".png");
        }
        if(texturePixelMap.containsKey(name+"_top.png")){
            return texturePixelMap.get(name+"_top.png");
        }

        return 0;
    }

    private String reduceName(String name) {
        if(name.startsWith("minecraft:")){
            name = name.split("minecraft:",2)[1];
        }
        return name;
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
                                if(img == null){
                                    System.out.println("IMAGE IS NULL?! -> " + name + " stream="+stream);
                                    img.getHeight();
                                }else {
                                    int texturePixel = getTexturePixel(img);
                                    if (texturePixel != -1) {
                                        texturePixelMap.put(name.split("/")[1], texturePixel);

                                        textureMap.put(name.split("/")[1], resizeImage(img, 16, 16));
                                    }
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
      //  texturePixelMap.put("water.png",new Color(	63, 118, 228).getRGB());
        texturePixelMap.put("lava.png",new Color(	217, 98, 0).getRGB());

        //textureMap.put("grass_block.png",new Color(	9470285).getRGB());
    }


    public static TextureManager create(){
        TextureManager textureManager = new TextureManager();
        textureManager.loadData();
        return textureManager;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
        try {
            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = resizedImage.createGraphics();
            graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
            graphics2D.dispose();
            return resizedImage;
        }catch (Exception exception){
            exception.printStackTrace();
        }

        return null;
    }





}
