package entity;

import event.CommandEvent;

import java.net.URL;

public abstract class APIs {
    private final String name;

    private final URL url;

    public APIs(String name, URL url){this.name = name; this.url = url;}

    public String getName(){
        return name;
    }

    public abstract void execute(CommandEvent commandEvent);

}
