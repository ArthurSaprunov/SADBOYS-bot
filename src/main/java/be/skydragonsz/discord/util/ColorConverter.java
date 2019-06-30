package be.skydragonsz.discord.util;

import java.awt.*;

public class ColorConverter {
    public static int convertToDecimal(String hex){
        if(hex.contains("#")) hex = hex.replace("#","");
        if(hex.contains(",")) hex = hex.replace(",","");
        return Integer.parseInt(hex,16);
    }

    public static int convertToDecimal(String red, String green, String blue){
        String tempHex = red + green + blue;
        return convertToDecimal(tempHex);

    }

    public static int convertToDecimal(Color color){
        return color.getRGB();

    }
}
