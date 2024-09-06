
package com.ou.formatters;

import com.ou.pojo.User;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;


public class UserFormatters implements Formatter<User>{

    @Override
    public String print(User u, Locale locale) {
        return String.valueOf(u.getId());
    }

    @Override
    public User parse(String string, Locale locale) throws ParseException {
        User u = new User();
        u.setId(Integer.parseInt(string));
        return u;
    }
    
}
