/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colorConversion;

import java.awt.Color;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser.Event;
import static javax.json.stream.JsonParser.Event.KEY_NAME;
import static javax.json.stream.JsonParser.Event.VALUE_NUMBER;

/**
 * JsonColorDeserializer: Converts Json to Color
 * 
 * @author fmdes
 * @version 1.0
 */

public class JsonColorDeserializer implements JsonbDeserializer<Color> {
    
    @Override
    public Color deserialize(javax.json.stream.JsonParser parser, javax.json.bind.serializer.DeserializationContext ctx, java.lang.reflect.Type rtType) {
        String keyname = ""; 
        int value = 0; 
        int red = 0; 
        int green = 0; 
        int blue = 0;        
        while (parser.hasNext()) {
            Event event = parser.next();
            
            switch (event) {
                case KEY_NAME: {
                keyname = parser.getString();         
                break;
                }
                case VALUE_NUMBER: {
                    value = parser.getInt();         
                    if (keyname.equals("red")) red = value;
                    else if (keyname.equals("green")) green = value;
                    else if (keyname.equals("blue")) blue = value;
                    break;
                }
            }
        }
        return new Color(red,green,blue);
    }
}

    

